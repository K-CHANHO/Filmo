package com.movie.test.report.view.service;

public interface ViewService {

    Long getViewCount(String reportId);
    void addViewCount(String reportId);
    Long addViewCountV2(String reportId);
    void registViewCount(String reportId);
    void deleteViewCount(String reportId);
}
