package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import team.YongAndJoe.NewsTodayBackend.annotation.HashPassword;
import team.YongAndJoe.NewsTodayBackend.annotation.ValidateUser;
import team.YongAndJoe.NewsTodayBackend.config.ErrorMessageConfig;
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
    private ErrorMessageConfig errorMessageConfig;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/login")
    @HashPassword
    @ValidateUser
    public ResponseEntity<?> login(@RequestBody User user, BindingResult result) {
        AjaxResponseBody body;

        if (!accountService.existByUsername(user.getUsername())) {
            body = AjaxResponseBody.FAIL(errorMessageConfig.getUserNotExist(), null);
        } else if (accountService.login(user) == null) {
            body = AjaxResponseBody.FAIL(errorMessageConfig.getLoginFailed(), null);
        } else {
            body = AjaxResponseBody.SUCCESS(null, null, jwtTokenUtil.generateJwt(user));
        }

        return ResponseEntity.ok(body);
    }

    @PostMapping("/register")
    @HashPassword
    @ValidateUser
    public ResponseEntity<?> register(@RequestBody User user, BindingResult result) {
        AjaxResponseBody body;

        if (accountService.existByUsername(user.getUsername())) {
            body = AjaxResponseBody.FAIL(errorMessageConfig.getUserExist(), null);
        } else if (accountService.register(user)){
            body = AjaxResponseBody.SUCCESS(null, null, jwtTokenUtil.generateJwt(user));
        } else {
            body = AjaxResponseBody.FAIL(errorMessageConfig.getBadRequest(), null);
        }

        return ResponseEntity.ok(body);
    }

    @GetMapping("/logout")
    public ResponseEntity<AjaxResponseBody> logout(HttpServletRequest request) {
        AjaxResponseBody body;

        String token = jwtTokenUtil.getTokenFromRequest(request);
        if (jwtTokenUtil.invalidateJwt(token)) {
            body = AjaxResponseBody.SUCCESS("", null);
        } else {
            body = AjaxResponseBody.FAIL(errorMessageConfig.getBadRequest(), null);
        }

        return ResponseEntity.ok(body);
    }
}