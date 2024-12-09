package my.fitlink.domain.user.dto.response;

import my.fitlink.domain.board.entity.Category;

public class CategoryResponse {
    public record CategoryInfo(
            String categoryName
    ) {
        public static CategoryInfo from(Category category) {
            return new CategoryInfo(
                    category.getCategoryName()
            );
        }
    }

    public record CategoryListInfo(
            String categoryName
    ) {
        public static CategoryListInfo from(Category category) {
            return new CategoryListInfo(
                    category.getCategoryName()
            );
        }

    }
}