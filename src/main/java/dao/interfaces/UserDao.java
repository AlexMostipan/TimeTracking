package dao.interfaces;

import model.User;

import java.util.List;

public interface UserDao {


    public int create(User user);

    public User findByEmail(String email);

    public List<User> findAll();

    public boolean update(User user);

    public boolean delete(int id);

    public User findById(int id);

    public User getUserByUserName(String username);
}

