package com.codeup.blogapp.controllers;

import com.codeup.blogapp.models.Post;
import com.codeup.blogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsGetId(@PathVariable long id, Model model) {
        Post post = postDao.getReferenceById(id);
//        model.addAttribute("title", post.getTitle());
//        model.addAttribute("body", post.getBody());
//        model.addAttribute("id", post.getId());
        model.addAttribute("post", post);

//        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/fwd", method = RequestMethod.POST)
    public String postFwd(@RequestParam(name = "id") Long id, Model model){
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
    public String postsGetCreate() {
        return "/posts/create";
    }

    @PostMapping ("/posts/create")
    public String method(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }
}
