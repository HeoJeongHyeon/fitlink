package my.fitlink.domain.auth.dto;

public class TokenResponse {

    public record Detail(
            String accessToken,
            String tokenType /* access인지 refresh인지 ? 구분짓는걸로 해야하나 ? */

    ) {
        public static Detail from(String accessToken) {
            return new Detail(accessToken,"Bearer");
        }
    }

}
