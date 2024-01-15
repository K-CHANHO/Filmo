package com.movie.test.complaint.service;

import com.movie.test.complaint.dto.ComplaintDTO;
import com.movie.test.complaint.entity.ComplaintEntity;
import com.movie.test.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public ComplaintDTO registComplaint(ComplaintDTO complaintDTO) {

        complaintDTO.setComplaintId(UUID.randomUUID().toString());

        ComplaintEntity complaint = ComplaintDTO.toEntity(complaintDTO);
        ComplaintEntity savedComplaint = complaintRepository.save(complaint);

        return ComplaintDTO.toDTO(savedComplaint);
    }

    @Override
    public long getComplaintCount(String reportId) {

        long complaintCount = complaintRepository.countByReportId(reportId);

        return complaintCount;
    }
}
