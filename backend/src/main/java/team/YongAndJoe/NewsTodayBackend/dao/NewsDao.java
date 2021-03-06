package team.YongAndJoe.NewsTodayBackend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.YongAndJoe.NewsTodayBackend.entity.News;
import team.YongAndJoe.NewsTodayBackend.entity.NewsPreview;

import java.util.List;

@Mapper
@Repository
public interface NewsDao extends CrudRepository<News, Long> {

    /**
     * Get a news by id
     * @param id news's id
     * @return news
     */
    News getById(long id);

    /**
     * Increase the number of clicks of a news by 1
     * @param id news's id
     */
    void increaseNumClick(long id);

    /**
     * Get top news
     * @param id category id
     * @param top number of newest news
     * @return list of newest news
     */
    List<NewsPreview> getTopNewsPreviews(long id, int top);

    /**
     * Get random news
     * @param id category id
     * @param num number of random news
     * @return list of news
     */
    List<NewsPreview> getRandomNewsPreviews(long id, int num);

    /**
     * Get most viewed news in past 24 hr
     * @param num number of news to retrieve
     * @return list of news
     */
    List<NewsPreview> getMostViewedNewsPreviews(int num);

    /**
     * Get all image urls by id
     * @param id news's id
     * @return list of news image urls
     */
    List<String> getImageUrls(long id);
}
