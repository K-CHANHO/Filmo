package com.movie.test.report.hashtag.service;

import java.util.ArrayList;
import java.util.List;

public interface TagService {

    List<String> getTagsInReport(String reportId);

    void saveTags(String reportId, String tags);

    void deleteTagInReport(String reportId);
}
