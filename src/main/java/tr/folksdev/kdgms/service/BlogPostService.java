package tr.folksdev.kdgms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.folksdev.kdgms.dto.BlogPostDto;
import tr.folksdev.kdgms.dto.CreatePostRequest;
import tr.folksdev.kdgms.dto.UpdatePostRequest;
import tr.folksdev.kdgms.model.BlogPost;
import tr.folksdev.kdgms.model.User;
import tr.folksdev.kdgms.repository.BlogPostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    private final UserService userService;

    public BlogPostService(BlogPostRepository blogPostRepository, UserService userService) {
        this.blogPostRepository = blogPostRepository;
        this.userService = userService;
    }

    public ResponseEntity<String>createNewPost(CreatePostRequest createPostRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        blogPostRepository.save(new BlogPost(createPostRequest.getPosting(), user));
        return ResponseEntity.ok("post created successfully");
    }

    public ResponseEntity<String>updatePost(UpdatePostRequest updatePostRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);
        BlogPost blogPost = blogPostRepository.getById(updatePostRequest.getId());
        if (blogPost.getUser() != user ){
            return ResponseEntity.badRequest().body("it isn't your post");
        }
        blogPostRepository.save(new BlogPost(blogPost.getId(), updatePostRequest.getPosting(),user));
        return ResponseEntity.ok("post updated successfully");
    }

    public void deletePost(String post_id){
        BlogPost blogPost = blogPostRepository.getById(post_id);
        blogPostRepository.delete(blogPost);
    }

    //Diğer servisler tarafından kullanılması için
    public BlogPost getPost(String post_id){
        return blogPostRepository.getById(post_id);
    }

    //kullanıcıya gönderilen
    public ResponseEntity<BlogPostDto>getBlogPost(String blogpost_id){
        BlogPost blogPost = blogPostRepository.findBlogPostById(blogpost_id);
        BlogPostDto blogPostDto = new BlogPostDto(blogPost.getId(),blogPost.getPosting(),blogPost.getUser().getId());
        return ResponseEntity.ok(blogPostDto);
    }

    public ResponseEntity<List<BlogPostDto>>getAllPost(){
        List<BlogPost> blogPostList = blogPostRepository.findAll();
        List<BlogPostDto> blogPostDtoList = new ArrayList<>();
        for (BlogPost post: blogPostList
             ) {
            blogPostDtoList.add(new BlogPostDto(post.getId(),post.getPosting(),post.getUser().getId()));
        }
        return ResponseEntity.ok(blogPostDtoList);
    }

    public ResponseEntity<List<BlogPostDto>>getAllByUser(String username){
        User user = userService.getUserByUsername(username);
        List<BlogPost> blogPostList = blogPostRepository.findAllByUser(user);
        List<BlogPostDto> blogPostDtoList = new ArrayList<>();
        for (BlogPost post: blogPostList
        ) {
            blogPostDtoList.add(new BlogPostDto(post.getId(),post.getPosting(),post.getUser().getId()));
        }
        return ResponseEntity.ok(blogPostDtoList);
    }

}
