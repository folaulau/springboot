package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.kaveinga.utils.RandomGeneratorUtils;

public class V5__removePassword extends BaseJavaMigration {
	
	public void migrate(Context context) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("ALTER TABLE `user` ");
		queryBuilder.append("DROP COLUMN `password`; ");
		
		jdbcTemplate.execute(queryBuilder.toString());
	}
}
