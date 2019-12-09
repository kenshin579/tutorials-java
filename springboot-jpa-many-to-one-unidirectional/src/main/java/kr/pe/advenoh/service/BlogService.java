package kr.pe.advenoh.service;

import kr.pe.advenoh.exception.ResourceNotFoundException;
import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.model.Post;
import kr.pe.advenoh.repository.CommentRepository;
import kr.pe.advenoh.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post modifyPost(Long postId, Post post) {
        //todo : 검색을 해서 저장을 해야 하는지 아니면 그냥 저장하면 같은 Post에 저장이 되나?

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        foundPost.setTitle(post.getTitle());
        foundPost.setAuthor(post.getAuthor());
        foundPost.setContent(post.getContent());
        foundPost.setLikeCount(post.getLikeCount());
        return postRepository.save(foundPost);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.delete(foundPost);
    }

    @Transactional
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment modifyComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        foundComment.setAuthor(comment.getAuthor());
        foundComment.setContent(comment.getContent());
        return commentRepository.save(foundComment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.delete(foundComment);
    }


}
