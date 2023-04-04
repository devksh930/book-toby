package me.devksh930.booktoby.user.service;

import me.devksh930.booktoby.user.domain.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void upgradeLevels();
    List<User> getAll();
    void update(User user);

}
