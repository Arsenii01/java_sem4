package com.example.pw17.phones;

import com.example.pw17.manufactures.Manufacture;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.example.pw17.manufactures.ManufactureDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class PhoneRepo {

	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<Phone> phoneRawMapper;
	private final RowMapper<Phone> phoneRawMapperWithFilter;
	private final RowMapper<Manufacture> manufactureRowMapper;
	@PersistenceContext
	private EntityManager entityManager;

	public PhoneRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		phoneRawMapper =
			new BeanPropertyRowMapper<>(Phone.class);
		phoneRawMapperWithFilter = (rs, rowNum) -> {
			Phone phone = new Phone();
			phone.setId(rs.getLong("id"));
			phone.setName(rs.getString("name"));
			phone.setCreationYear(rs.getInt("creation_year"));

			try {
				List<Manufacture> manufactures = new ArrayList<>();
				manufactures.add(new Manufacture());
				manufactures.get(0).setAddress(rs.getString("manufacture_address"));
				manufactures.get(0).setName(rs.getString("manufacture_name"));
				manufactures.get(0).setId(rs.getLong("manufacture_id"));
				phone.setManufactures(manufactures);
			} catch (Exception ignored) {}

			return phone;
		};
		manufactureRowMapper =
			new BeanPropertyRowMapper<>(Manufacture.class);
	}

	public List<Phone> findAll() {
		String sql = "SELECT * FROM phones";
		return jdbcTemplate.query(sql, phoneRawMapper);
	}

	public Phone findById(Long id) {
		String sql = "SELECT * FROM phones WHERE id = ?";
		Phone phone = jdbcTemplate
			.query(sql, phoneRawMapper, id)
			.get(0);

		phone.setManufactures(getManufactureByPhoneId(id));
		return phone;
	}

	private List<Manufacture> getManufactureByPhoneId(
		Long id
	) {
		String sql =
			"SELECT m.name, m.address, m.id FROM manufactures m " +
			"WHERE m.phone_id = ?";

		return jdbcTemplate.query(
			sql,
				manufactureRowMapper,
			id
		);
	}

	public void save(Phone phone) {
		String sql =
			"INSERT INTO phones (name, creation_year) VALUES (?, ?)";
		jdbcTemplate.update(
			sql,
			phone.getName(),
			phone.getCreationYear()
		);
	}

	public void deleteById(Long id) {
		String sql = "DELETE FROM phones WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public List<Phone> findByFilters(
			String name, String creationYear
	) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
		Root<Phone> phoneRoot = criteriaQuery.from(Phone.class);

		Predicate predicate = builder.conjunction();

		if (name != null && !name.isEmpty()) {
			predicate = builder.and(predicate, builder.like(phoneRoot.get("name"), name));
		}
		if (creationYear != null && !creationYear.isEmpty()) {
			predicate = builder.and(predicate, builder.like(phoneRoot.get("creationYear"), creationYear));
		}

		criteriaQuery.select(phoneRoot).orderBy(builder.asc(phoneRoot.get("name")));

		criteriaQuery.where(predicate);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public List<Phone> nativeFindByFilters(
			String name, String creationYear,
			String manufactureName, String manufactureAddress
	) {
		StringBuilder sqlBuilder = new StringBuilder(
			"SELECT p.id, p.name, p.creation_year"
		);

		boolean isManufactureName = !manufactureName.isEmpty();
		boolean isManufactureAddress = !manufactureAddress.isEmpty();
		boolean isManufacture = isManufactureName || isManufactureAddress;

		if (isManufacture) {
			sqlBuilder.append(
					", m.name AS manufacture_name, m.address AS manufacture_address, m.id AS manufacture_id"
			);
		}
		sqlBuilder.append(" FROM phones p");
		if (isManufacture) {
			sqlBuilder.append(" LEFT JOIN manufactures m ON p.id = m.phone_id");
		}

		sqlBuilder.append(" WHERE 1=1");
		List<Object> params = new ArrayList<>();
		if (!name.isEmpty()) {
			sqlBuilder.append(" AND p.name = ?");
			params.add(name);
		}
		if (!creationYear.isEmpty()) {
			sqlBuilder.append(" AND p.creation_year = ?");
			params.add(creationYear);
		}
		if (isManufactureName) {
			sqlBuilder.append(" AND m.name = ?");
			params.add(manufactureName);
		}
		if (isManufactureAddress) {
			sqlBuilder.append(" AND m.address = ?");
			params.add(manufactureAddress);
		}

		return jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), phoneRawMapperWithFilter);
	}
}
