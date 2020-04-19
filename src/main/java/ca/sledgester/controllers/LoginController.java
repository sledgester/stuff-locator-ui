package ca.sledgester.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sledgester on 2016-05-27.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {

        return "login";

    }
}
