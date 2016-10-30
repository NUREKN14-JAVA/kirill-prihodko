package db;

import java.sql.Connection;

public interface ConnectionFactory {
	public Connection createConnection() throws DatabaseException;
}
