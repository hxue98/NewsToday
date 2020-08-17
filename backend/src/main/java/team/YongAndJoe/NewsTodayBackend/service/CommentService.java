package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.YongAndJoe.NewsTodayBackend.dao.CommentDao;
import team.YongAndJoe.NewsTodayBackend.entity.Comment;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public List<Comment> getCommentsByNewsId(long id) {
        List<Comment> comments = commentDao.getCommentsByNewsId(id);
        for (Comment comment : comments) {
            comment.setImageUrls(commentDao.getImageUrls(comment.getId()));
        }
        return comments;
    }

    public void createComment(Comment comment) {
        commentDao.createComment(comment);
        commentDao.createCommentImageUrls(comment);
    }
}
