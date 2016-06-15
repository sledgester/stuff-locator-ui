package ca.sledgester.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sledgester on 2016-05-27.
 */
@Controller
public class MainMenuController {
    @RequestMapping("/mainMenu")
    public String mainMenu() {
        return "mainMenu";
    }
}
