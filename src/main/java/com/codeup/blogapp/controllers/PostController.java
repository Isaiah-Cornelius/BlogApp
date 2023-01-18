package com.codeup.blogapp.controllers;

import com.codeup.blogapp.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {


    @GetMapping("/posts")
    public String postsGet(Model model) {
        List<Post> postsList = new ArrayList();
        postsList.add(new Post(1l, "Post 1 Title", "Post 1 Body"));
        postsList.add(new Post(2l, "Post 2 Title", "Post 2 Body"));
        model.addAttribute("posts", postsList);
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsGetId(@PathVariable long id, Model model) {
        String stringId = Long.toString(id);
        String postTitle = "Test title " + stringId;
        String postBody = "Test body " + stringId;
        Post post = new Post(id, postTitle, postBody);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsGetCreate() { return "view the form for creating a post."; }

    @PostMapping ("/posts/create")
    @ResponseBody
    public String postsPostCreate() { return "create a new post."; }
}
