package org.example.backend.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDon;
import org.example.backend.repositories.HoaDonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService extends GenericServiceImpl<HoaDon, UUID> {
    public HoaDonService(JpaRepository<HoaDon, UUID> repository,HoaDonRepository hoaDonRepository) {
        super(repository);
        this.hoaDonRepository = hoaDonRepository;
    }

    private final HoaDonRepository hoaDonRepository;

    public List<QuanLyDonHangRespose> getHDGetAll() {
        return hoaDonRepository.GetAllHoaDon();
    }

    public QuanLyDonHangRespose getHoaDonByID(UUID id) {
        return hoaDonRepository.getHoaDonbyID(id);
    }

    public PageResponse<List<QuanLyDonHangRespose>> getHoaDonByStats(int page, int size,String status) {
        Pageable pageable = PageRequest.of(page, size);
        Page<QuanLyDonHangRespose> qlhdPage = hoaDonRepository.GetAllHoaDonByTrangThai(pageable,status);

        return PageResponse.<List<QuanLyDonHangRespose>>builder()
                .page(qlhdPage.getNumber())
                .size(qlhdPage.getSize())
                .totalPage(qlhdPage.getTotalPages())
                .items(qlhdPage.getContent())
                .build();
    }

    // Phương thức lấy số lượng hóa đơn cho từng trạng thái trong danh sách truyền vào
    public List<Long> countHoaDonByStatuses(List<String> statuses) {
        return hoaDonRepository.countHoaDonByTrangThaiList(statuses);
    }

    public PageResponse<List<QuanLyDonHangRespose>> searchHoaDon(int page, int itemsPerPage, String keyFind,
                                                             String loai, Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia,String status) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        String choXacNhan = "Chờ Xác Nhận";
        Page<QuanLyDonHangRespose> HDPage = hoaDonRepository.searchHoaDon(pageable,keyFind,loai,minNgay,maxNgay,minGia,maxGia,status,choXacNhan);
        return PageResponse.<List<QuanLyDonHangRespose>>builder()
                .page(HDPage.getNumber())
                .size(HDPage.getSize())
                .totalPage(HDPage.getTotalPages())
                .items(HDPage.getContent())
                .build();
    }

    public byte[] exportInvoiceToExcel(List<QuanLyDonHangRespose> invoiceList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Hóa Đơn");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Mã Hóa Đơn");
        headerRow.createCell(2).setCellValue("Tên Khách Hàng");
        headerRow.createCell(3).setCellValue("Số Điện Thoại");
        headerRow.createCell(4).setCellValue("Địa Chỉ");
        headerRow.createCell(5).setCellValue("Tổng Tiền");
        headerRow.createCell(6).setCellValue("Loại Hóa Đơn");
        headerRow.createCell(7).setCellValue("Ngày Tạo");
        headerRow.createCell(8).setCellValue("Trạng Thái");

        int rowNum = 1;
        for (QuanLyDonHangRespose invoice : invoiceList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(invoice.getId().toString());
            row.createCell(1).setCellValue(invoice.getMaHD());
            row.createCell(2).setCellValue(invoice.getTenKhachHang());
            row.createCell(3).setCellValue(invoice.getSoDienThoai());
            row.createCell(4).setCellValue(invoice.getDiaChi());

            // Chuyển đổi `BigDecimal` cho cột "Tổng Tiền"
            row.createCell(5).setCellValue(invoice.getTongTien() != null ? invoice.getTongTien().doubleValue() : 0.0);

            row.createCell(6).setCellValue(invoice.getLoaiHoaDon());

            // Định dạng ngày "Ngày Tạo"
            row.createCell(7).setCellValue(invoice.getNgayTao() != null ? invoice.getNgayTao().toString() : "Không xác định");

            row.createCell(8).setCellValue(invoice.getTrangThai());
        }

        // Ghi dữ liệu ra byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }


}
