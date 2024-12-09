package my.fitlink.domain.chat.dto.request;

public class ChatMessageRequest {
    public record Create(
            Long chatRoomId,
            Long senderId,
            String content
    ) {}
}