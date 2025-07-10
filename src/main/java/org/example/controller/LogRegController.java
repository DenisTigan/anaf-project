package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LogRegController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.register(username, password);
        return success ? "Utilizator creat cu succes!" : "Utilizatorul există deja!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");

        if (userService.login(username, password)) {
            session.setAttribute("user", username);
            return ResponseEntity.ok("Autentificare reușită!");
        } else {
            return ResponseEntity.status(401).body("Date incorecte!");
        }
    }


    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Delogat.";
    }

    @GetMapping("/check")
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}