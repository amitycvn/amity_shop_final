package org.example.backend.constants.api;

import static org.example.backend.constants.api.ApiUrl.API_BASE;

public class Admin {
    public static final String BASE = API_BASE + "/admin";
    public static final String BASE_CLIENT = API_BASE + "/client";

    // Endpoint USER
    public static final String USER = BASE + "/user";
    public static final String USER_GET_ALL = USER + "/all";
    public static final String USER_GET_BY_ID = USER + "/{id}";
    public static final String USER_CREATE = USER + "/create";
    public static final String USER_UPDATE = USER + "/update/{id}";
    public static final String USER_UPDATE_HO_SO = "/api/v1/profile/updateHs/{id}";
    public static final String USER_DELETE = USER + "/delete/{id}";
    public static final String USER_SORT = USER + "/sort";
    public static final String PAGE_USER = USER + "/page";
    public static final String USER_GET_BY_NV = USER + "/search";
    public static final String USER_GET_TRANG_THAI = USER + "/status/{userId}";
    public static final String USER_SAVE_EXCEL = USER + "/excel";
    public static final String USER_LOGIN = USER + "/login";
    public static final String USER_REGISTER = USER + "/createUser";
    public static final String USER_UPDATE_PASSWORD = USER + "/updateUser/{id}";
    public static final String USER_SEARCH_EMAIL = USER + "/searchEmail";
    public static final String USER_SEARCH_SDT = USER + "/searchSdt";
    public static final String USER_UPDATE_PASSWORD_BY_EMAIL = USER + "/updatePassword/{email}";




    // Endpoint CUSTOMER
    public static final String CUSTOMER = BASE + "/customer";
    public static final String CUSTOMER_GET_ALL = CUSTOMER + "/all";
    public static final String CUSTOMER_GET_BY_ID = CUSTOMER + "/{id}";
    public static final String CUSTOMER_CREATE = CUSTOMER + "/create";
    public static final String CUSTOMER_CREATE_SELL = CUSTOMER + "/create_sell";
    public static final String CUSTOMER_UPDATE_SELL = CUSTOMER + "/update_sell";
    public static final String CUSTOMER_UPDATE = CUSTOMER + "/update/{id}";
    public static final String CUSTOMER_DELETE = CUSTOMER + "/delete/{id}";
    public static final String CUSTOMER_SORT = CUSTOMER + "/sort";
    public static final String PAGE_CUSTOMER = CUSTOMER + "/page";
    public static final String CUSTOMER_GET_BY_KH = CUSTOMER + "/search";
    public static final String CUSTOMER_GET_BY_SDT = CUSTOMER + "/search/sdt";
    public static final String CUSTOMER_LOGIN = CUSTOMER + "/login";
    public static final String CUSTOMER_UPDATE_DIACHI_BAN_HANG_CLIENT = CUSTOMER + "/update/diaChiBanHangClient/{id}";

    // Endpoint PRODUCT
    public static final String PRODUCT = BASE + "/product";
    public static final String PRODUCT_GET_ALL = PRODUCT + "/all";
    public static final String PRODUCT_GET_BY_ID = PRODUCT + "/{id}";
    public static final String PRODUCT_DETAIL_BY_ID = PRODUCT + "/{id}";
    public static final String PRODUCT_CREATE = PRODUCT + "/create";
    public static final String PRODUCT_UPDATE = PRODUCT + "/update/{id}";
    public static final String PRODUCT_SET_DELETE = PRODUCT + "/delete/{id}";
    public static final String PRODUCT_SEARCH= PRODUCT + "/search";
    public static final String PRODUCT_PAGE= PRODUCT + "/page";



    // Endpoint PRODUCT_DETAIL
    public static final String PRODUCT_DETAIL = BASE + "/product_detail";
    public static final String PRODUCT_DETAIL1 = BASE + "/product_detail1";
    public static final String PRODUCT_DETAIL_GET_ALL = PRODUCT_DETAIL + "/all";
    public static final String PRODUCT_DETAIL_GET_BY_ID = PRODUCT_DETAIL + "/{id}";
    public static final String PRODUCT_DETAIL_GET_BY_ID1 = PRODUCT_DETAIL1 + "/{id}";
    public static final String PRODUCT_DETAIL_CREATE = PRODUCT_DETAIL + "/create/{id}";
    public static final String PRODUCT_DETAIL_UPDATE = PRODUCT_DETAIL + "/update/{id}/{idSanPham}";
    public static final String PRODUCT_DETAIL_SET_DELETE = PRODUCT_DETAIL + "/delete/{id}";
    public static final String PRODUCT_DETAIL_DETAIL = PRODUCT_DETAIL + "/detail/{id}";
    public static final String PRODUCT_DETAIL_SEARCH = PRODUCT_DETAIL + "/search";
    public static final String PRODUCT_DETAIL_SEARCH2 = PRODUCT_DETAIL + "/search2";
    public static final String PRODUCT_DETAIL_PAGE = PRODUCT_DETAIL + "/page";
    public static final String PRODUCT_DETAIL_QR = PRODUCT_DETAIL + "/qrcode/{id}";
    public static final String PRODUCT_DETAIL_QR_TO_CART = PRODUCT_DETAIL + "/qrcode/cart/{id}";
    public static final String PRODUCT_DETAIL_CLIENT= PRODUCT_DETAIL + "/client";



