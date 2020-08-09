package team.YongAndJoe.NewsTodayBackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.error-msgs")
public class ErrorMessageConfig {

    private String requireAuthentication;
    private String userExist;
    private String userNotExist;
    private String loginFailed;
    private String badRequest;
    private String requireUsername;
    private String requireEmail;
    private String requirePassword;
    private String passwordTooShort;
    private String emailFormatInvalid;

    public String getRequireAuthentication() {
        return requireAuthentication;
    }

    public void setRequireAuthentication(String requireAuthentication) {
        this.requireAuthentication = requireAuthentication;
    }

    public String getUserExist() {
        return userExist;
    }

    public void setUserExist(String userExist) {
        this.userExist = userExist;
    }

    public String getUserNotExist() {
        return userNotExist;
    }

    public void setUserNotExist(String userNotExist) {
        this.userNotExist = userNotExist;
    }

    public String getLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(String loginFailed) {
        this.loginFailed = loginFailed;
    }

    public String getBadRequest() {
        return badRequest;
    }

    public void setBadRequest(String badRequest) {
        this.badRequest = badRequest;
    }

    public String getRequireUsername() {
        return requireUsername;
    }

    public void setRequireUsername(String requireUsername) {
        this.requireUsername = requireUsername;
    }

    public String getRequirePassword() {
        return requirePassword;
    }

    public void setRequirePassword(String requirePassword) {
        this.requirePassword = requirePassword;
    }

    public String getPasswordTooShort() {
        return passwordTooShort;
    }

    public void setPasswordTooShort(String passwordTooShort) {
        this.passwordTooShort = passwordTooShort;
    }

    public String getRequireEmail() {
        return requireEmail;
    }

    public void setRequireEmail(String requireEmail) {
        this.requireEmail = requireEmail;
    }

    public String getEmailFormatInvalid() {
        return emailFormatInvalid;
    }

    public void setEmailFormatInvalid(String emailFormatInvalid) {
        this.emailFormatInvalid = emailFormatInvalid;
    }
}