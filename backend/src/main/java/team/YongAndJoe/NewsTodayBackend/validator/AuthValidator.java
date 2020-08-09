package team.YongAndJoe.NewsTodayBackend.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import team.YongAndJoe.NewsTodayBackend.config.ErrorMessageConfig;
import team.YongAndJoe.NewsTodayBackend.entity.User;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthValidator implements Validator {

    @Autowired
    private ErrorMessageConfig errorMessageConfig;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if(Objects.nonNull(user.getUsername()) && user.getUsername().length() == 0) {
            errors.rejectValue("username", "Length", errorMessageConfig.getRequireUsername());
        } else if(user.getEmail().length() == 0) {
            errors.rejectValue("email", "Length", errorMessageConfig.getRequireEmail());
        } else if(user.getPassword().length() == 0) {
            errors.rejectValue("password", "Length", errorMessageConfig.getRequirePassword());
        } else if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", errorMessageConfig.getPasswordTooShort());
        } else if(!check(user.getEmail())) {
            errors.rejectValue("email", "Length", errorMessageConfig.getEmailFormatInvalid());
        }
    }

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private boolean check(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}