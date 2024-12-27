package org.example.backend.services;

import org.example.backend.models.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatLieuService extends GenericServiceImpl<ChatLieu, UUID>{
    public ChatLieuService(JpaRepository<ChatLieu, UUID> repository) {
        super(repository);
    }
}
