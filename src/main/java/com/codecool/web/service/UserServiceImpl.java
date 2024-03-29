package com.codecool.web.service;

import com.codecool.web.exceptions.AlreadyExistsException;
import com.codecool.web.exceptions.NotValidEmailException;
import com.codecool.web.model.User;

import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService {
    private List<User> users;

    public UserServiceImpl() {
        users = DataContainer.getInstance().getUsersList();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addNewUser(User newUser) {
        users.add(newUser);
    }

    public User getUser(String name) {
        User loginUser = null;
        for (User user : users) {
            if (user.getName().equals(name)) {
                loginUser = user;
            }
        }
        return loginUser;
    }

    public void register(String name, String email, String password, boolean isMentor) throws AlreadyExistsException, NotValidEmailException {
        System.out.println("userservice: name " + name + " email " + email + " pw " + password);
        if (users.size() > 0) {
            System.out.println(users.size());
            User tempUser = null;
            for (User user : users) {
                System.out.println(user.getName());
                if (user.getName().equals(name)) {
                    throw new AlreadyExistsException("this name already in use");
                } else if (user.getEmail().equals(email)) {
                    throw new AlreadyExistsException("this email already in use");
                } else if (!email.contains("@")) {
                    throw new NotValidEmailException("That is not a valid e-mail");
                } else {
                    tempUser = (new User(generateId(), name, email, password, isMentor));
                }
            }
            users.add(tempUser);
        } else {
            users.add(new User(generateId(), name, email, password, isMentor));


        }

    }

    public int generateId() {
        Random rand = new Random();
        int n = rand.nextInt(10000);
        while (users.contains(n)) {
            n = rand.nextInt(10000);
        }
        return n;
    }

    public boolean authenticateUser(String name, String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getUserById(int id) {
        for (User temp : users) {
            if (temp.getId() == id) {
                return temp;
            }
        }
        return null;
    }

    public void editUser(int id, String email, String password, boolean permission) {
        User editedUser = getUserById(id);
        editedUser.setEmail(email);
        editedUser.setPassword(password);
        editedUser.setPermission(permission);
    }
}
