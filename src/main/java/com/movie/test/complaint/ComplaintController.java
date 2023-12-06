package com.movie.test.complaint;

import com.movie.test.complaint.dto.ComplaintDTO;
import com.movie.test.complaint.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/complaint/regist")
    public String registComplaint(ComplaintDTO complaintDTO) {

        ComplaintDTO registedComplaint = complaintService.registComplaint(complaintDTO);

        return registedComplaint.getComplaintId();

    }
}
