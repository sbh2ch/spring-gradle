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
        model.addAttribute("listView", service.selectBoardList());
        return "list";
    }

    @RequestMapping("/boardRead.kosc")
    public String boardRead(Model model, BoardVo boardVo) {
        model.addAttribute("boardInfo", service.readBoardOne(boardVo.getBrdNo()));
        model.addAttribute("listView",service.selectFileList(boardVo.getBrdNo()));
        return "read";
    }

    @RequestMapping("/boardWriteForm.kosc")
    public String boardForm(Model model, HttpServletRequest req) {
        String brdNo = req.getParameter("brdNo");
        model.addAttribute("boardInfo", service.readBoardOne(brdNo));
        model.addAttribute("listView",service.selectFileList(brdNo));

        return "writeForm";
    }

    @RequestMapping("/boardSave.kosc")
    public String boardSave(Model model, HttpServletRequest req, BoardVo boardVo){
        String[] fileNo = req.getParameterValues("fileNo");
        service.writeBoard(boardVo, fileNo);

        return "redirect:/board/boardList.kosc";
    }

    @RequestMapping("/boardDelete.kosc")
    public String boardDelete(BoardVo boardVo){
        service.deleteBoard(boardVo);

        return "redirect:boardList.kosc";
    }


}
