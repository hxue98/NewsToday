package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.AccountService;
import team.YongAndJoe.NewsTodayBackend.service.MapValidationErrorService;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;
import team.YongAndJoe.NewsTodayBackend.util.JwtTokenUtil;
import team.YongAndJoe.NewsTodayBackend.validator.LoginValidator;
import team.YongAndJoe.NewsTodayBackend.validator.RegisterValidator;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/acc")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private LoginValidator loginValidator;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, BindingResult result) {

        loginValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        AjaxResponseBody body = new AjaxResponseBody();
        body.setSuccess(true);
        body.setJwtToken(jwtTokenUtil.generateJwt(user));

        return ResponseEntity.ok(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user, BindingResult result) {
        // password length must greater than 6 characters
        //username and password is required
        registerValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null) return errorMap;

        //create user
        AjaxResponseBody body = new AjaxResponseBody();
        if(accountService.register(user)){
            body.setSuccess(true);
            body.setJwtToken(jwtTokenUtil.generateJwt(user));
        }

        return new ResponseEntity<AjaxResponseBody>(body, HttpStatus.CREATED);
    }

    @GetMapping("/logout")
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