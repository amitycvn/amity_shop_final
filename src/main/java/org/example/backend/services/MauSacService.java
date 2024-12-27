package org.example.backend.services;

import org.example.backend.models.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class MauSacService extends GenericServiceImpl<MauSac , UUID> {
    public MauSacService(JpaRepository<MauSac, UUID> repository) {
        super(repository);
    }
}
