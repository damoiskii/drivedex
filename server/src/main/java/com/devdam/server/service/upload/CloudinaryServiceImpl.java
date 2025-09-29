package com.devdam.server.service.upload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.devdam.server.error.CloudinaryRemovalException;
import com.devdam.server.error.CloudinaryUploadException;
import com.devdam.server.util.FileUtils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    private final String storagePath = "drivedex/uploads/";
    private String subPath;

    @Override
    @SuppressWarnings("unchecked")
    public String uploadFile(MultipartFile file, String filename) throws CloudinaryUploadException {
        String publicID = getPublicID(filename);

        Map<String, Object> params = Map.of(
                "public_id", publicID,
                "overwrite", true,
                "resource_type", "image"
        );

        Map<String, Object> response;
        try {
            response = cloudinary.uploader().upload(file.getBytes(), params);
            log.info("Cloudinary upload successful: {}", response.get("url"));
        } catch (Exception e) {
            log.error("Cloudinary upload error: ", e);
            throw new CloudinaryUploadException("Failed to upload temporary file to Cloudinary");
        }

        return response.get("url").toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String uploadFile(String url, String filename) throws CloudinaryUploadException {
        byte[] fileBytes = FileUtils.getFileBytesFromUrl(url);

        if(fileBytes == null) return null;

        String publicID = getPublicID(filename);
        Map<String, Object> params = Map.of(
                "public_id", publicID,
                "overwrite", true,
                "resource_type", "image"
        );

        Map<String, Object> response;
        try {
            response = cloudinary.uploader().upload(fileBytes, params);
            log.info("Cloudinary upload successful: {}", response.get("url"));
        } catch (Exception e) {
            log.error("Cloudinary upload error: ", e);
            throw new CloudinaryUploadException("Failed to upload temporary file to Cloudinary");
        }

        return response.get("url").toString();
    }

    @Override
    public void deleteFile(String filename) throws CloudinaryRemovalException {
        String publicID = getPublicID(filename);
        // Map response = cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());

        try {
            cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());
            log.info("Cloudinary removal successful: {}", publicID);
        } catch (Exception e) {
            log.error("Cloudinary removal error: ", e);
            throw new CloudinaryRemovalException("Failed to remove file from Cloudinary");
        }
    }

    // Get public ID for the filename
    // public String getPublicID(String filename) {
    //     StringBuilder sb = new StringBuilder(storagePath);
        
    //     if (subPath != null && !subPath.isBlank()) {
    //         sb.append(subPath).append('/');
    //     }

    //     sb.append(filename);
    //     return sb.toString();
    // }

    public String getPublicID(String filename) {
        return (subPath != null && subPath.trim().length() > 0)
                ? (storagePath + subPath + "/" + filename)
                : (storagePath + filename);
    }
}
