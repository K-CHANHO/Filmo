package com.movie.test.report.view.service;

import com.movie.test.redis.service.RedisService;
import com.movie.test.report.view.entity.ViewEntity;
import com.movie.test.report.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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

    /**
     * Redis에 추가되어야 하는 조회수 저장.
     * 추가되어야 하는 조회수 return.
     */
    @Override
    public Long addViewCountV2(String reportId) {
//        String prefix = "view_"; // 추후 다른 값도 redis 활용 시 구분자 추가하여야 할 듯.
        String viewCount = redisService.getData(reportId);
        if(null == viewCount){
            redisService.setData(reportId, String.valueOf(1));
            return 1L;
        } else {
            redisService.setData(reportId, String.valueOf(Long.valueOf(viewCount)+1));
            return Long.valueOf(viewCount)+1;
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
