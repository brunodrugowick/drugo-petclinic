package com.drugowick.drugopetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "", "index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/oups")
    public String error() {
        throw new RuntimeException("This is a simulated error. " +
                "The cat and the dog above are sad because you couldn't find what you're looking for.");
    }
}
