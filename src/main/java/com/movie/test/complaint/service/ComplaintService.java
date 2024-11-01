package com.movie.test.complaint.service;

import com.movie.test.complaint.dto.ComplaintDto;

public interface ComplaintService {

    ComplaintDto registComplaint(ComplaintDto complaintDTO);

    long getComplaintCount(String reportId);

    void deleteComplaintByReportId(String reportId);

    void deleteById(String userId, String reportId);
}
