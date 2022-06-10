package org.mvarlamov.controller;

import org.mvarlamov.model.Dish;
import org.mvarlamov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.constraints.NotNull;
import java.util.List;

@EnableWebMvc
@RestController
public class MyRestController {
    @Autowired
    JdbcTemplate template;
    private final AuthenticationManager authenticationManager;

    public MyRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/test")
    public List<Dish> test() {
        return template.query("SELECT * FROM Dish", new BeanPropertyRowMapper<>(Dish.class));
    }

    @PostMapping("/auth")
    public String authorize(@NotNull @RequestParam String login, @NotNull @RequestParam String password) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    login, password
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            System.err.println(user);
            return "ok";
        } catch (BadCredentialsException ex) {
            return "not ok";
        }
    }
}
