package my.fitlink.domain.board.dto.response;

import my.fitlink.domain.board.entity.Category;

public class CategoryResponse {

    public record Info(
            Long categoryId,
            String categoryName
    ) {
        public static Info from(Category category) {
            return new Info(
                    category.getId(),
                    category.getCategoryName()
            );
        }
    }
}
