package my.fitlink.domain.board.dto.request;

public class PostRequest {

    public record Create(
            Long categoryId,
            String title,
            String content
    ) {

    }

    public record Update(
            Long categoryId,
            String title,
            String content
    ) {

    }
}
