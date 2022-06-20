package com.mvarlamov.Music.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User {
    @NotNull
    private String login;
    private String pwdHash;
    private Role role;
    private String name;
    private Integer age;
    private Gender gender;
}