    // Endpoint COMPANY
    public static final String COMPANY = BASE + "/company";
    public static final String COMPANY_GET_ALL = COMPANY + "/all";
    public static final String COMPANY_GET_BY_ID = COMPANY + "/{id}";
    public static final String COMPANY_CREATE = COMPANY + "/create";
    public static final String COMPANY_UPDATE = COMPANY + "/update/{id}";
    public static final String COMPANY_DELETE = COMPANY + "/delete/{id}";
    public static final String COMPANY_SEARCH = COMPANY + "/search";
    public static final String COMPANY_PAGE = COMPANY + "/page";


    // Endpoint BILL
    public static final String BILL = BASE + "/bill";
    public static final String BILL_GET_ALL = BILL + "/all";
    public static final String BILL_GET_BY_ID = BILL + "/{id}";
    public static final String BILL_CREATE = BILL + "/create";
    public static final String BILL_UPDATE = BILL + "/update/{id}";
    public static final String BILL_DELETE = BILL + "/delete/{id}";
    public static final String PAGE_BILL = BILL + "/page";
    public static final String USER_GET_BY_BILL = BILL + "/search";
    public static final String COUT_BY_STATUS_BILL = BILL + "/status";
    public static final String BILL_EXCEL = BILL + "/excel";
    public static final String THONG_KE = BILL + "/thongke";
    public static final String THONG_KE_PRO = BILL + "/thongKePro";
    public static final String THONG_KE_MONTH = BILL + "/month";
    public static final String TOP_5_SP = BILL + "/top5";
    public static final String SP_SAP_HET = BILL + "/sp50";


    // Endpoint BILL_DETAIL
    public static final String BILL_DETAIL = BASE + "/bill_detail";
    public static final String BILL_DETAIL_GET_ALL = BILL_DETAIL + "/all";
    public static final String BILL_DETAIL_GET_BY_ID = BILL_DETAIL + "/{id}";
    public static final String BILL_DETAIL_GET_BY_MA = BILL_DETAIL ;
    public static final String BILL_DETAIL_CREATE = BILL_DETAIL + "/create";
    public static final String BILL_DETAIL_UPDATE = BILL_DETAIL + "/update/{id}";
    public static final String BILL_DETAIL_DELETE = BILL_DETAIL + "/delete/{id}";
    // Endpoint VOUCHER
    public static final String VOUCHER = BASE + "/voucher";
    public static final String VOUCHER_GET_ALL = VOUCHER + "/all";
    public static final String VOUCHER_GET_ALL_ADMIN = VOUCHER + "/all/admin";
    public static final String VOUCHER_GET_BY_ID = VOUCHER + "/{id}";
    public static final String VOUCHER_GET_BY_ID_KH = VOUCHER + "/kh/{id}";
    public static final String VOUCHER_CREATE = VOUCHER + "/create";
    public static final String VOUCHER_UPDATE = VOUCHER + "/update/{id}";
    public static final String VOUCHER_DELETE = VOUCHER + "/delete/{id}";
    public static final String VOUCHER_UPDATE_STATUS = VOUCHER + "/updateStatus/{id}";
    public static final String VOUCHER_SEARCH = VOUCHER + "/search/{find}&{filterType}";
    public static final String VOUCHER_EXCEL = VOUCHER + "/excel";
    public static final String VOUCHER_CREATE_PGG_KH = VOUCHER + "/create-phieu-giam-gia-nguoi-dung";
    public static final String VOUCHER_SET_DA_SU_DUNG = VOUCHER + "/set-da-su-dung";

