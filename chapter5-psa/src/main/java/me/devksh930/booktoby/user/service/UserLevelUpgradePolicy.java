package me.devksh930.booktoby.user.service;

import me.devksh930.booktoby.user.domain.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);

    void upgradeLevel(User user);
}
