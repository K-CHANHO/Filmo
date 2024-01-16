package com.movie.test.report;

import com.movie.test.report.complaint.service.ComplaintService;
import com.movie.test.report.hashtag.service.TagService;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportCompactServiceImpl implements ReportCompactService{

    private final ReportService reportService;
    private final ReplyService replyService;
    private final TagService tagService;
    private final ComplaintService complaintService;


    /**
     * 감상문 등록
     * 1. 감상문 저장
     * 2. 감상문에 사용된 태그 저장
     */
    @Override
    public String registReport(ReportDTO reportDTO) {

        String reportId = reportService.registReport(reportDTO);
        tagService.saveTags(reportId, reportDTO.getTagString());

        return reportId;
    }

    /**
     * 감상문 수정
     * 1. 감상문 수정
     * 2. 태그 수정
     */
    @Override
    public String modifyReport(ReportDTO reportDTO) {

        // 감상문 수정
        String reportId = reportService.modifyReport(reportDTO);

        // 태그 수정
        // 먼저 TagInReport 삭제 후 다시 저장
        tagService.deleteTagInReport(reportDTO.getReportId());
        tagService.saveTags(reportDTO.getReportId(), reportDTO.getTagString());

        return reportId;
    }

    /**
     * 감상문 삭제
     * 1. 감상문 삭제
     * 2. 댓글 삭제
     * 3. 태그 삭제
     * 4. 신고 삭제
     * @param reportId
     */
    @Override
    public void deleteReport(String reportId) {

        reportService.deleteReport(reportId);
        replyService.deleteRepliesByReportId(reportId);
        tagService.deleteTagInReport(reportId);
        complaintService.deleteComplaintByReportId(reportId);

    }
}
