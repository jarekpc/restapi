package pl.nullpointerexception.restapi.model;

import pl.nullpointerexception.restapi.dto.PostDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jazy
 */
public class PostDtoMapper {

    private PostDtoMapper(){}

    public static List<PostDto> mapToDto(List<Post> posts) {
        //zamiana post na dto
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .created(post.getCreated()).build();
    }
}
