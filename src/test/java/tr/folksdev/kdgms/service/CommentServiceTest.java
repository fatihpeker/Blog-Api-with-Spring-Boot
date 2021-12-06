package tr.folksdev.kdgms.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.folksdev.kdgms.dto.CreateCommentRequest;
import tr.folksdev.kdgms.model.User;
import tr.folksdev.kdgms.repository.CommentRepository;
import tr.folksdev.kdgms.security.AuthEntryPointJwt;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);


    private CommentRepository commentRepository;
    private UserService userService;
    private BlogPostService blogPostService;

    private CommentService commentService;

    @BeforeEach
    void setUp() {
        commentRepository = Mockito.mock(CommentRepository.class);
        userService = Mockito.mock(UserService.class);
        blogPostService = Mockito.mock(BlogPostService.class);

        commentService = new CommentService(commentRepository,userService,blogPostService);
    }

    @Test
    void testCreateNewComment_whenUserNotExist_shouldReturnNull(){
        CreateCommentRequest createCommentRequest = new CreateCommentRequest("remark",
                "blogpost_id",
                new User("id","username","password","name","surname"));

        Mockito.when(userService.getUserByUsername("username")).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class,
                ()->commentService.createNewComment(createCommentRequest));
        Mockito.verify(userService).getUserByUsername("username");
        Mockito.verifyNoInteractions(blogPostService);
        Mockito.verifyNoInteractions(commentRepository);
    }

//    @Test
//    void testCreateNewComment_whenBlogpostNotExist_shouldReturnNull(){
//        CreateCommentRequest createCommentRequest = new CreateCommentRequest("remark",
//                "blogpost_id",
//                new User("id","username","password","name","surname"));
//
//        Mockito.when(userService.getUserByUsername("username")).thenReturn(null);
//
//        User user = userService.getUserByUsername("username");
//    }

}