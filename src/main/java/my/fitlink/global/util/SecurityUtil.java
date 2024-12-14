package my.fitlink.global.util;

import my.fitlink.domain.user.entity.User;
import my.fitlink.global.security.auth.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new IllegalArgumentException("인증 없음.");
            }

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            if (customUserDetails == null) {
                throw new IllegalArgumentException("사용자 없음");
            }

            return customUserDetails.getUser();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("잘못된 정보 ");
        }
    }

}
