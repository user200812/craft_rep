package ru.craftautoweb.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.craftautoweb.entities.VDocumentsEntity;
import ru.craftautoweb.hub.HubMessage;
import ru.craftautoweb.utils.HibernateUtil;

import java.util.List;


/**
 * Created by User on 05.12.2016.
 */
@Controller
@EnableScheduling
public class TopicController {

    @Autowired
    private SimpMessagingTemplate simpMessaging;

    @MessageMapping("/main")
    public void receiveMessage(HubMessage message) {
        //log.info("message.getMessage() = " + message.getMessage());
    }


    @Scheduled(fixedDelay = 120000)
    private void sendDangerOrders() {
        Session session = HibernateUtil.getSession();
        String sql = "from VDocumentsEntity  where warning='WARN' order by id";
        List<VDocumentsEntity> list = session.createQuery(sql).list();
        session.close();
        String message = "";
        for (VDocumentsEntity item : list) {
            String linkstr = "<a class='text-danger' href=Orders/Edit/" +
                    item.getIdDoc() + ">" +
                    item.getNumber() + "</a>";
            if (message == "")
                message = linkstr;
            else
                message += ", " + linkstr;
        }

        if (!message.equals("")) {
            message = "У вас есть просроченные заказы!<br>Номера: " + message;

            simpMessaging.convertAndSend("topic/main", new HubMessage(message));
            //log.debug("Send message: " + message);
        }
    }

    @RequestMapping("/start")
    public String start() {
        return "start";
    }
}
