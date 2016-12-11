package kn145.prihodko.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import kn145.prihodko.usermanagement.User;

public class MockUserDao implements UserDao {
    private long id = 0;
    private Map<Long, User> users = new HashMap<Long, User>();

    public User create(User user) throws DatabaseException {
        //System.out.println("create " + user);
        Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    public void update(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
    }

    public void delete(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

    public User find(Long id) throws DatabaseException {
        
        return (User) users.get(id);
    }

    public Collection<?> findAll() throws DatabaseException {
        return users.values();
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        // TODO Auto-generated method stub

    }
    

}