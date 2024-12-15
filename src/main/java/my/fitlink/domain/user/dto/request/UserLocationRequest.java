package my.fitlink.domain.user.dto.request;


import jakarta.validation.constraints.NotBlank;

public class UserLocationRequest {
    public record create(

            @NotBlank
            String dong,
            @NotBlank
            double latitude,
            @NotBlank
            double longitude
    ) {

    }
}
