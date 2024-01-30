package com.movie.test.report.view.service;

public interface ViewService {

    Long getViewCount(String reportId);
    void addViewCount(String reportId);
    void registViewCount(String reportId);
    void deleteViewCount(String reportId);
}
