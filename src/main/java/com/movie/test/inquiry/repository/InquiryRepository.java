package com.movie.test.inquiry.repository;

import com.movie.test.inquiry.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, String>, InquiryRepositoryCustom {
}
