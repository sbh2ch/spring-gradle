package com.son.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kiost on 2017-05-11.
 */
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/boardList.kosc ")
    public String list(Model model) {
        model.addAttribute("listView",service.selectBoardList());
        return "list";
    }

    @RequestMapping("/boardRead.kosc")
    public String boardRead(Model model, HttpServletRequest req){
        String brdNo = req.getParameter("brdno");
        model.addAttribute("listView",service.readBoardOne(brdNo));
        return "read";
    }
}
