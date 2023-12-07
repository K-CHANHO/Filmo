package com.movie.test.complaint.service;

import com.movie.test.complaint.dto.ComplaintDTO;

public interface ComplaintService {

    ComplaintDTO registComplaint(ComplaintDTO complaintDTO);

    long getComplaintCount(String reportId);

}
