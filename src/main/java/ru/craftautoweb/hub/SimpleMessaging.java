package ru.craftautoweb.hub;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Администратор on 06.12.2016.
 */
//@Component
public class SimpleMessaging extends SimpMessagingTemplate {
    public SimpleMessaging(MessageChannel messageChannel) {
        super(messageChannel);
    }
}
