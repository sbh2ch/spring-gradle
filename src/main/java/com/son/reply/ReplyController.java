package com.son.reply;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kiost on 2017-05-17.
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/replyListAjax")
    public void replyListAjax(HttpServletResponse res, ReplyVo replyVo) {
        try {
            res.getWriter().print(new ObjectMapper().writeValueAsString(replyService.selectReplyListByBrdNo(replyVo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/replySaveAjax")
    public void replySaveAjax(HttpServletResponse res, ReplyVo replyVo) {
        System.out.println(replyVo);
        try {
            res.getWriter().print(new ObjectMapper().writeValueAsString(replyService.insertReply(replyVo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/replyDeleteAjax")
    public void replyDeleteAjax(HttpServletResponse res, ReplyVo replyVo) {
        try {
            res.getWriter().print(new ObjectMapper().writeValueAsString(replyService.deleteReply(replyVo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/replyUpdateAjax")
    public void replyUpdateAjax(HttpServletResponse res, ReplyVo replyVo) {
        try {
            res.getWriter().print(new ObjectMapper().writeValueAsString(replyService.updateReply(replyVo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