    // Endpoint SALE
    public static final String SALE = BASE + "/sale";
    public static final String SALE_GET_ALL = SALE + "/all";
    public static final String SALE_PRODUCT_DETAIL_GET_ALL = SALE + "/product_detail";
    public static final String SALE_GET_BY_ID = SALE + "/{id}";
    public static final String SALE_CREATE = SALE + "/create";
    public static final String SALE_UPDATE = SALE + "/update/{id}";
    public static final String SALE_DELETE = SALE + "/delete/{id}";
    public static final String SALE_SET_DELETE = SALE + "/deleted/{id}";
    public static final String SALE_SEARCH_VALUE = SALE + "/search";
    public static final String SALE_GET_ALL_CLIENT = BASE_CLIENT + "/sale/all";

    // Endpoint SALE_DETAIL
    public static final String SALE_DETAIL = BASE + "/sale-detail";
    public static final String SALE_DETAIL_GET_ALL = SALE_DETAIL + "/all";
    public static final String SALE_DETAIL_GET_BY_ID = SALE_DETAIL + "/{id}";
    public static final String SALE_DETAIL_CREATE = SALE_DETAIL + "/create";
    public static final String SALE_DETAIL_UPDATE = SALE_DETAIL + "/update/{id}";
    public static final String SALE_DETAIL_DELETE = SALE_DETAIL + "/delete/{id}";
    public static final String SALE_DETAIL_SET_DELETE = SALE_DETAIL + "/deleted/{id}";
    public static final String SALE_DETAIL_GET_BY_ID_DGG = SALE_DETAIL + "/sale/{id}";
    public static final String SALE_DETAIL_GET_BY_ID_SPCT = SALE_DETAIL + "/product_detail/{id}";

    // Endpoint MATERIAL
    public static final String MATERIAL = BASE + "/material";
    public static final String MATERIAL_GET_ALL = MATERIAL + "/all";
    public static final String MATERIAL_GET_BY_ID = MATERIAL + "/{id}";
    public static final String MATERIAL_CREATE = MATERIAL + "/create";
    public static final String MATERIAL_SET_UPDATE = MATERIAL + "/update/{id}";
    public static final String MATERIAL_DELETE = MATERIAL + "/delete/{id}";
    public static final String MATERIAL_SEARCH = MATERIAL + "/search";
    public static final String MATERIAL_PAGE = MATERIAL + "/page";

    // Endpoint LINING
    public static final String LINING = BASE + "/lining";
    public static final String LINING_GET_ALL = LINING + "/all";
    public static final String LINING_GET_BY_ID = LINING + "/{id}";
    public static final String LINING_CREATE = LINING + "/create";
    public static final String LINING_SET_UPDATE = LINING + "/update/{id}";
    public static final String LINING_DELETE = LINING + "/delete/{id}";
    public static final String LINING_SEARCH = LINING + "/search";
    public static final String LINING_PAGE = LINING + "/page";
    // Endpoint LIST
    public static final String LIST = BASE + "/list";
    public static final String LIST_GET_ALL = LIST + "/all";
    public static final String LIST_GET_BY_ID = LIST + "/{id}";
    public static final String LIST_CREATE = LIST + "/create";
    public static final String LIST_SET_UPDATE = LIST + "/update/{id}";
    public static final String LIST_DELETE = LIST + "/delete/{id}";
    public static final String LIST_SEARCH = LIST + "/search";
    public static final String LIST_PAGE = LIST + "/page";

    // Endpoint SOLE
    public static final String SOLE = BASE + "/sole";
    public static final String SOLE_GET_ALL = SOLE + "/all";
    public static final String SOLE_GET_BY_ID = SOLE + "/{id}";
    public static final String SOLE_CREATE = SOLE + "/create";
    public static final String SOLE_SET_UPDATE = SOLE + "/update/{id}";
    public static final String SOLE_DELETE = SOLE + "/delete/{id}";
    public static final String SOLE_SEARCH = SOLE + "/search";
    public static final String SOLE_PAGE = SOLE + "/page";

    // Endpoint ADDRESS
    public static final String ADDRESS = BASE + "/address";
    public static final String ADDRESS_GET_ALL = ADDRESS + "/all";
    public static final String ADDRESS_GET_BY_ID = ADDRESS + "/{id}";
    public static final String ADDRESS_CREATE = ADDRESS + "/create";
    public static final String ADDRESS_UPDATE = ADDRESS + "/update/{id}";
    public static final String ADDRESS_DELETE = ADDRESS + "/delete/{id}";

    // Endpoint CART
    public static final String CART = BASE + "/cart";
    public static final String CART_GET_ALL = CART + "/all";
    public static final String CART_GET_BY_ID = CART + "/{id}";
    public static final String CART_CREATE = CART + "/create";
    public static final String CART_UPDATE = CART + "/update/{id}";
    public static final String CART_DELETE = CART + "/delete";

