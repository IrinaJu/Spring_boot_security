package ru.pg.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pg.spring.boot_security.demo.model.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
}
