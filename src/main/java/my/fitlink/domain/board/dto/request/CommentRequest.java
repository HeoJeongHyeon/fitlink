package my.fitlink.domain.board.dto.request;

public class CommentRequest {

    public record Create(
            Long postId,
            Long userId,
            Long parentId,
            String content
    ){}
}
