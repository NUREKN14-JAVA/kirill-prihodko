package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String driver;
	private String url;
	private String user;
	private String password;
	
	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.password = password;
		this.user = user;
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		String url = "jdbc:hsqldb:file:db/usermanagement";
		String user = "sa";
		String password = "";
		String driver = "org.hsqldb.jdbcDriver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}
