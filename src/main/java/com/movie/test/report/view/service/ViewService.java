package com.movie.test.report.view.service;

public interface ViewService {

    Long getViewCount(String reportId);
    void addViewCount(String reportId);

}
