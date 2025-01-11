package org.example.backend.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PhieuGiamGiaService extends GenericServiceImpl<PhieuGiamGia , UUID> {
    public PhieuGiamGiaService(JpaRepository<PhieuGiamGia, UUID> repository, PhieuGiamGiaRepository PGGRepository) {
        super(repository);
        this. PGGrepository= PGGRepository;
    }

//    public PageResponse<List<phieuGiamGiaReponse>> getAllPGG(int page, int itemsPerPage) {
//        Pageable pageable = PageRequest.of(page, itemsPerPage);
//        Page<phieuGiamGiaReponse> PGGPage = PGGrepository.getAllPhieuGiamGia(pageable);
//
//        return PageResponse.<List<phieuGiamGiaReponse>>builder()
//                .page(PGGPage.getNumber())
//                .size(PGGPage.getSize())
//                .totalPage(PGGPage.getTotalPages())
//                .items(PGGPage.getContent())
//                .build();
//    }

    private final PhieuGiamGiaRepository PGGrepository;

    public List<phieuGiamGiaReponse> getPGGGetAll() {
        return PGGrepository.getAllPhieuGiamGia();
    }

    public List<phieuGiamGiaReponse> getPGGGetAllbyIDKH(UUID id) {
        String dangDienRa = "Đang Diễn Ra";
        String sapDienRa = "Sắp Diễn Ra";
        String daSuDung = "Đã sử dụng";
        return PGGrepository.getAllPhieuGiamGiabyIDKH(id,dangDienRa,sapDienRa,daSuDung);
    }

    public void setDeletedPhieuGiamGia(Boolean deleted, UUID id){
        PGGrepository.updateDetailPhieuGiamGia(deleted, id);
    }

    public void updateTrangThaiAndNgayKetThuc(String trangThai, Instant ngayKetThuc, UUID id) {
        PGGrepository.updateTrangThaiAndNgayKetThuc(trangThai, ngayKetThuc, id);
    }

    public PageResponse<List<phieuGiamGiaReponse>> searchPGG(int page, int itemsPerPage,String keyFind,
      String trangThai, Integer sapXep, Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia) {
        // Tạo đối tượng Sort dựa trên giá trị của tham số sapXep
        Sort sort = Sort.unsorted();  // Mặc định không sắp xếp nếu sapXep không khớp

        switch (sapXep) {
            case 1:
                sort = Sort.by(Sort.Order.desc("ngayTao")); // sapXep = 1, sắp xếp theo ngayTao giảm dần
                break;
            case 2:
                sort = Sort.by(Sort.Order.asc("ngayTao"));  // sapXep = 2, sắp xếp theo ngayTao tăng dần
                break;
            case 3:
                sort = Sort.by(Sort.Order.desc("ngaySua")); // sapXep = 3, sắp xếp theo ngaySua giảm dần
                break;
            default:
                sort = Sort.by(Sort.Order.desc("ngayTao"));
                break; // Không sắp xếp nếu sapXep không có giá trị mong đợi
        }

        // Tạo Pageable với phân trang và sắp xếp
        Pageable pageable = PageRequest.of(page, itemsPerPage, sort);


//        Pageable pageable = PageRequest.of(page, itemsPerPage);
        Page<phieuGiamGiaReponse> PGGPage = PGGrepository.searchPhieuGiamGia(pageable,keyFind,trangThai,minNgay,maxNgay,minGia,maxGia);
        return PageResponse.<List<phieuGiamGiaReponse>>builder()
                .page(PGGPage.getNumber())
                .size(PGGPage.getSize())
                .totalPage(PGGPage.getTotalPages())
                .items(PGGPage.getContent())
                .build();
    }

    public byte[] exportToExcel(List<phieuGiamGiaReponse> voucherList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Phiếu Giảm Giá");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Mã");
        headerRow.createCell(2).setCellValue("Tên");
        headerRow.createCell(3).setCellValue("Loại");
        headerRow.createCell(4).setCellValue("Giá Trị");
        headerRow.createCell(5).setCellValue("Giảm Tối Đa");
        headerRow.createCell(6).setCellValue("Mức Độ");
        headerRow.createCell(7).setCellValue("Ngày Bắt Đầu");
        headerRow.createCell(8).setCellValue("Ngày Kết Thúc");
        headerRow.createCell(9).setCellValue("Số Lượng");
        headerRow.createCell(10).setCellValue("Điều Kiện");
        headerRow.createCell(11).setCellValue("Trạng Thái");

        int rowNum = 1;
        for (phieuGiamGiaReponse voucher : voucherList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(voucher.getId().toString());
            row.createCell(1).setCellValue(voucher.getMa());
            row.createCell(2).setCellValue(voucher.getTen());

            // Kiểm tra giá trị loai trước khi sử dụng
            Boolean loai = voucher.getLoai();
            if (loai != null) {
                row.createCell(3).setCellValue(loai ? "Phần trăm" : "Giá");
            } else {
                row.createCell(3).setCellValue("Chưa xác định"); // Hoặc giá trị mặc định nào đó
            }

            row.createCell(4).setCellValue(voucher.getGiaTri() != null ? voucher.getGiaTri().doubleValue() : 0.0);
            row.createCell(5).setCellValue(voucher.getGiamToiDa() != null ? voucher.getGiamToiDa().doubleValue() : 0.0);
            row.createCell(6).setCellValue(voucher.getMucDo() != null ? voucher.getMucDo() : "Không xác định");
            row.createCell(7).setCellValue(voucher.getNgayBatDau() != null ? voucher.getNgayBatDau().toString() : "Không xác định");
            row.createCell(8).setCellValue(voucher.getNgayKetThuc() != null ? voucher.getNgayKetThuc().toString() : "Không xác định");
            row.createCell(9).setCellValue(voucher.getSoLuong() != null ? voucher.getSoLuong() : 0);
            row.createCell(10).setCellValue(voucher.getDieuKien() != null ? voucher.getDieuKien() : 0);
            row.createCell(11).setCellValue(voucher.getTrangThai() != null ? voucher.getTrangThai() : "Không xác định");
        }

        // Ghi dữ liệu ra byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    @Scheduled(fixedDelay = 60000)
    public void capNhatTrangThaiPhieuGiamGia() {
        // Lấy thời gian hiện tại
        Instant thoiGianHienTai = Instant.now();
        
        // Lấy tất cả phiếu giảm giá
        List<phieuGiamGiaReponse> danhSachPhieu = PGGrepository.getAllPhieuGiamGia();
        
        for (phieuGiamGiaReponse phieu : danhSachPhieu) {
            // Kiểm tra số lượng
            if (phieu.getSoLuong() != null && 
                phieu.getSoLuong() == 0 && 
                !phieu.getTrangThai().equals("Hết số lượng")) {
                
                // Cập nhật trạng thái thành "Hết Số Lượng"
                updateTrangThaiAndNgayKetThuc("Hết số lượng", phieu.getNgayKetThuc(), phieu.getId());
                continue; // Bỏ qua các kiểm tra khác nếu đã hết số lượng
            }
            
            // Kiểm tra phiếu đến thời gian bắt đầu
            if (phieu.getNgayBatDau() != null && 
                phieu.getNgayBatDau().isBefore(thoiGianHienTai) && 
                phieu.getNgayKetThuc().isAfter(thoiGianHienTai) && 
                phieu.getSoLuong() > 0 &&
                !phieu.getTrangThai().equals("Đang diễn ra")) {
                
                // Cập nhật trạng thái thành "Đang diễn ra"
                updateTrangThaiAndNgayKetThuc("Đang diễn ra", phieu.getNgayKetThuc(), phieu.getId());
            }
            
            // Kiểm tra phiếu đến thời gian kết thúc
            if (phieu.getNgayKetThuc() != null && 
                phieu.getNgayKetThuc().isBefore(thoiGianHienTai) && 
                !phieu.getTrangThai().equals("Đã kết thúc")) {
                
                // Cập nhật trạng thái thành "Đã kết thúc"
                updateTrangThaiAndNgayKetThuc("Đã kết thúc", phieu.getNgayKetThuc(), phieu.getId());
            }
        }
    }


    //check so luong phieu dung cho ban hang tai quầy

    public boolean checkVoucherQuantity(UUID saleId, int requiredQuantity) {
        String trangThai = "Đang diễn ra";
        Optional<Integer> availableQuantity = PGGrepository.findSoLuongGiamGiaById(saleId,trangThai);
        // Nếu không tìm thấy phiếu giảm giá, coi như không đủ số lượng
        if (availableQuantity.isEmpty()) {
            return false;
        }
        return availableQuantity.get() >= requiredQuantity;
    }
}
