package pl.nullpointerexception.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexception.restapi.dto.PostDto;
import pl.nullpointerexception.restapi.model.Post;
import pl.nullpointerexception.restapi.model.PostDtoMapper;
import pl.nullpointerexception.restapi.service.PostService;

import java.util.List;

/**
 * @author jazy
 */
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/comments")
    public List<Post> getPostWithComment(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComment(pageNumber, sortDirection);
    }

    @GetMapping("/posts")
    public List<PostDto> getPost(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToDto(postService.getPosts(pageNumber, sortDirection));
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }

    @PostMapping("/posts")
    public PostDto addPost(@RequestBody PostDto postDto) {
        return postService.addPost(postDto);

    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);

    }
}
