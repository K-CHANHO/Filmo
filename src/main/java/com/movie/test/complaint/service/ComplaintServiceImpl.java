package com.movie.test.complaint.service;

import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.entity.ComplaintEntity;
import com.movie.test.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public ComplaintDto registComplaint(ComplaintDto complaintDTO) {

        // 동일한 사용자가 동일한 게시물 중복 신고 방지.
        ComplaintEntity complaint = complaintRepository.findByUserIdAndReportId(complaintDTO.getUserId(), complaintDTO.getReportId());

        if(complaint == null){
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
            stringBuilder.append(UUID.randomUUID().toString());

            complaintDTO.setComplaintId(stringBuilder.toString());
            complaint = complaintRepository.save(ComplaintDto.toEntity(complaintDTO));
        }

        return ComplaintDto.toDTO(complaint);
    }

    @Override
    public long getComplaintCount(String reportId) {

        long complaintCount = complaintRepository.countByReportId(reportId);

        return complaintCount;
    }

    @Override
    public void deleteComplaintByReportId(String reportId) {
        complaintRepository.deleteByReportId(reportId);
    }

    @Override
    public void deleteById(String userId, String reportId) {
        complaintRepository.deleteByUserIdAndReportId(userId, reportId);
    }
}
