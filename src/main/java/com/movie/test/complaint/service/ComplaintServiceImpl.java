package com.movie.test.complaint.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.dto.ComplaintSaveDto;
import com.movie.test.complaint.entity.ComplaintEntity;
import com.movie.test.complaint.mapper.ComplaintSaveMapper;
import com.movie.test.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public ComplaintDto saveComplaint(ComplaintSaveDto complaintSaveDto) {

        // 동일한 사용자가 동일한 게시물 중복 신고 방지.
        ComplaintEntity complaint = complaintRepository.findByUserIdAndTargetId(complaintSaveDto.getUserId(), complaintSaveDto.getTargetId());

        if(complaint == null){
            ComplaintDto complaintDto = ComplaintSaveMapper.INSTANCE.toMetaDto(complaintSaveDto);
            complaintDto.setComplaintId(CustomUUID.createUUID());
            complaint = complaintRepository.save(ComplaintDto.toEntity(complaintDto));
        }

        return ComplaintDto.toDTO(complaint);
    }

    @Override
    public long getComplaintCount(String reportId) {

        long complaintCount = complaintRepository.countByTargetId(reportId);

        return complaintCount;
    }

    @Override
    public void deleteComplaintByReportId(String reportId) {
        complaintRepository.deleteByTargetId(reportId);
    }

    @Override
    public void deleteById(String userId, String reportId) {
        complaintRepository.deleteByUserIdAndTargetId(userId, reportId);
    }
}
