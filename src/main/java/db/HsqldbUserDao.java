package db;

import java.util.Collection;
import java.util.LinkedList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kn145.prihodko.usermanagement.User;

class HsqldbUserDao implements UserDAO {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private static final String FIND_QUERY =  "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(){
		
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			int n = statement.executeUpdate();
			if(n != 1)
				throw new DatabaseException("The number of the inserted rows is " + n);
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if(keys.next())
				user.setId(new Long(keys.getLong(1)));
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (SQLException e) {
			throw new DatabaseException();
		} catch (DatabaseException e) {
			throw e;
		}
	}

	@Override
	public void delete(User user) throws DatabaseException {
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(this.DELETE_QUERY);
			statement.setLong(1, new Long(user.getId()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of deleted rows: " + n);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User user) throws DatabaseException {
		try
		{
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			statement.setLong(4, new Long(user.getId()));
			int n = statement.executeUpdate();
			if(n != 1)
				throw new DatabaseException("update failed - number of updated rows: " + n);
		}
		catch(DatabaseException e)
		{
			throw e;
		}
		catch(SQLException e)
		{
			throw new DatabaseException(e);
		}		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
		try {
        	PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
    		statement.setLong(1, id);
    		ResultSet res = statement.executeQuery();
            if(res.next()) {
            	User user = new User();
	            user.setId(res.getLong(1));
	            user.setFirstName(res.getString(2));
	            user.setLastName(res.getString(3));
	            user.setDateOfBirthd(res.getDate(4));
	            return user;
            }
            res.close();
			statement.close();
			connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
       return null;
    }

	@Override
	public Collection findAll() throws DatabaseException {
		
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next())	{
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

}
