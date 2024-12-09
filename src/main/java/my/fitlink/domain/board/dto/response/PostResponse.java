package my.fitlink.domain.board.dto.response;

import my.fitlink.domain.board.entity.Post;

public class PostResponse {

    public record Info(
            Long postId,
            String categoryName,
            String title,
            String content
    ) {
        public static Info from(Post post) {
            return new Info(
                    post.getId(),
                    post.getCategory().getCategoryName(),
                    post.getTitle(),
                    post.getContent()
            );
        }
    }

}
