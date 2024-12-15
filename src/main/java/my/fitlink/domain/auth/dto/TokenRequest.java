package my.fitlink.domain.auth.dto;

public class TokenRequest {

    public record Create(
            String accessToken
    ){

    }
}
