package my.fitlink.domain.chat.dto.request;

public class ChatRoomMemberRequest {
    public record Create(
        Long chatRoomId,
        Long userId
    ) {}
}
