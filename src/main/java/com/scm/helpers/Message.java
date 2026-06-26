package com.scm.helpers;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;

}
