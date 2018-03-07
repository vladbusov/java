package main.controllers;

import main.models.User;
import main.models.Message;
import main.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiConroller {

    @PostMapping(value = "/logout", produces = "application/json")
    public Message logout(HttpSession session) {
        return UserService.loguot(session);
    }

    @GetMapping(value = "/user", produces = "application/json")
    public Message getUser(HttpSession session) {
        return UserService.getUserData(session);
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public Message register(@RequestBody User newbie, HttpSession session) {
        return UserService.registUser(newbie);
    }

    @PostMapping(value = "/signin", produces = "application/json")
    public Message authorize(@RequestBody String email, @RequestBody String password, HttpSession session) {
        return UserService.login(email, password, session);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public Message editProfile(@RequestBody User user, HttpSession session) {
        return UserService.editUser(user, session);
    }

    @GetMapping(value = "/{unknown}", produces = "application/json")
    public Message register(@PathVariable("unknown") String unknown, HttpSession session) {
        return UserService.notFound(unknown);
    }

}
