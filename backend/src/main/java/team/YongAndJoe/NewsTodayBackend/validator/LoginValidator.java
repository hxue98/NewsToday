package team.YongAndJoe.NewsTodayBackend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.AccountService;

@Component
public class LoginValidator implements Validator {

    @Autowired
    AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if(user.getUsername().length() == 0) {
            errors.rejectValue("username", "Length", "Username is required");
        } else if(user.getPassword().length() == 0) {
            errors.rejectValue("password", "Length", "Password is required");
        } else if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password is wrong");
        } else if(!accountService.existByUsername(user.getUsername())){
            errors.rejectValue("username", "exist", "Username is not exists");
        }
    }
}
