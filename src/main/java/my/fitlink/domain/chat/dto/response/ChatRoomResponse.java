package my.fitlink.domain.chat.dto.response;

import my.fitlink.domain.chat.entity.ChatRoom;
import my.fitlink.domain.chat.entity.ChatRoomMember;

import java.util.List;

public class ChatRoomResponse {
    public record Info(
            Long id,
            String title,
            List<ChatRoomMemberResponse.Info> members
    ) {
        public static Info from(ChatRoom room, List<ChatRoomMember> members) {
            return new Info(
                    room.getId(),
                    room.getTitle(),
                    members.stream()
                            .map(ChatRoomMemberResponse.Info::from)
                            .toList()
            );
        }
    }
}