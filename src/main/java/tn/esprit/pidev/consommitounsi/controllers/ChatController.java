package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.chat.Chat;
import tn.esprit.pidev.consommitounsi.services.chat.IChatService;
import tn.esprit.pidev.consommitounsi.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {
    @Autowired
    IChatService chatService;

    @PostMapping("/customer/chats/{senderId}/{receiverId}")
    @ResponseBody
    public void addChat(@RequestBody Chat c, @PathVariable("senderId")long senderId, @PathVariable("receiverId")long receiverId) {
        if (UserSession.hasId(senderId) && !c.getMessage().isEmpty())
            chatService.add(c, senderId, receiverId);
    }

    @GetMapping("/customer/chats/{senderId}/{receiverId}")
    @ResponseBody
    public List<Chat> getConversation(@PathVariable("senderId")long senderId, @PathVariable("receiverId")long receiverId) {
        if (UserSession.hasId(senderId) || UserSession.isAdmin())
            return chatService.getConversation(senderId, receiverId);
        return new ArrayList<>();
    }
}
