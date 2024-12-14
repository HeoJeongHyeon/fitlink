package my.fitlink.domain.user.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.fitlink.domain.user.dto.request.ProfileRequest;
import my.fitlink.domain.user.dto.response.ProfileResponse;
import my.fitlink.domain.user.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile API")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/setProfile")
    public ResponseEntity<ProfileResponse.Details> setProfile(
            @RequestBody ProfileRequest.create request) {
        return ResponseEntity.ok().body(
                profileService.setProfile(request));
    }
}
