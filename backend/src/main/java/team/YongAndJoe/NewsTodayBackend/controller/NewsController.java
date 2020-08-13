package team.YongAndJoe.NewsTodayBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import team.YongAndJoe.NewsTodayBackend.service.NewsService;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/id")
    public ResponseEntity<?> getNewsById(@RequestParam("id") long id) {
        AjaxResponseBody body;

        body = AjaxResponseBody.SUCCESS(null, newsService.getById(id));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/topPreviews")
    public ResponseEntity<?> getTopNewsPreviews(@RequestParam("categoryId") long categoryId, @RequestParam("num") int num) {
        AjaxResponseBody body;

        body = AjaxResponseBody.SUCCESS(null, newsService.getTopNewsPreviews(categoryId, num));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/randomPreviews")
    public ResponseEntity<?> getRandomNewsPreviews(@RequestParam("categoryId") long categoryId, @RequestParam("num") int num) {
        AjaxResponseBody body;

        body = AjaxResponseBody.SUCCESS(null, newsService.getRandomNewsPreviews(categoryId, num));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/mostViewedPreviews")
    public ResponseEntity<?> getMostViewedNewsPreviews(@RequestParam("num") int num) {
        AjaxResponseBody body;

        body = AjaxResponseBody.SUCCESS(null, newsService.getMostViewedNewsPreviews(num));

        return ResponseEntity.ok(body);
    }
}
