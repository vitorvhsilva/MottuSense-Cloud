package br.com.mottusense.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String login() {
        return "autenticacao/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "autenticacao/logout";
    }

}
