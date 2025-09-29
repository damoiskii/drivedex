package com.devdam.server.service.upload;

import org.springframework.web.multipart.MultipartFile;

import com.devdam.server.error.CloudinaryRemovalException;
import com.devdam.server.error.CloudinaryUploadException;

public interface CloudinaryService {
    String uploadFile(MultipartFile file, String filename) throws CloudinaryUploadException;
    String uploadFile(String url, String filename) throws CloudinaryUploadException;
    void deleteFile(String filename) throws CloudinaryRemovalException;
}
