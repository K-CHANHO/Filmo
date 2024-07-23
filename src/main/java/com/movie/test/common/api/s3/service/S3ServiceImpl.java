package com.movie.test.common.api.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service{

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadFile(MultipartFile file) {

        // 저장 폴더 및 파일명 지정
        String originalFilename = file.getOriginalFilename();
        String fileName = createFileName(originalFilename);

        // 파일 변환
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        // 파일 업로드
        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
        } catch (IOException e) {
            log.error("--- 파일 변환 중 에러 발생 ---");
        }

        return getFileUrl(fileName);
    }

    @Override
    public String uploadImage(String imageUrl) {

        String fileName = "profile/".concat(UUID.randomUUID().toString()).concat(".png");
        URL url = null;
        InputStream in = null;
        ObjectMetadata objectMetadata = new ObjectMetadata();

        try {
            url = new URL(imageUrl);
            in = url.openStream();
            objectMetadata.setContentDisposition("inline");
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, in, objectMetadata));

        } catch (Exception e) {
            log.error("--- 파일 업로드 중 에러 발생 ---");
        }

        return getFileUrl(fileName);
    }


    @Override
    public String createFileName(String originalFilename) {

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        /* TODO : 이미지 확장자만 가능하도록 구현해야 함
        if(!extension.equals("jpg") && !extension.equals("png")){

        }
         */

        return "profile/".concat(UUID.randomUUID().toString()).concat("." + extension);
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}
