package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.YongAndJoe.NewsTodayBackend.dao.PostDao;
import team.YongAndJoe.NewsTodayBackend.entity.Post;

@Service
public class PostService {

    @Autowired
    PostDao postDao;

    public Post getById(long id) {
        Post post = postDao.getById(id);
        post.setImages(postDao.getImagePaths(id));
        return post;
    }

    public void createPost(Post post) {
        postDao.createPost(post);
        postDao.createPostImages(post);
    }
}
