package com.baeldung.modelmapper;

import static org.junit.Assert.assertEquals;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.baeldung.modelmapper.dto.PostDto;
import com.baeldung.modelmapper.model.Post;

@Slf4j
public class PostDtoUnitTest {
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() {
        Post post = new Post();
        post.setId(Long.valueOf(1));
        post.setTitle(randomAlphabetic(6));
        post.setUrl("www.test.com");
 
        PostDto postDto = modelMapper.map(post, PostDto.class);
        log.info("postDto: {}", postDto.toString());

        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getUrl(), postDto.getUrl());
    }
 
    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        PostDto postDto = new PostDto();
        postDto.setId(Long.valueOf(1));
        postDto.setTitle(randomAlphabetic(6));
        postDto.setUrl("www.test.com");
 
        Post post = modelMapper.map(postDto, Post.class);
        assertEquals(postDto.getId(), post.getId());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getUrl(), post.getUrl());
    }
}