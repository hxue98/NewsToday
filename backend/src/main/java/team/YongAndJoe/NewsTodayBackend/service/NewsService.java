package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.YongAndJoe.NewsTodayBackend.dao.CommentDao;
import team.YongAndJoe.NewsTodayBackend.dao.NewsDao;
import team.YongAndJoe.NewsTodayBackend.entity.Comment;
import team.YongAndJoe.NewsTodayBackend.entity.News;
import team.YongAndJoe.NewsTodayBackend.entity.NewsPreview;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private CommentDao commentDao;

    public News getById(long id) {
        newsDao.increaseNumClick(id);
        News news = newsDao.getById(id);
        List<Comment> comments = commentDao.getCommentsByNewsId(id);
        for (Comment comment: comments) {
            comment.setImageUrls(commentDao.getImageUrls(comment.getId()));
        }
        news.setComments(comments);
        news.setImageUrl(newsDao.getImageUrls(id));
        return news;
    }

    public List<NewsPreview> getTopNewsPreviews(long categoryId, int num) {
        // limit to 50 news
        if (num > 50) {
            num = 50;
        }
        List<NewsPreview> previews = newsDao.getTopNewsPreviews(categoryId, num);
        for (NewsPreview preview : previews) {
            preview.setImageUrl(newsDao.getImageUrls(preview.getId()));
        }
        return previews;
    }

    public List<NewsPreview> getRandomNewsPreviews(long categoryId, int num) {
        // limit to 50 news
        if (num > 50) {
            num = 50;
        }
        List<NewsPreview> previews = newsDao.getRandomNewsPreviews(categoryId, num);
        for (NewsPreview preview : previews) {
            preview.setImageUrl(newsDao.getImageUrls(preview.getId()));
        }
        return previews;
    }

    public List<NewsPreview> getMostViewedNewsPreviews(int num) {
        // limit to 50 news
        if (num > 50) {
            num = 50;
        }
        List<NewsPreview> previews = newsDao.getMostViewedNewsPreviews(num);
        for (NewsPreview preview : previews) {
            preview.setImageUrl(newsDao.getImageUrls(preview.getId()));
        }
        return previews;
    }

}
