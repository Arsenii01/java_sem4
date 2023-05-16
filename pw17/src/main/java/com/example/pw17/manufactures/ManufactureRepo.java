package com.example.pw17.manufactures;

import com.example.pw17.phones.Phone;
import java.util.List;
import javax.sql.DataSource;

import com.example.pw17.phones.PhoneDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureRepo {

	public JdbcTemplate jdbcTemplate;
	private final RowMapper<Manufacture> manufactureRowMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public ManufactureRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		manufactureRowMapper =
			(rs, rowNum) -> {
				Manufacture manufacture = new Manufacture();
				manufacture.setId(rs.getLong("id"));
				manufacture.setName(rs.getString("name"));
				manufacture.setAddress(rs.getString("address"));
				long phoneId = rs.getLong("phone_id");
				if (phoneId != 0) {
					Phone phone = new Phone();
					phone.setId(phoneId);
					phone.setName(rs.getString("phone_name"));
					phone.setCreationYear(
						rs.getInt("phone_creation_year")
					);
					manufacture.setPhone(phone);
				}
				return manufacture;
			};
	}

	public List<Manufacture> findAll() {
		String sql =
			"SELECT m.*, p.name AS phone_name, p.creation_year AS phone_creation_year " +
			"FROM manufactures m " +
			"LEFT JOIN phones p ON m.phone_id = p.id";
		return jdbcTemplate.query(sql, manufactureRowMapper);
	}

	public Manufacture findById(Long id) {
		String sql =
			"SELECT m.*, p.name AS phone_name, p.creation_year AS phone_creation_year " +
			"FROM manufactures m " +
			"LEFT JOIN phones p ON m.phone_id = p.id " +
			"WHERE m.id = ?";

		return jdbcTemplate
			.query(sql, manufactureRowMapper, id)
			.get(0);
	}

	public void save(Manufacture manufacture) {
		String sql =
			"INSERT INTO manufactures (name, address, phone_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(
			sql,
			manufacture.getName(),
			manufacture.getAddress(),
			manufacture.getPhone().getId()
		);
	}

	public void deleteById(Long id) {
		String sql = "DELETE FROM manufactures WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public List<Manufacture> findByFilters(String name, String address) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Manufacture> query = builder.createQuery(Manufacture.class);
		Root<Manufacture> manufactureRoot = query.from(Manufacture.class);

		Predicate predicate = builder.conjunction();

		if (name != null && !name.isEmpty()) {
			predicate = builder.and(predicate, builder.like(manufactureRoot.get("name"), name));
		}
		if (address != null && !address.isEmpty()) {
			predicate = builder.and(predicate, builder.like(manufactureRoot.get("address"), address));
		}

		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}
}
