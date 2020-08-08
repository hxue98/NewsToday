package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import team.YongAndJoe.NewsTodayBackend.dao.UserDao;
import team.YongAndJoe.NewsTodayBackend.entity.User;

@Service
public class AccountService {

    @Autowired
    private UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    public boolean existByUsername(String username) {
        return userDao.existByUsername(username);
    }

    public User login(User user) {
        return userDao.login(user);
    }

    public boolean register(User user) {
        return userDao.register(user);
    }

}
