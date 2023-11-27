package com.movie.test.reply.controller;

import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.service.replyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class replyController {

    @Autowired
    replyService replyService;

    @PostMapping("/registReply")
    public void registReply(replyDTO replyDTO) {

        replyService.registReply(replyDTO);
    }

    @PostMapping("/modifyReply")
    public void modifyReply(replyDTO replyDTO) {
        replyService.modifyReply(replyDTO);
    }

    @GetMapping("deleteReply")
    public void deleteReply(String replyId){
        replyService.deleteReply(replyId);
    }
}
