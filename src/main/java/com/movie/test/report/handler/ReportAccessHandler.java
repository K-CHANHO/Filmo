package com.movie.test.report.handler;

import com.movie.test.report.report.repository.ReportRepository;
import com.movie.test.user.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportAccessHandler{

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public String getUid(String id) {
        return reportRepository.findById(id)
                .map(report -> report.getUserId())
                .map(userId -> userRepository.findById(userId))
                .get().get().getEmail();
    }
}
