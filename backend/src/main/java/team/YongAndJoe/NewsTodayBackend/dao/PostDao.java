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
     * Get all image paths by id
     * @param id post's id
     * @return list of post images
     */
    List<String> getImagePaths(long id);

    /**
     * Stores a post
     * @param post post to create
     */
    void createPost(Post post);

    /**
     * Stores images for a post
     * @param post post whose images will be stored
     */
    void createPostImages(Post post);

}
