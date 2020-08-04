package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.AccountService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/acc")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "getUser/{id}")
    public ResponseEntity<?> login(@PathVariable int id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @RequestMapping(value = "login")
    public boolean login(HttpServletRequest request, User user) {
        if (accountService.login(user) == null) {
            return false;
        } else {

            return true;
        }
    }

    @RequestMapping(value = "register")
    public boolean register(User user) {
        return accountService.register(user);
    }
}