package pl.nullpointerexception.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import pl.nullpointerexception.restapi.dto.PostDto;
import pl.nullpointerexception.restapi.model.Comment;
import pl.nullpointerexception.restapi.model.Post;
import pl.nullpointerexception.restapi.model.PostDtoMapper;
import pl.nullpointerexception.restapi.service.repository.CommentRepository;
import pl.nullpointerexception.restapi.service.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jazy
 */
@Service
@AllArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPost(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public List<Post> getPostsWithComment(int page, Sort.Direction sort) {
        List<Post> allPost = postRepository.findAllPost(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPost.stream().map(Post::getId).collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPost.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPost;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream().filter(c -> c.getPostId() == id).collect(Collectors.toList());
    }

    public PostDto addPost(PostDto postDto) {
        Post post = PostDtoMapper.mapDtoToPost(postDto);
        Post save = postRepository.save(post);
        return PostDtoMapper.mapToPostDto(save);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        postEdited.setComment(post.getComment());
        return postEdited;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
