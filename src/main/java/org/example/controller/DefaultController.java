package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String redirect(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null) {
            return "redirect:/index.html";
        } else {
            return "redirect:/login.html";
        }
    }
}
