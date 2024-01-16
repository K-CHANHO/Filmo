package com.movie.test.report.complaint.service;

import com.movie.test.report.complaint.dto.ComplaintDTO;

public interface ComplaintService {

    ComplaintDTO registComplaint(ComplaintDTO complaintDTO);

    long getComplaintCount(String reportId);

    void deleteComplaintByReportId(String reportId);

    void deleteById(String userId, String reportId);
}
