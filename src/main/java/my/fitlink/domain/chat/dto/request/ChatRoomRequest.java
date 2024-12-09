package my.fitlink.domain.chat.dto.request;

import java.util.List;

public class ChatRoomRequest {
    public record Create(
        String title,
        List<Long> memberIds
    ) {}
}