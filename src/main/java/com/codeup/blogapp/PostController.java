package com.codeup.blogapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsGet() { return "posts index page."; }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postsGetId() { return "view an individual post."; }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsGetCreate() { return "view the form for creating a post."; }

    @PostMapping ("/posts/create")
    @ResponseBody
    public String postsPostCreate() { return "create a new post."; }
}
