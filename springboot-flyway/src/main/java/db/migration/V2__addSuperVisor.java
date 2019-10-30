package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.kaveinga.utils.RandomGeneratorUtils;

public class V2__addSuperVisor extends BaseJavaMigration {
	
	public void migrate(Context context) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("INSERT INTO `user` ");
		queryBuilder.append("(`email`,`first_name`,`last_name`,`password`,`phone_number`,`uuid`)");
		queryBuilder.append("VALUES");
		queryBuilder.append("('folaudev+"+RandomGeneratorUtils.getIntegerWithin(1, Integer.MAX_VALUE)+"@gmail.com','Supervisor','Kaveinga','Test in plain text','3101234567','"+RandomGeneratorUtils.getUuid("usr")+"')");
		try {
			jdbcTemplate.execute(queryBuilder.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
