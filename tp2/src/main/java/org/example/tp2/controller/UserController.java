package org.example.tp2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping(".hello")
    public String hello(){
        return "Bienvenue dans l'API securise ";
    }
    @GetMapping(".profile")
    public String profile(
            Authentication authentication){
        return "Utilisateur connectee" + authentication.getName();
    }

}
