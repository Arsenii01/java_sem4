package com.example.pw15.phones;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneRepo {

	private final JdbcTemplate jdbcTemplate;

	public PhoneRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Phone> findAll() {
		String sql = "SELECT * FROM phones";
		return jdbcTemplate.query(
			sql,
			new BeanPropertyRowMapper<>(Phone.class)
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
}
