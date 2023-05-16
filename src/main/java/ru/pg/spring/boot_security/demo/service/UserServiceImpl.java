package ru.pg.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pg.spring.boot_security.demo.model.Role;
import ru.pg.spring.boot_security.demo.model.User;
import ru.pg.spring.boot_security.demo.repository.RoleRepository;
import ru.pg.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(passwordCoder(user));
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @PostConstruct
    public void addDefaultUser() {
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(roleRepository.findById(1L).get());
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleRepository.findById(1L).get());
        roleSet2.add(roleRepository.findById(2L).get());
        User user1 = new User("name1", "lastname1", (byte) 14, "user1", "pass1", roleSet1);
        User user2 = new User("name2", "lastname2", (byte) 11, "admin1", "admin1", roleSet2);
        save(user1);
        save(user2);

    }
}
