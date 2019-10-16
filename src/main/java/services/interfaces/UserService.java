package services.interfaces;

import model.User;

import java.util.List;

public interface UserService {
    User login(String username, String pass);

    void create(String username, String pass, String email);

    User getUserByUsername(String username);

    User getUserById(int userId);

    public List<User> getAllUsers();
}
