package com.movie.test.reply.controller;

import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.service.replyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class replyController {

    @Autowired
    private replyService replyService;

    @GetMapping("/getReplies/{reportId}")
    public List<replyDTO> getReplies(@PathVariable String reportId) {
        List<replyDTO> replies = replyService.getReplies(reportId);

        return replies;
    }

    @PostMapping("/registReply")
    public void registReply(replyDTO replyDTO) {

        replyService.registReply(replyDTO);
    }

    @PostMapping("/modifyReply")
    public void modifyReply(replyDTO replyDTO) {
        replyService.modifyReply(replyDTO);
    }

    @GetMapping("/deleteReply/{replyId}")
    public void deleteReply(@PathVariable String replyId){
        replyService.deleteReply(replyId);
    }
}
