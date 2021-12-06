package tr.folksdev.kdgms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.folksdev.kdgms.dto.CommentDto;
import tr.folksdev.kdgms.dto.CreateCommentRequest;
import tr.folksdev.kdgms.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/1.0/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<String>createNewComment(@Valid @RequestBody CreateCommentRequest createCommentRequest){
        return commentService.createNewComment(createCommentRequest);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String>deleteComment(@RequestParam(value = "comment_id") String comment_id){
        return commentService.deleteComment(comment_id);
    }

    @GetMapping(value = "get_all")
    public ResponseEntity<List<CommentDto>>getAllComment(@RequestParam(value = "blogpost_id") String blogpost_id){
        return commentService.getAllComment(blogpost_id);
    }

}
