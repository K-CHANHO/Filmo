package com.movie.test.report.hashtag.service;

import com.movie.test.report.hashtag.entity.TagEntity;
import com.movie.test.report.hashtag.entity.TagInReportEntity;
import com.movie.test.report.hashtag.repository.TagInReportRepository;
import com.movie.test.report.hashtag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService{

    private final TagInReportRepository tagInReportRepository;
    private final TagRepository tagRepository;

    @Override
    // 감상문에 사용된 태그 검색
    public List<String> getTagsInReport(String reportId) {
        // 감상문에 사용된 태그들 id 검색
        List<Long> tagIds = tagInReportRepository.findTagIdByReportId(reportId);

        // 태그 id들로 태그내용들 검색
        List<String> TagContents = tagRepository.findByTagIds(tagIds);

        return TagContents;
    }

    @Override
    @Transactional
    // 태그 저장
    public void saveTags(String reportId, String fullTags) {

        Long tagId = null;
        String[] tags = fullTags.split(",");

        for (int i = 0; i <tags.length; i++) {
            TagEntity tagEntity = tagRepository.findByContent(tags[i]);

            // tag 테이블에 없으면
            if(tagEntity == null){
                // tag 테이블에 태그 저장
                TagEntity savedTag = tagRepository.save(TagEntity.builder().content(tags[i]).build());
                tagId = savedTag.getTagId();
            }
            // tag 테이블에 있으면 tagId 추출
            else {
                tagId = tagEntity.getTagId();
            }

            // tag in report 테이블에 저장
            tagInReportRepository.save(TagInReportEntity.builder()
                    .reportId(reportId)
                    .tagId(tagId)
                    .build());

        }

    }

    @Override
    public void deleteTagInReport(String reportId) {
        tagInReportRepository.deleteByReportId(reportId);

    }
}
