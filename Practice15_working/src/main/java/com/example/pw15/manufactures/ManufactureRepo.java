package com.example.pw15.manufactures;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureRepo {

	public JdbcTemplate jdbcTemplate;

	public ManufactureRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Manufacture> findAll() {
		String sql = "SELECT * FROM manufactures";
		return jdbcTemplate.query(
			sql,
			new BeanPropertyRowMapper<>(Manufacture.class)
		);
	}

	public void save(Manufacture manufacture) {
		String sql =
			"INSERT INTO manufactures (name, address) VALUES (?, ?)";
		jdbcTemplate.update(
			sql,
			manufacture.getName(),
			manufacture.getAddress()
		);
	}

	public void deleteById(Long id) {
		String sql = "DELETE FROM manufactures WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}
