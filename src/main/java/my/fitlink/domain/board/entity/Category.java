package my.fitlink.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.fitlink.domain.user.dto.request.CategoryRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;


    private Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category toCategory(CategoryRequest.Create request) {
        return new Category(request.categoryName());
    }


}
