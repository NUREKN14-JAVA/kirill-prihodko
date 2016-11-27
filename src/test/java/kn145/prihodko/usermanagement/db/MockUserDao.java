package kn145.prihodko.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import kn145.prihodko.usermanagement.User;

public class MockUserDao implements UserDAO {

	private long id = 0;
	private Map users = new HashMap();
	
	@Override
	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		return user;
	}

	@Override
	public void update(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		users.remove(currentId);
		users.put(currentId, user);
		

	}

	@Override
	public void delete(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		users.remove(currentId);

	}

	@Override
	public User find(Long id) throws DatabaseException {
		
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseException {
		
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
