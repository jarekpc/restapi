package pl.nullpointerexception.restapi.service.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.nullpointerexception.restapi.model.Post;

import java.util.List;

/**
 * @author jazy
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p")
    List<Post> findAllPost(Pageable page);
}
