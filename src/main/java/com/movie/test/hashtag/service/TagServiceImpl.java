package com.movie.test.hashtag.service;

import com.movie.test.hashtag.repository.TagInReportRepository;
import com.movie.test.hashtag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagInReportRepository tagInReportRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<String> getTagsInReport(String reportId) {
        List<Long> tagIds = tagInReportRepository.findTagIdByReportId(reportId);

        List<String> TagContents = tagRepository.findByReportId(tagIds);

        return TagContents;
    }
}
