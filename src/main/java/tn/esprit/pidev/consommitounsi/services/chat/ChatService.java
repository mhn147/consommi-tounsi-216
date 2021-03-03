package tn.esprit.pidev.consommitounsi.services.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.chat.Chat;
import tn.esprit.pidev.consommitounsi.repositories.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.chat.ChatRepository;

import java.util.Date;
import java.util.List;

@Service
public class ChatService implements IChatService {
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    public void add(Chat c, long senderId, long receiverId) {
        User sender=userRepository.findById(senderId).orElse(null);
        User receiver=userRepository.findById(receiverId).orElse(null);
        if (sender!=null && receiver!=null) {
            c.setDate(new Date());
            c.setSender(sender);
            c.setReceiver(receiver);
            chatRepository.save(c);
        }
    }

    public List<Chat> getConversation(long senderId, long receiverId) {
        return chatRepository.getConversation(senderId, receiverId);
    }
}
