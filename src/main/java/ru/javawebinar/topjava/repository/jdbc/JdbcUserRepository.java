package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional()
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:name, email=:email, password=:password, " +
                        "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional()
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        User user = DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id));
        if (user == null) {
            return null;
        }
        Set<Role> roleSet = getUserRole(user);
        user.setRoles(roleSet);
        return user;
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        User user = DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email));
        if (user == null) {
            return null;
        }
        Set<Role> roleSet = getUserRole(user);
        user.setRoles(roleSet);
        return user;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
    }

    @Override
    @Transactional
    public void addRole(Role role, int id) {
        jdbcTemplate.update("INSERT INTO user_roles VALUES (?, ?)", id, role.name());
    }

    @Override
    @Transactional
    public void setRole(Role role, int id) {
        jdbcTemplate.update("UPDATE user_roles SET role=? WHERE user_id=?", role.name(), id);
    }

    @Override
    @Transactional
    public void deleteRole(Role role, int id) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=? AND role=?", id, role.name());
    }

    private Set<Role> getUserRole(User user) {
        List<String> roles = jdbcTemplate.queryForList("SELECT role FROM user_roles where user_id=?", String.class, user.getId());
        return roles.stream().map(s -> {
            if (s.equals(Role.ROLE_USER.name())) {
                return Role.ROLE_USER;
            } else {
                return Role.ROLE_ADMIN;
            }
        }).collect(Collectors.toSet());
    }
}
