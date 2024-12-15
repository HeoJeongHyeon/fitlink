package my.fitlink.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.fitlink.domain.user.dto.request.ProfileRequest;
import my.fitlink.domain.user.dto.response.ProfileResponse;
import my.fitlink.domain.user.entity.Profile;
import my.fitlink.domain.user.entity.User;
import my.fitlink.domain.user.repository.ProfileRepository;
import my.fitlink.domain.user.repository.UserRepository;
import my.fitlink.global.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileResponse.Details setProfile(ProfileRequest.create request) {
        User user = SecurityUtil.getCurrentUser();
        Profile profile = Profile.from(request, user);
        profileRepository.save(profile);
        return ProfileResponse.Details.from(profile);
    }

}
