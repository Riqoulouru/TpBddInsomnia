package com.example.tpbdd.posts;

import com.example.tpbdd.log.LogsController;
import com.example.tpbdd.users.Users;
import com.example.tpbdd.users.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UsersController usersController;

    @Autowired
    LogsController logsController;

    @GetMapping("/getAllPosts")
    public List<Posts> getAllPosts() {
        return this.postsRepository.findAll();
    }
    @GetMapping("/getAllPosts/{userId}")
    public List<Posts> getAllPostsUser(@PathVariable String userId) {
        return this.postsRepository.findAllByUserId(userId);
    }

    @GetMapping("/getActu/{userId}")
    public List<Posts> getActu(@PathVariable String userId) {
        List<Posts> posts = this.postsRepository.findAllByUserId(userId);
        posts.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
        return posts;
    }

    @PostMapping("/setIdUserGeneratedPost")
    public String setIdUserGeneratedPost(){
        List<Posts> posts = this.postsRepository.findAll();
        List<Users> users = this.usersController.usersRepository.findAll();
        for (Posts post : posts) {
            //random between 0 and 300
            int random = (int) (Math.random() * 300);
            post.setUserId(this.usersController.getUserById(users.get(random).getId()).getId());
            this.postsRepository.save(post);
        }
        return "Posts updated";
    }

    @PostMapping("/addPost/{userId}")
    public String addPost(@PathVariable String userId, @RequestBody String content) {
        if (this.usersController.getUserById(userId) != null) {
            Users user = this.usersController.getUserById(userId);
            Posts post = new Posts(userId, content);
            this.postsRepository.save(post);
            this.logsController.addLog(userId, user.getUsername() + " a ajouté un post");
            return "Post added";
        } else {
            return "User not found";
        }
    }

}
