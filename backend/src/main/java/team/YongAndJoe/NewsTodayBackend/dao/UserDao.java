package team.YongAndJoe.NewsTodayBackend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team.YongAndJoe.NewsTodayBackend.entity.User;

@Mapper
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    /**
     * Get a user by id
     * @param id user's id
     * @return user
     */
    public User getById(int id);

    /**
     * Check if a user exist by email
     * @param email email to check
     * @return true if email exists, false otherwise
     */
    public boolean existByEmail(String email);

    /**
     * Login using username and password
     * @param user username and password
     * @return user if username and password are correct, null otherwise
     */
    public User login(User user);

    /**
     * Register a new user
     * @param user email and password
     * @return true if user does not exist, false otherwise
     */
    public boolean register(User user);
}
