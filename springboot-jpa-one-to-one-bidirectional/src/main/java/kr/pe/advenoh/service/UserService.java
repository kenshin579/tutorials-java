package kr.pe.advenoh.service;

import kr.pe.advenoh.exception.ResourceNotFoundException;
import kr.pe.advenoh.model.User;
import kr.pe.advenoh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User modifyUser(Long postId, User post) {
        //todo : 검색을 해서 저장을 해야 하는지 아니면 그냥 저장하면 같은 Post에 저장이 되나?
//
//        User foundPost = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
//        foundPost.setTitle(post.getTitle());
//        foundPost.setAuthor(post.getAuthor());
//        foundPost.setContent(post.getContent());
//        foundPost.setLikeCount(post.getLikeCount());
//        return postRepository.save(foundPost);
        return null;
    }

    @Transactional
    public void deleteUser(Long postId) {
        User foundUser = userRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
        userRepository.delete(foundUser);
    }

}
