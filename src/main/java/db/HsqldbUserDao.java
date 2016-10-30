package db;

import java.util.Collection;

import java.lang.Exception;

import kn145.prihodko.usermanagement.User;

import org.dbunit.DatabaseTestCase;

public class HsqldbUserDao implements UserDAO {

	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}
	@Override
	public User create(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
