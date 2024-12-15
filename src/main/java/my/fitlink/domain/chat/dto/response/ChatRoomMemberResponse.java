package my.fitlink.domain.chat.dto.response;

import my.fitlink.domain.chat.entity.ChatRoomMember;

public class ChatRoomMemberResponse {
    public record Info(
        Long id,
        Long chatRoomId,
        Long userId,
        String userName
    ) {
        public static Info from(ChatRoomMember member) {
            return new Info(
                member.getId(),
                member.getChatRoom().getId(),
                member.getUser().getId(),
                member.getUser().getNickname()
            );
        }
    }
}