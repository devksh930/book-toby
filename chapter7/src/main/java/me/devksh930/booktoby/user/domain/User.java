package me.devksh930.booktoby.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    String id;
    String name;
    String password;
    String email;
    Level level;
    int login;
    int recommend;

    public User(
            final String id,
            final String name,
            final String password,
            final String email,
            final Level level,
            final int login,
            final int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(this.level + "은 업그레이드가 불가능 합니다");
        } else {
            this.level = nextLevel;
        }
    }
}
