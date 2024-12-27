package org.example.backend.repositories;

import org.example.backend.models.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThongBaoRepository extends JpaRepository<ThongBao, UUID> {
}