    // Endpoint CART_DETAIL
    public static final String CART_DETAIL = BASE + "/cart-detail";
    public static final String CART_DETAIL_GET_ALL = CART_DETAIL + "/all";
    public static final String CART_DETAIL_GET_BY_ID = CART_DETAIL + "/{id}";
    public static final String CART_DETAIL_CREATE = CART_DETAIL + "/create";
    public static final String CART_DETAIL_UPDATE = CART_DETAIL + "/update/{id}";
    public static final String CART_DETAIL_DELETE = CART_DETAIL + "/delete/{id}";

    // Endpoint BRAND
    public static final String BRAND = BASE + "/brand";
    public static final String BRAND_GET_ALL = BRAND + "/all";
    public static final String BRAND_GET_BY_ID = BRAND + "/{id}";
    public static final String BRAND_CREATE = BRAND + "/create";
    public static final String BRAND_UPDATE = BRAND + "/update/{id}";
    public static final String BRAND_DELETE = BRAND + "/delete/{id}";

    // Endpoint IMAGE
    public static final String IMAGE = BASE + "/image";
    public static final String IMAGE_GET_ALL = IMAGE + "/all";
    public static final String IMAGE_GET_BY_ID = IMAGE + "/{idSanPham}";
    public static final String IMAGE_CREATE = IMAGE + "/create";
    public static final String IMAGE_SET_UPDATE = IMAGE + "/update/{id}";
    public static final String IMAGE_DELETE = IMAGE + "/delete/{id}";

    // Endpoint SIZE
    public static final String SIZE = BASE + "/size";
    public static final String SIZE_GET_ALL = SIZE + "/all";
    public static final String SIZE_GET_BY_ID = SIZE + "/{id}";
    public static final String SIZE_CREATE = SIZE + "/create";
    public static final String SIZE_UPDATE = SIZE + "/update/{id}";
    public static final String SIZE_DELETE = SIZE + "/delete/{id}";
    public static final String SIZE_SEARCH = SIZE + "/search";
    public static final String SIZE_PAGE = SIZE + "/page";

    // Endpoint BILL_HISTORY
    public static final String BILL_HISTORY = BASE + "/bill-history";
    public static final String BILL_HISTORY_GET_ALL = BILL_HISTORY + "/all";
    public static final String BILL_HISTORY_GET_BY_ID = BILL_HISTORY + "/{id}";
    public static final String BILL_HISTORY_CREATE = BILL_HISTORY + "/create";
    public static final String BILL_HISTORY_UPDATE = BILL_HISTORY + "/update/{id}";
    public static final String BILL_HISTORY_DELETE = BILL_HISTORY + "/delete/{id}";

    // Endpoint COLOR
    public static final String COLOR = BASE + "/color";
    public static final String COLOR_GET_ALL = COLOR + "/all";
    public static final String COLOR_GET_BY_ID = COLOR + "/{id}";
    public static final String COLOR_CREATE = COLOR + "/create";
    public static final String COLOR_UPDATE = COLOR + "/update/{id}";
    public static final String COLOR_DELETE = COLOR + "/delete/{id}";
    public static final String COLOR_SEARCH = COLOR + "/search";
    public static final String COLOR_PAGE = COLOR + "/page";

    // Endpoint PAY
    public static final String PAY = BASE + "/pay";
    public static final String PAY_GET_ALL = PAY + "/all";
    public static final String PAY_GET_BY_ID = PAY + "/{id}";
    public static final String PAY_CREATE = PAY + "/create";
    public static final String PAY_CREATE_V2 = PAY + "/createv2";
    public static final String PAY_UPDATE = PAY + "/update/{id}";
    public static final String PAY_DELETE = PAY + "/delete/{id}";

    // Endpoint NOTIFICATION
    public static final String NOTIFICATION = BASE + "/notification";
    public static final String NOTIFICATION_GET_ALL = NOTIFICATION + "/all";
    public static final String NOTIFICATION_GET_BY_ID = NOTIFICATION + "/{id}";
    public static final String NOTIFICATION_CREATE = NOTIFICATION + "/create";
    public static final String NOTIFICATION_UPDATE = NOTIFICATION + "/update/{id}";
    public static final String NOTIFICATION_DELETE = NOTIFICATION + "/delete/{id}";

