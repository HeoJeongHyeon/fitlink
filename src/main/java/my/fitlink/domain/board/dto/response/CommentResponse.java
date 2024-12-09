package my.fitlink.domain.board.dto.response;

import my.fitlink.domain.board.entity.Comment;
import java.util.List;

public class CommentResponse {

    public record Info(
            Long commentId,
            Long postId,
            Long userId,
            Long parentId,
            String content
    ){
        public static Info from(Comment comment) {
            return new Info(
                    comment.getId(),
                    comment.getPost().getId(),
                    comment.getUser().getId(),
                    comment.getParentId(),
                    comment.getContent());
        }
    }

    public record CommentWithReplies(
            Info comment,
            List<Info> replies
    ) {}
}
