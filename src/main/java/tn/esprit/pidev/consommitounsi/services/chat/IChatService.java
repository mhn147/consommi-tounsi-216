package tn.esprit.pidev.consommitounsi.services.chat;

import tn.esprit.pidev.consommitounsi.entities.chat.Chat;

import java.util.List;

public interface IChatService {
    void add(Chat c, long senderId, long receiverId);
    List<Chat> getConversation(long senderId, long receiverId);
}
