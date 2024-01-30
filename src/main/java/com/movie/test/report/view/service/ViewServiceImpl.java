package com.movie.test.report.view.service;

import com.movie.test.report.view.entity.ViewEntity;
import com.movie.test.report.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final ViewRepository viewRepository;

    @Override
    public Long getViewCount(String reportId) {

        viewRepository.countByReportId(reportId);
        return null;
    }

    @Override
    public void addViewCount(String reportId) {

        ViewEntity viewEntity = viewRepository.findById(reportId).orElse(new ViewEntity());
        ViewEntity updateViewEntity = viewEntity.toBuilder().viewCount(viewEntity.getViewCount() + 1).build();
        viewRepository.save(updateViewEntity);
    }

    @Override
    public void registViewCount(String reportId) {
        ViewEntity viewEntity = ViewEntity.builder().reportId(reportId).viewCount(0L).build();
        viewRepository.save(viewEntity);
    }

    @Override
    public void deleteViewCount(String reportId) {
        viewRepository.deleteById(reportId);
    }
}
