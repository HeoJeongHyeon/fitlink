package my.fitlink.domain.chat.dto.response;

import my.fitlink.domain.chat.entity.ChatMessage;

import java.time.LocalDateTime;

public class ChatMessageResponse {
    public record Info(
        Long id,
        Long chatRoomId,
        String senderName,
        String content
    ) {
        public static Info from(ChatMessage message) {
            return new Info(
                message.getId(),
                message.getChatRoom().getId(),
                message.getSender().getNickname(),
                message.getContent()
            );
        }
    }
}