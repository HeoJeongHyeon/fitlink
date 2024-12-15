package my.fitlink.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.board.dto.request.CommentRequest;
import my.fitlink.domain.user.entity.User;
import my.fitlink.global.entity.BaseTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long parentId;

    @Column(nullable = false)
    private String content;

    private Comment(Post post, User user, Long parentId, String content) {
        this.post = post;
        this.user = user;
        this.parentId = parentId;
        this.content = content;
    }

    public static Comment toComment(CommentRequest.Create request, Post post, User user) {
        return new Comment(
                post, user, request.parentId(), request.content()
        );
    }
}
