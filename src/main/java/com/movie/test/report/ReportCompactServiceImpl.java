package com.movie.test.report;

import com.movie.test.report.complaint.service.ComplaintService;
import com.movie.test.report.hashtag.service.TagService;
import com.movie.test.report.like.service.LikeService;
import com.movie.test.report.reply.dto.ReplyDTO;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.service.ReportService;
import com.movie.test.report.view.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportCompactServiceImpl implements ReportCompactService{

    private final ReportService reportService;
    private final ReplyService replyService;
    private final TagService tagService;
    private final ComplaintService complaintService;
    private final LikeService likeService;
    private final ViewService viewService;


    /**
     * 감상문 등록
     * 1. 감상문 저장
     * 2. 감상문에 사용된 태그 저장
     * 3. 조회수 데이터 생성
     */
    @Override
    public String registReport(ReportDTO reportDTO) {

        String reportId = reportService.registReport(reportDTO);
        tagService.saveTags(reportId, reportDTO.getTagString());
        viewService.addViewCount(reportId);

        return reportId;
    }

    /**
     * 감상문 리스트 조회
     * 1. 감상문 Id 리스트 조회
     * 2. 감상문 Id 리스트를 통해 각각의 감상문 정보 조회
     */
    @Override
    public Slice<ReportDTO> getReportList(ReportListSearchDTO reportListSearchDTO, Pageable pageable) {

        // reportId를 구한 뒤 getSingleReport로 각각 구해오기?
        Slice<String> searchReportId = reportService.getSearchReportId(reportListSearchDTO, pageable);

        // 구현해놓은 getSingleReport(단건 조회) 이용 TODO: 조회수 처리는 어떻게 할 것인지 고민 필요. 리스트를 데이터를 위한 로직 추가 고려.
        Slice<ReportDTO> searchReportDTO = searchReportId.map(this::getSingleReport);

        return searchReportDTO;
    }

    /**
     * 감상문 상세 조회
     * 1. 감상문 조회
     * 2. 신고횟수 조회
     * 3. 댓글 조회
     * 4. 태그 조회
     * 5. 좋아요수 조회
     * 6. 조회수 증가 TODO: 조회수 업데이트 조건 고려 후 추가. 추후 redis로 구현하기.
     * 7. 조회수 조회
     */
    @Override
    public ReportDTO getSingleReport(String reportId) {

        // 1. 감상문 조회
        ReportDTO report = reportService.getReport(reportId);
        if(report == null){
            return null;
        }

        // 2. 신고횟수 조회
        report.setComplaintCount(complaintService.getComplaintCount(reportId));

        // 3. 댓글 조회
        List<ReplyDTO> replies = replyService.getReplies(reportId);
        report.setReplyCount((long) replies.size());

        // 4. 태그 조회
        List<String> tagsInReport = tagService.getTagsInReport(reportId);
        if(!tagsInReport.isEmpty()) {
            String tagString = String.join("#", tagsInReport);
            report.setTagString("#" + tagString);
        }

        // 5. 좋아요수 조회
        Long countLike = likeService.countLike(reportId);
        report.setLikeCount(countLike);

        // 6. 조회수 증가
        viewService.addViewCount(reportId);

        // 7. 조회수 조회
        Long viewCount = viewService.getViewCount(reportId);
        report.setViewCount(viewCount);

        return report;
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
     * 5. 좋아요 삭제
     * 6. 조회수 삭제
     */
    @Override
    public void deleteReport(String reportId) {

        reportService.deleteReport(reportId);
        replyService.deleteRepliesByReportId(reportId);
        tagService.deleteTagInReport(reportId);
        complaintService.deleteComplaintByReportId(reportId);
        likeService.deleteLike(reportId);
        viewService.deleteViewCount(reportId);

    }


}
