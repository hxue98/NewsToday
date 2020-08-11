package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.YongAndJoe.NewsTodayBackend.dao.CommentDao;
import team.YongAndJoe.NewsTodayBackend.dao.NewsDao;
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
        news.setComments(commentDao.getCommentsByNewsId(id));
        return news;
    }

    public List<NewsPreview> getTopNewsPreviews(int top) {
        // limit to 50 news
        if (top > 50) {
            top = 50;
        }
        return newsDao.getTopNewsPreviews(top);
    }

    public List<NewsPreview> getRandomNewsPreviews(int num) {
        // limit to 50 news
        if (num > 50) {
            num = 50;
        }
        return newsDao.getRandomNewsPreviews(num);
    }

    public List<NewsPreview> getMostViewedNewsPreviews(int num) {
        // limit to 50 news
        if (num > 50) {
            num = 50;
        }
        return newsDao.getMostViewedNewsPreviews(num);
    }

}
