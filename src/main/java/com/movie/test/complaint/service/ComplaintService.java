package com.movie.test.complaint.service;

import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.dto.ComplaintSaveDto;

public interface ComplaintService {

    ComplaintDto saveComplaint(ComplaintSaveDto complaintSaveDto);

    long getComplaintCount(String reportId);

    void deleteComplaintByReportId(String reportId);

    void deleteById(String userId, String reportId);
}
