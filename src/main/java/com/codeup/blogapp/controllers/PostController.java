package com.codeup.blogapp.controllers;

import com.codeup.blogapp.models.Post;
import com.codeup.blogapp.models.User;
import com.codeup.blogapp.repositories.PostRepository;
import com.codeup.blogapp.repositories.UserRepository;
import com.codeup.blogapp.service.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsGetId(@PathVariable long id, Model model) {
        Post post = postDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/delete", method = RequestMethod.POST)
    public String postDelete(@RequestParam(name = "id") Long id, Model model){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postsGetCreate(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping ("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User postUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(postUser);
        postDao.save(post);
        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit")
    public String postsGetEdit(@RequestParam(name = "id") Long id, Model model) {
        Post post = postDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping ("/posts/edit")
    public String editPost(@ModelAttribute Post post) {
        User postUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(postUser);
        postDao.save(post);
        return "redirect:/posts";
    }
}
