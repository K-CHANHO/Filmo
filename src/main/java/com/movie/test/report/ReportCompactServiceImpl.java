package com.movie.test.report;

import com.movie.test.redis.service.RedisService;
import com.movie.test.report.bookmark.service.BookmarkService;
import com.movie.test.report.complaint.service.ComplaintService;
import com.movie.test.report.hashtag.service.TagService;
import com.movie.test.report.like.service.LikeService;
import com.movie.test.report.reply.dto.ReplyDTO;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.dto.ReportSimpleDTO;
import com.movie.test.report.report.service.ReportService;
import com.movie.test.report.view.service.ViewService;
import com.movie.test.user.userinfo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    private final UserService userService;
    private final BookmarkService bookmarkService;

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
        viewService.registViewCount(reportId);

        return reportId;
    }

    /**
     * 감상문 리스트 조회
     * 1. 감상문 Id 리스트 조회
     * 2. 감상문 Id 리스트를 통해 각각의 감상문 정보 조회
     */
    @Override
    public Slice<ReportSimpleDTO> getReportList(ReportListSearchDTO reportListSearchDTO, Pageable pageable) {

        // reportId를 구한 뒤 getSingleReport로 각각 구해오기?
        Slice<String> searchReportId = reportService.getSearchReportId(reportListSearchDTO, pageable);

        // 구현해놓은 getSimpleReport 이용
        Slice<ReportSimpleDTO> searchReportDTO = searchReportId.map(this::getSimpleReport);

        return searchReportDTO;
    }

    /**
     * 리스트에서 보여줄 간단한 내용 조회
     * 1. 제목, 내용, 작성시간, 이미지
     * 2. 닉네임
     * 3. 좋아요 수
     * 4. 댓글 수
     */
    @Override
    public ReportSimpleDTO getSimpleReport(String reportId) {

        // 제목, 내용, 작성시간, 이미지
        ReportDTO reportDTO = reportService.getReport(reportId);
        String title = reportDTO.getTitle();
        String content = reportDTO.getContent();
        Timestamp createDate = reportDTO.getCreateDate();
        String imageUrl = reportDTO.getImageUrl();

        // 닉네임
        String nickname = userService.getUserInfo(reportDTO.getUserId()).getNickname();

        // 좋아요수
        Long likeCount = likeService.countLike(reportId);

        // 댓글 수
        Long replyCount =  Long.valueOf(replyService.getReplies(reportId).size());

        // 북마크 수
        Long bookmarkCount = bookmarkService.getBookmarkCount(reportId);

        ReportSimpleDTO reportSimpleDTO = ReportSimpleDTO.builder()
                .reportId(reportId)
                .title(title)
                .content(content)
                .createDate(createDate)
                .imageUrl(imageUrl)
                .nickname(nickname)
                .likeCount(likeCount)
                .replyCount(replyCount)
                .bookmarkCount(bookmarkCount)
                .build();

        return reportSimpleDTO;
    }

    /**
     * 감상문 상세 조회
     * 1. 감상문 조회
     * 2. 신고횟수 조회
     * 3. 댓글 조회
     * 4. 태그 조회
     * 5. 좋아요수 조회
     * 6. (필요 시) 조회수 증가
     * 7. 조회수 조회
     */
    @Override
    public ReportDTO getSingleReport(String reportId) {

        // 1. 감상문 조회
        ReportDTO reportDTO = reportService.getReport(reportId);
        if(reportDTO == null){
            return null;
        }

        // 2. 신고횟수 조회
        reportDTO.setComplaintCount(complaintService.getComplaintCount(reportId));

        // 3. 댓글 조회
        List<ReplyDTO> replies = replyService.getReplies(reportId);
        reportDTO.setReplyCount((long) replies.size());

        // 4. 태그 조회
        List<String> tagsInReport = tagService.getTagsInReport(reportId);
        if(!tagsInReport.isEmpty()) {
            String tagString = String.join(",", tagsInReport);
            reportDTO.setTagString(tagString);
        }

        // 5. 좋아요수 조회
        Long countLike = likeService.countLike(reportId);
        reportDTO.setLikeCount(countLike);

        // 6. 조회수 증가 (redis)
        Long redisCount = viewService.addViewCountV2(reportId);

        // 7. 조회수 조회
        Long viewCount = viewService.getViewCount(reportId);
        reportDTO.setViewCount(viewCount + redisCount);


        return reportDTO;
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
