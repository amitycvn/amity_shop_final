package org.example.backend.services;

import org.example.backend.models.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class ThongBaoService extends GenericServiceImpl<ThongBao , UUID> {
    public ThongBaoService(JpaRepository<ThongBao, UUID> repository) {
        super(repository);
    }
}
