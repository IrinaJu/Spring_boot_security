package ru.pg.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pg.spring.boot_security.demo.model.User;
import ru.pg.spring.boot_security.demo.service.RoleService;
import ru.pg.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping()
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("admin/new")
    public String pageCreateUser(User user, Model model) {
        model.addAttribute("listRoles", roleService.findAllRole());
        return "create";
    }

    @PostMapping("admin/new")
    public String pageCreate(@RequestParam("role") ArrayList<Long> roles,
                             @ModelAttribute("user") @Valid User user) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/delete/{id}")
    public String pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit/{id}")
    public String pageEditUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("listRoles", roleService.findAllRole());
        return "edit";
    }

    @PutMapping("admin/edit")
    public String pageEdit(@RequestParam("role") ArrayList<Long> roles,
                           @Valid User user) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }
}
