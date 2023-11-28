package com.movie.test.inquiry.repository;

import com.movie.test.inquiry.entity.inquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface inquiryRepository extends JpaRepository<inquiryEntity, String> {
}
