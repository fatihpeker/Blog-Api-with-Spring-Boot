package tr.folksdev.kdgms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.folksdev.kdgms.model.BlogPost;
import tr.folksdev.kdgms.model.User;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, String> {

    BlogPost findBlogPostById(String blogpost_id);

    List<BlogPost> findAllByUser(User user);

}
