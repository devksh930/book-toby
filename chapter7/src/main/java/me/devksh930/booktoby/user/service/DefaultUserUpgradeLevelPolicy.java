package me.devksh930.booktoby.user.service;

import me.devksh930.booktoby.user.domain.Level;
import me.devksh930.booktoby.user.domain.User;

import static me.devksh930.booktoby.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static me.devksh930.booktoby.user.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;

public class DefaultUserUpgradeLevelPolicy implements UserLevelUpgradePolicy {
    public DefaultUserUpgradeLevelPolicy() {
    }

    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        return switch (currentLevel) {
            case BASIC -> (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER -> (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
            case GOLD -> false;
            default -> throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        };
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
    }
}
