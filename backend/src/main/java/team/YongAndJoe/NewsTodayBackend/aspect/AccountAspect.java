package team.YongAndJoe.NewsTodayBackend.aspect;

import org.apache.commons.codec.binary.Hex;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import team.YongAndJoe.NewsTodayBackend.entity.User;
import team.YongAndJoe.NewsTodayBackend.service.MapValidationErrorService;
import team.YongAndJoe.NewsTodayBackend.validator.LoginValidator;
import team.YongAndJoe.NewsTodayBackend.validator.RegisterValidator;

@Aspect
@Configuration
public class AccountAspect {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private LoginValidator loginValidator;

    @Before("@annotation(team.YongAndJoe.NewsTodayBackend.annotation.HashPassword)")
    public void hashPassword(JoinPoint joinPoint) throws Throwable {
        User user = (User)joinPoint.getArgs()[0];
        user.setPassword(Hex.encodeHexString(user.getPassword().getBytes()));
    }

    @Around("@annotation(team.YongAndJoe.NewsTodayBackend.annotation.ValidateUser)")
    public Object validateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        User user = (User)joinPoint.getArgs()[0];
        BindingResult result = (BindingResult)joinPoint.getArgs()[1];
        loginValidator.validate(user, result);
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap == null) {
            return joinPoint.proceed();
        }
        else {
            return errorMap;
        }
    }
}
