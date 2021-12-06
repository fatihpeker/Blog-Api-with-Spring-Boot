package tr.folksdev.kdgms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.folksdev.kdgms.dto.BlogPostDto;
import tr.folksdev.kdgms.dto.CreatePostRequest;
import tr.folksdev.kdgms.dto.UpdatePostRequest;
import tr.folksdev.kdgms.model.BlogPost;
import tr.folksdev.kdgms.service.BlogPostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/1.0/blogpost")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<String>createNewPost(@Valid @RequestBody CreatePostRequest createPostRequest){
        return blogPostService.createNewPost(createPostRequest);
    }

    @PutMapping(value = "update")
    public ResponseEntity<String>updatePost(@Valid @RequestBody UpdatePostRequest updatePostRequest){
        return blogPostService.updatePost(updatePostRequest);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String>deletePost(@RequestParam(value = "post_id")String post_id){
        blogPostService.deletePost(post_id);
        return ResponseEntity.ok("post deleted");
    }

    @GetMapping(value = "get_all")
    public ResponseEntity<List<BlogPostDto>>getAllBlogPost(){
        return blogPostService.getAllPost();
    }

    @GetMapping(value = "get_one")
    public ResponseEntity<BlogPostDto>getOneBlogPost(@RequestParam(value = "blogpost_id") String blogpost_id){
        return blogPostService.getBlogPost(blogpost_id);
    }

    @GetMapping(value = "get_by_user")
    public ResponseEntity<List<BlogPostDto>>getAllPostByUser(@RequestParam(value = "username") String username){
        return blogPostService.getAllByUser(username);
    }

}
