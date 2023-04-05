package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FINDUSERSQL = """
            select * from user where username = ?
            """;

    private static final String INSERTUSERSQL = """
            insert into user
            (user_id, username , name)
            values
            (? , ? , ? )
            """;

    public Optional<User> findUserByUsername(String username) {

        try {
            User user = jdbcTemplate.queryForObject(FINDUSERSQL, BeanPropertyRowMapper.newInstance(User.class),
                    username);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public String insertUser(User user) {

        String newuser_id = UUID.randomUUID().toString().substring(0, 8);

        try {
            Integer rowsupdated = jdbcTemplate.update(INSERTUSERSQL, newuser_id, user.getUsername(), user.getName());

            if (rowsupdated > 0) {
                return newuser_id;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("SQL Error");
            return null;
        }

    }

}
