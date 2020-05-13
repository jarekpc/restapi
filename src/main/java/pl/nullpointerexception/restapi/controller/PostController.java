package pl.nullpointerexception.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Post> getPostWithComment(@RequestParam(required = false) int page, Sort.Direction sort) {
        return postService.getPostsWithComment(page, sort);
    }

    @GetMapping("/posts")
    public List<PostDto> getPost(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        return PostDtoMapper.mapToDto(postService.getPosts(pageNumber, sort));
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }

}
