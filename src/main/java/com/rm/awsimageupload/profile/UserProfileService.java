package com.rm.awsimageupload.profile;

import java.io.IOException;
import java.util.*;

import com.rm.awsimageupload.bucket.BucketName;
import com.rm.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. check if image is not empty
        isFileEmpty(file);

        // 2. if file is an image
        isImage(file);

        // 3. the user exists in db
        UserProfile user = getUserProfileOrThrow(userProfileId);

        // 4. grab some meta from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. store image in s3 and update db with s3 image url
        String path =  String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User Profile %s not found", userProfileId)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType())
                .contains(file.getContentType())) {
            throw new IllegalStateException("File must an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
    }
}