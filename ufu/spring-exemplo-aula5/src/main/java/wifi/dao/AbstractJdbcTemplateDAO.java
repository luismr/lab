package wifi.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractJdbcTemplateDAO {

	protected JdbcTemplate jdbcTemplate;
	
	public AbstractJdbcTemplateDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
