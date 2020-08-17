package team.YongAndJoe.NewsTodayBackend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.YongAndJoe.NewsTodayBackend.entity.Post;

import java.util.List;


@Mapper
@Repository
public interface PostDao {

    /**
     * Get a post by id
     * @param id post's id
     * @return post
     */
    Post getById(long id);

    /**
     * Get all image urls by id
     * @param id post's id
     * @return list of post image urls
     */
    List<String> getImageUrls(long id);

    /**
     * Stores a post
     * @param post post to create
     */
    void createPost(Post post);

    /**
     * Stores image urls for a post
     * @param post post whose image urls will be stored
     */
    void createPostImageUrls(Post post);

}
