package tr.folksdev.kdgms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.folksdev.kdgms.dto.CommentDto;
import tr.folksdev.kdgms.dto.CreateCommentRequest;
import tr.folksdev.kdgms.model.BlogPost;
import tr.folksdev.kdgms.model.Comment;
import tr.folksdev.kdgms.model.User;
import tr.folksdev.kdgms.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final BlogPostService blogPostService;

    public CommentService(CommentRepository commentRepository, UserService userService, BlogPostService blogPostService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.blogPostService = blogPostService;
    }

    public ResponseEntity<String>createNewComment(CreateCommentRequest createCommentRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        BlogPost blogPost =  blogPostService.getPost(createCommentRequest.getBlogPost_id());
        commentRepository.save(new Comment(createCommentRequest.getRemark(),user,blogPost));
        return ResponseEntity.ok("remark created");
    }

    public  ResponseEntity<String>deleteComment(String comment_id){
        Comment comment = commentRepository.getById(comment_id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(username);
        User ownerOfPost = userService.getUserByUsername(comment.getBlogPost().getUser().getUsername());
        if (currentUser == comment.getUser() || currentUser == ownerOfPost ){
            commentRepository.delete(comment);
            return ResponseEntity.ok("comment deleted successfully");
        }
        return ResponseEntity.badRequest().body("you can not delete this comment!!!");
    }

    public ResponseEntity<List<CommentDto>>getAllComment(String blogpost_id){
        BlogPost blogPost = blogPostService.getPost(blogpost_id);
        List<Comment> commentList = commentRepository.findAllByBlogPost(blogPost);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment: commentList
             ) {
            commentDtoList.add(new CommentDto(comment.getId(),comment.getRemark(),comment.getUser().getId(),comment.getBlogPost().getId()));
        }
        return ResponseEntity.ok(commentDtoList);
    }

}
