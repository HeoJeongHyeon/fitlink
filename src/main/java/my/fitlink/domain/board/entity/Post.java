package my.fitlink.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.board.dto.request.PostRequest;
import my.fitlink.global.entity.BaseTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;



    /* imageUrl */

    private Post(Category category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;

    }

    public static Post toPost(PostRequest.Create request, Category category) {
        return new Post(
                category,
                request.title(),
                request.content());
    }
}
