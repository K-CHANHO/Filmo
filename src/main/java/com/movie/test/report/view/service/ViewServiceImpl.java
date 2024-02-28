package com.movie.test.report.view.service;

import com.movie.test.redis.service.RedisService;
import com.movie.test.report.view.entity.ViewEntity;
import com.movie.test.report.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final ViewRepository viewRepository;
    private final RedisService redisService;

    @Override
    public Long getViewCount(String reportId) {

        Long count = viewRepository.findById(reportId).get().getViewCount();
        return count;
    }

    @Override
    public void addViewCount(String reportId) {

        ViewEntity viewEntity = viewRepository.findById(reportId).orElse(new ViewEntity());
        ViewEntity updateViewEntity = viewEntity.toBuilder().viewCount(viewEntity.getViewCount() + 1).build();
        viewRepository.save(updateViewEntity);

    }

    @Override
    public void addViewCountV2(String reportId) {
        String viewCount = redisService.getData(reportId);
        if(null == viewCount){
            redisService.setData(reportId, String.valueOf(1));
        } else {
            redisService.setData(reportId, String.valueOf(Integer.valueOf(viewCount)+1));
        }

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
