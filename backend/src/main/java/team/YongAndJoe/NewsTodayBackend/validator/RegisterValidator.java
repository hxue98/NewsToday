package team.YongAndJoe.NewsTodayBackend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import team.YongAndJoe.NewsTodayBackend.config.ErrorMessageConfig;
import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.AccountService;

@Component
public class RegisterValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ErrorMessageConfig errorMessageConfig;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if(user.getUsername().length() == 0) {
            errors.rejectValue("username", "Length", errorMessageConfig.getRequireUsername());
        } else if(user.getPassword().length() == 0) {
            errors.rejectValue("password", "Length", errorMessageConfig.getRequirePassword());
        } else if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", errorMessageConfig.getPasswordTooShort());
        }
    }
}
