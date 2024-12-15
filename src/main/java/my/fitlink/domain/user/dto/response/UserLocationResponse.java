package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.user.entity.UserLocation;
import java.time.LocalDateTime;

public class UserLocationResponse {

    public record Details(
            Long locationId,
            String dong,
            double latitude,
            double longitude
    ) {
        public static Details from(UserLocation location) {
            return new Details(
                    location.getId(),
                    location.getDong(),
                    location.getLatitude(),
                    location.getLongitude()
            );
        }
    }

    public record Summary(
            String dong
    ) {
        public static Summary from(UserLocation location) {
            return new Summary(
                    location.getDong()
            );
        }
    }

}