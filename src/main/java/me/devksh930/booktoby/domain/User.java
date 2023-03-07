package me.devksh930.booktoby.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;

    public User(
            final String id,
            final String name,
            final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
