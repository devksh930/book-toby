package me.devksh930.booktoby.user.service;

import me.devksh930.booktoby.user.domain.User;

public interface UserService {
    void add(User user);

    void upgradeLevels();
}
