package my.fitlink.domain.board.dto.request;

public class CategoryRequest {

    public record Create(
            String categoryName
    ) {

    }
}
