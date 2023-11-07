package edu.whu.demo.controller;

import edu.whu.demo.domain.User;
import edu.whu.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('user/query')")
    public List<User> findUser() {return userService.findall();}

    @PostMapping
    @PreAuthorize("hasAuthority('user/update')")
    public User addUser(@RequestBody User user){return userService.addUser(user);}

    @DeleteMapping
    @PreAuthorize("hasAuthority('user/update')")
    public void deleteUser(@PathVariable String userName){userService.deleteUser(userName);}

}
