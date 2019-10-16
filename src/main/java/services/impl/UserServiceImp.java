package services.impl;

import dao.TransactionManager;
import dao.interfaces.UserDao;
import model.User;
import model.enums.Role;
import services.interfaces.UserService;

import java.util.List;

public class UserServiceImp implements UserService {
    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImp(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }


    @Override
    public User login(String email, String pass) {
        try {
            transactionManager.getConnection();
            User user = userDao.findByEmail(email);
            if (user == null || !user.getPassword().equals(pass)) {
                return null;
            }
            return user;
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void create(String username, String pass, String email) {
        try {
            User user = new User(Role.USER, username, pass, email);
            transactionManager.getConnection();
            userDao.create(user);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            transactionManager.getConnection();
            return userDao.getUserByUserName(username);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public User getUserById(int userId) {
        try {
            transactionManager.getConnection();
            return userDao.findById(userId);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            transactionManager.getConnection();
            return userDao.findAll();
        } finally {
            transactionManager.closeConnection();
        }
    }

}

