package org.example.backend.services;

import org.example.backend.models.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class KichThuocService extends GenericServiceImpl<KichThuoc , UUID> {
    public KichThuocService(JpaRepository<KichThuoc, UUID> repository) {
        super(repository);
    }
}
