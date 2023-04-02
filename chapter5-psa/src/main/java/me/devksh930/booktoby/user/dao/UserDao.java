package me.devksh930.booktoby.user.dao;

import me.devksh930.booktoby.user.domain.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    User get(String id);

    List<User> getAll();

    void deleteAll();

    int getCount();

    void update(User user);
}
