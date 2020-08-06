package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.AccountService;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;
import team.YongAndJoe.NewsTodayBackend.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/acc")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> login(HttpServletRequest request, User user) {
        AjaxResponseBody body = new AjaxResponseBody();

        //user non exist
        if(!accountService.existByUsername(user.getUsername())) {
            body.setSuccess(false);
            body.setMsg("User Non Exist");
        } else if (accountService.login(user) == null) {
            body.setSuccess(false);
            body.setMsg("Login failed");
        } else {
            body.setSuccess(true);
            body.setJwtToken(jwtTokenUtil.generateJwt(user));
        }

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<AjaxResponseBody> register(User user) {
        AjaxResponseBody body = new AjaxResponseBody();

        if (accountService.existByUsername(user.getUsername())) {
            body.setSuccess(false);
            body.setMsg("User exists");
        } else if (accountService.register(user)){
            body.setSuccess(true);
            body.setJwtToken(jwtTokenUtil.generateJwt(user));
        }

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponseBody> logout(HttpServletRequest request) {
        AjaxResponseBody body = new AjaxResponseBody();

        String token = jwtTokenUtil.getTokenFromRequest(request);
        if (jwtTokenUtil.invalidateJwt(token)) {
            body.setSuccess(true);
        } else {
            body.setSuccess(false);
            body.setMsg("Bad request");
        }

        return ResponseEntity.ok(body);
    }
}