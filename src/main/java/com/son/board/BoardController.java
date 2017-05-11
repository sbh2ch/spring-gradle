package com.son.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kiost on 2017-05-11.
 */
@Controller
public class BoardController {
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("text", "hello gradle");
        return "index";
    }
}