    // Endpoint PRODUCT_RETURN
    public static final String PRODUCT_RETURN = BASE + "/product-return";
    public static final String PRODUCT_RETURN_GET_ALL = PRODUCT_RETURN + "/all";
    public static final String PRODUCT_RETURN_GET_BY_ID = PRODUCT_RETURN + "/{id}";
    public static final String PRODUCT_RETURN_CREATE = PRODUCT_RETURN + "/create";
    public static final String PRODUCT_RETURN_UPDATE = PRODUCT_RETURN + "/update";
    public static final String PRODUCT_RETURN_DELETE = PRODUCT_RETURN + "/delete/{id}";
    //tra hang client
    public static final String PRODUCT_RETURN_CLIENT = BASE_CLIENT + "/product-return";
    public static final String PRODUCT_RETURN_GET_ALL_CLIENT = PRODUCT_RETURN_CLIENT + "/all";
    public static final String PRODUCT_RETURN_CREATE_CLIENT = PRODUCT_RETURN_CLIENT + "/create";
    public static final String PRODUCT_RETURN_UPDATE_CLIENT = PRODUCT_RETURN_CLIENT + "/update/{id}";
    public static final String PRODUCT_RETURN_GET_BY_IDKH = PRODUCT_RETURN_CLIENT + "/bill/{idNguoiDung}";
    public static final String PRODUCT_RETURN_DETAIL_BY_ID = PRODUCT_RETURN_CLIENT + "/bill-detail/{id}";



    public static final String SELL = BASE + "/sell";
    public static final String SELL_GET_ALL = SELL + "/all";
    public static final String SELL_GET_BY_ID = SELL + "/{id}";
    public static final String SELL_CREATE = SELL + "/create";
    public static final String SELL_CLIENT_CREATE = BASE_CLIENT + "/sell/create";
    public static final String SELL_UPDATE = SELL + "/update/hd";
    public static final String SELL_SET_DELETE = SELL + "/update/{id}";
    public static final String SELL_QR = SELL + "/generate";



    public static final String SELL_DETAIL = BASE + "/sell_detail";
    public static final String SELL_DETAIL_GET_ALL = SELL_DETAIL + "/all";
    public static final String SELL_DETAIL_GET_BY_ID = SELL_DETAIL + "/{id}";
    public static final String SELL_DETAIL_CREATE = SELL_DETAIL + "/create";
    public static final String SELL_DETAIL_UPDATE = SELL_DETAIL + "/update/{id}";
    public static final String SELL_DETAIL_SET_DELETE = SELL_DETAIL + "/update/{id}";
    public static final String SELL_DETAIL_QR = SELL_DETAIL + "/generate-qr";
    public static final String SELL_DETAIL_QR_COMPLETE = SELL_DETAIL + "/generate-qr/complete";

    public static final String SELL_ORDER = BASE + "/sell/order";
    public static final String SELL_ORDER_PRODUCT_COMPLETE = SELL_ORDER + "/complete/{id}";
    public static final String SELL_ORDER_VOUCHER_COMPLETE = SELL_ORDER + "/complete/voucher/{id}";

    public static final String SELL_CLIENT = BASE + "/sellClient";
    public static final String SELL_CLIENT_GET_ALL = SELL_CLIENT + "/all";
    public static final String SELL_CLIENT_GET_BY_ID_DGG = SELL_CLIENT + "/all/{id}";
    public static final String SELL_CLIENT_SEARCH_1 = BASE_CLIENT+"/sellClient" + "/search";
    public static final String SELL_CLIENT_SEARCH = SELL_CLIENT + "/client/search";
    public static final String SELL_CLIENT_TOP = BASE_CLIENT + "/client/top5";
    public static final String SELL_CLIENT_SALE_SP = BASE_CLIENT + "/client/sale/sp";
    public static final String SELL_CLIENT_GET_BY_ID_SPCT = BASE_CLIENT + "/sale/spct";
    public static final String SELL_CLIENT_CHECK_THONG_TIN_HOA_DON = BASE_CLIENT + "/sellClient/checkThongTinHoaDon";

    public static final String SELL_CLIENT_SET_PHUONG_THUC_THANH_TOAN =  BASE_CLIENT + "/sellClient/PhuongThucThanhToan";
    public static final String SELL_CLIENT_SET_LICH_SU_HOA_DON = BASE_CLIENT + "/sellClient/LichSuHoaDon";
    public static final String SELL_CLIENT_GET_LICH_SU_HOA_DON = BASE_CLIENT + "/sellClient/getLichSuHoaDon/{id}";
    public static final String SELL_CLIENT_GET_PHUONG_THUC_THANH_TOAN = BASE_CLIENT + "/sellClient/getPhuongThucThanhToan/{id}";
    public static final String SELL_CLIENT_HOAN_LAI_SO_LUONG_SP = BASE_CLIENT + "/sellClient/hoanLaiSoLuongSP";
}
