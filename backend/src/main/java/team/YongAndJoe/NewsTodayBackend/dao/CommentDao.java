package team.YongAndJoe.NewsTodayBackend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.YongAndJoe.NewsTodayBackend.entity.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    /**
     * Get all comments by newsId
     * @param newsId news's id
     * @return all comments under that news
     */
    List<Comment> getCommentsByNewsId(long newsId);

    /**
     * Get all image paths by id
     * @param id comment's id
     * @return list of comment images
     */
    List<String> getImageUrls(long id);

    /**
     * Stores a comment
     * @param comment comment to create
     */
    void createComment(Comment comment);

    /**
     * Stores image urls for a comment
     * @param comment comment image urls to create
     */
    void createCommentImageUrls(Comment comment);
}
