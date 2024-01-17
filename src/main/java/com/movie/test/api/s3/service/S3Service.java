package com.movie.test.api.s3.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file);
    String uploadImage(String imageUrl);

    String createFileName(String originFileName);
    String getFileUrl(String fileName);
}
