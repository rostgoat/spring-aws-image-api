package com.rm.awsimageupload.profile;

import java.util.Objects;
import java.util.UUID;

public class UserProfile {
    
    private UUID userProfileId;
    private String username;
    private String userProfileImageLink;


    public UserProfile() {
    }
    
}
    public UserProfile(UUID userProfileId, String username, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.userProfileImageLink = userProfileImageLink;
    }

    public UUID getUserProfileId() {
        return this.userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfileImageLink() {
        return this.userProfileImageLink;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserProfile)) {
            return false;
        }
        UserProfile userProfile = (UserProfile) o;
        return Objects.equals(userProfileId, userProfile.userProfileId) && Objects.equals(username, userProfile.username) && Objects.equals(userProfileImageLink, userProfile.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, username, userProfileImageLink);
    }

    @Override
    public String toString() {
        return "{" +
            " userProfileId='" + getUserProfileId() + "'" +
            ", username='" + getUsername() + "'" +
            ", userProfileImageLink='" + getUserProfileImageLink() + "'" +
            "}";
    }
