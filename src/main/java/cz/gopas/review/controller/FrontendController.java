package cz.gopas.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/private")
    public String getGreeting() {
        return "index";
    }

}
