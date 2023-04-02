package me.devksh930.booktoby.user.service;

import me.devksh930.booktoby.user.domain.Level;
import me.devksh930.booktoby.user.domain.User;

public class EventUserUpgradePolicy implements UserLevelUpgradePolicy {
    private final int bonusCount;

    public EventUserUpgradePolicy() {
        this.bonusCount = 10;
    }

    public EventUserUpgradePolicy(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        return switch (currentLevel) {
            case BASIC -> user.getLogin() + bonusCount >= UserService.MIN_LOGCOUNT_FOR_SILVER;
            case SILVER -> user.getRecommend() + bonusCount >= UserService.MIN_RECCOMEND_FOR_GOLD;
            case GOLD -> false;
            default -> throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        };
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
    }
}
