package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import team.YongAndJoe.NewsTodayBackend.entity.Comment;
import team.YongAndJoe.NewsTodayBackend.service.CommentService;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        AjaxResponseBody body;

        commentService.createComment(comment);

        body = AjaxResponseBody.SUCCESS(null, null);

        return ResponseEntity.ok(body);
    }
}
