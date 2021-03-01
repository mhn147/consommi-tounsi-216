package tn.esprit.pidev.consommitounsi.repositories.chat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.chat.Chat;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    @Query("select c from Chat c where (c.sender.id = :senderId and c.receiver.id = :receiverId) or (c.sender.id = :receiverId and c.receiver.id = :senderId) order by c.date")
    List<Chat> getConversation(@Param("senderId")long senderId, @Param("receiverId")long receiverId);
}
