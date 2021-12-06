package tr.folksdev.kdgms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.folksdev.kdgms.model.BlogPost;
import tr.folksdev.kdgms.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,String> {

    List<Comment> findAllByBlogPost(BlogPost blogPost);

}
