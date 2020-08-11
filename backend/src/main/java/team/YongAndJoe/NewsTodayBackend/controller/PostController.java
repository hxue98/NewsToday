package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import team.YongAndJoe.NewsTodayBackend.entity.Post;
import team.YongAndJoe.NewsTodayBackend.service.PostService;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/id")
    public ResponseEntity<?> getNewsById(@RequestParam("id") long id) {
        AjaxResponseBody body;

        body = AjaxResponseBody.SUCCESS(null, postService.getById(id));

        return ResponseEntity.ok(body);
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        AjaxResponseBody body;

        postService.CreatePost(post);

        body = AjaxResponseBody.SUCCESS(null, null);

        return ResponseEntity.ok(body);
    }
}
