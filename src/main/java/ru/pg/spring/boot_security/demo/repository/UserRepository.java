package ru.pg.spring.boot_security.demo.repository;


import ru.pg.spring.boot_security.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u join fetch u.roles where u.username = :username")
    User findByUsername(@Param("username") String username);
}
