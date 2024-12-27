package org.example.backend.services;

// src/main/java/com/example/demo/service/GenericService.java
import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {

    // 1. Lấy tất cả các bản ghi
    List<T> findAll();

    // 2. Tìm bản ghi theo ID
    Optional<T> findById(ID id);

    // 3. Lưu một bản ghi (tạo mới hoặc cập nhật)
    T save(T entity);

    // 4. Xóa bản ghi theo ID
    void deleteById(ID id);

    // 5. Xóa tất cả các bản ghi
    void deleteAll();

    // 6. Kiểm tra xem một bản ghi có tồn tại theo ID
    boolean existsById(ID id);

    // 7. Lưu nhiều bản ghi
    List<T> saveAll(Iterable<T> entities);
}
