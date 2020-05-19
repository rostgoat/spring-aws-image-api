package com.rm.awsimageupload.datastore;

import java.util.*;
import com.rm.awsimageupload.profile.*;

import org.springframework.stereotype.Repository;

@Repository
public class FakeUserProfileDataStore {
    
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "rostypoo", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}