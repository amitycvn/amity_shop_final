package org.example.backend.services;

import org.example.backend.models.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DanhMucService extends GenericServiceImpl<DanhMuc, UUID> {
    public DanhMucService(JpaRepository<DanhMuc, UUID> repository) {
        super(repository);
    }
}
