package com.movie.test.common.redis.service;

import com.movie.test.report.view.entity.ViewEntity;
import com.movie.test.report.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReportViewScheduler {

    private final RedisService redisService;
    private final ViewRepository viewRepository;
    private final RedisTemplate redisTemplate;

//    @Scheduled(initialDelay = 10000, fixedDelay = 60000)
    public void updateReportView(){
//        RedisTemplate redisTemplate = redisService.getRedisTemplate();
        log.info("조회수 스케쥴러 진입");
        Set<String> keys = redisTemplate.keys("*");
        log.info("key set : {}", keys);

        for(String key : keys){
            ViewEntity viewEntity = viewRepository.findById(key).orElse(null);

            if(viewEntity != null){
                log.info("entity info : {}", viewEntity.toString());
                ViewEntity entity = viewEntity.toBuilder()
                        .viewCount(viewEntity.getViewCount() + Long.valueOf(redisService.getData(key)))
                        .build();
                log.info("수정된 entity info : {}", entity.toString());
                viewRepository.save(entity);
                redisService.deleteData(key);
            }

        }
    }
}
