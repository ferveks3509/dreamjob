package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {
    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try(var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO users(email, password) values (:email, :password)", true)
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword());
            int genId = query.executeUpdate().getKey(Integer.class);
            user.setId(genId);
            return Optional.of(user);
        }
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users where email = :email AND password = :password")
                    .addParameter("email", email)
                    .addParameter("password", password);
            var user = query.executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }
}
