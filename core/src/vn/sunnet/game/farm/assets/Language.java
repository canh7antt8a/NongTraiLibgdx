package vn.sunnet.game.farm.assets;

import vn.sunnet.game.farm.nature.F;

public class Language {
    public enum LANGU {
        VN,
        EN
    }

    public enum General {
        LAN("VN", "EN"),
        APP_NAME("Nông trại vui vẻ", "Happy farm"),
        SETTING("Cài đặt", "Setting"),
        MUSIC("Nhạc nền", "Music background"),
        SOUND("Nhạc hiệu ứng", "Sound effect"),
        BUY_GAME("\"Bạn có muốn mua 30.000 xu bằng tin nhắn giá 15.000 đồng không?\nHoặc nhấn \"\"khác\"\" để được mua ưu đãi hơn!", "Do you want to purchase 30.000 coins by a message costs only 15.000 vnd?\nOr click \"other\" button to get other promotions"),
        Information("Thông tin", "Information"),
        NOT_ENOUGH_MONEY("Bạn không đủ tiền", "You do not have enough money"),
        NOT_ENOUGH_DIAMON("Bạn không đủ xu. Bạn có muốn mua xu không ?", "You do not have enough coin. Do you want to purchase more coins?"),
        BUY_GAME_OFF_AD("Bạn có muốn mua game với 30.000 xu để tắt quảng cáo không ?", "Do you want to purchase 30.000 coins to turn off ads in game?"),
        EXIT_GAME("Bạn có chắc chắn muốn thoát game đang chơi không?", "Are you sure to exit the game?"),
        SHARE_GAME("Chia sẻ game", "Share this game"),
        SHARE_GAME_USER("Bạn có muốn giới thiệu game này cho người thân không?", "Do you want to recommend this game to your family and friends?"),
        TITLE_SHARE("Chơi game này đi, game này hay lắm", "Let's play this game. It's fun."),
        DOWNLOAD_HOT_GAME("Tải hot game", "Install hot game"),
        WANT_DOWNLOAD_HOT_GAME("Bạn có muốn tải hot game khác không?", "Do you want to install other hot game?"),
        GAME_OTHER("Game khác", "Other games"),
        CLOSE("Đóng", "Close"),
        YES("Có", "Yes"),
        NO("Không", "No"),
        FLOWER("Hoa", "Flower"),
        HOA_DONG_TIEN("Đồng tiền", "Gerbera"),
        HOA_LILY("Lily", "Lily"),
        HOA_SEN("Sen", "Lotus"),
        HOA_TU_CAU("Tú cầu", "Hydrangea"),
        HOA_CAM_CHUONG("Cẩm chướng", "Carnation"),
        HOA_LILY_LOA_KEN("Lily loa ken", "Lily"),
        HOA_TRANG_NGUYEN("Trạng nguyên", "Poinsettia"),
        HOA_HONG("Hồng", "Rose"),
        HOA_HUONG_DUONG("Hướng dương", "Sunflower"),
        HOA_VIOLET("Violet", "Violet"),
        HOA_BO_CONG_ANH("Bồ công anh", "Dandelion"),
        HOA_TULIP("Tulip", "Tulip"),
        BI_NGO("Bí ngô", "Pumpkin"),
        CA_CHUA("Cà chua", "Tomato"),
        CA_TIM("Cà tím", "Eggplant"),
        CAM("Cam", "Orange"),
        CHUOI("Chuối", "Banana"),
        CU_CAI_TRANG("Củ cải trắng", "White radish"),
        DUA_HAU("Dưa hấu", "Watermelon"),
        NHO("Nho", "Grape"),
        TAO("Táo", "Apple"),
        XOAI("Xoài", "Mango"),
        DAU_TAY("Dâu tây", "Strawberry"),
        LE("Lê", "Pear"),
        NHAN_CONG("Nhân công", "Farmer"),
        BAN_BAN_NONG_SAN("Bàn bán nông sản", "Farmer's kiosk"),
        NGOC_MAY_MAN("Ngọc may mắn", "Lucky gem"),
        GIAY_BAO_DAM("Giấy bảo đảm", "Warranty certificate"),
        GIA_MO_GD("Giá mở cửa giao dịch", "The opening price of the transaction"),
        CHUA_DC_MO_TT("Bạn chưa thể mở thị trường này được", "You can not open this market"),
        HTNV_TANG_LO_NUOC("Hoàn thành nhiệm vụ bạn sẽ được tặng \n một lọ nước nâng cấp !", "You will be given an upgrade level bottle when you complete the mission"),
        TT_TAM_DUNG_GD("Trạng thái: Tạm dừng giao dịch", "Status: Transaction closed"),
        TT_DANG_GD("Trạng thái: Đang giao dịch", "Status: Transaction is progressing"),
        GD_HOAN_THANH("Giao dịch hoàn thành", "Transaction completed"),
        NANG_CAP_TC("Nâng cấp thành công !", "Upgraded successfully!"),
        DAT_LV_MO_DAT("Để mở ô đất hiện tại bạn phải đạt level %d", "You need to get level % to open a new block"),
        COMMING_SOON("Chức năng này hiện đang được phát triển !", "This function is developing!"),
        MO_RONG_TC("Mở rộng thành công !", "Expansion success!"),
        MO_RONG_O_DAT("Mở rộng ô đất: ", "Expansion new block: "),
        HOAN_THANH_NV("Bạn đã hoàn thành nhiệm vụ", "You have completed the mission"),
        NHIEM_VU("Nhiệm vụ", "Mission"),
        DAP_UNG_NC_LT_QG("Đáp ứng nhu cầu kho lương thực quốc gia", "Meet the demand of Nation Food Storage"),
        DAT_SOI("Đất sỏi", "Gravel soil"),
        BUI_CAY_XANH("Bụi cây xanh", "Green bush "),
        BUI_CAY_TRANG("Bụi cây trắng", "White bush "),
        BUI_CAY_VANG("Bụi cây vàng", "Yellow bush"),
        BUI_CAY_DO("Bụi cây đỏ", "Red bush"),
        BUI_CAY_TIM("Bụi cây tím", "Purple bush"),
        NANG_CAP_TC_LEN_CAP("Nâng cấp thành công lên cấp %d!", "Upgraded successfully to level %d!"),
        NANG_CAP_THAT_BAI("Nâng cấp thất bại !", "Upgrade failed!"),
        NANG_CAP_RUONG_D_LEN_CAP("Nâng cấp ruộng %d lên cấp!", "Upgrade farm %d to level!"),
        KHO_CHUA_HAT_GIONG("Kho chứa hạt giống", "Store for seed"),
        THUE_1_NHAN_CONG("Thuê 1 nhân công tự động trồng trọt, chăn nuôi\nthu hoạch. Thuê trong 1 mùa", "Hire 1 farmer to plant, to breed and harvest. Hire in 1 farming season."),
        THEM_1_CHO_BAN("Thêm 1 chỗ bán nông phẩm trong 1 mùa", "Add 1 more kiosk in 1 farming season "),
        NGOC_MM_TANG_TC("Ngọc may mắn tăng thêm 20%\nkhả năng thành công", "Lucky gem to get 20% of successful "),
        NC_TB_VUONG_0_GIAM("Nếu nâng cấp vườn thất bại thì\nvườn không bị giảm cấp", "The farm will not be down level for failing in upgrade level "),
        KHO_CHUA_NONG_PHAM("Kho chứa nông phẩm", "Farm produce storage"),
        CHON_NP_DE_BAN("Chọn nông phẩm để bán", "Choose product to sell"),
        MUA_GAME_TC("Chúc mừng bạn đã mua game thành công !", "Congratulation on purchasing game successfully!"),
        KHONG_DU_XU_MUA_("Bạn không đủ xu để mua game !", "You do not have enough coin to purchase this game!"),
        YEAR("Năm", "Year"),
//        BAT_DAU_MUA_BAN("Bắt đầu mua bán", ""),
        CHUNG_CU("Chung cư", "Apartment"),
        CONG_VIEN("Công viên", "Park"),
        CUA_HANG_CAFE("Cửa hàng\nCafe", "Coffe"),
        CUA_HANG_FASTFOOD("Cửa hàng\nFastfood", "Fastfood"),
        KHACH_SAN("Khách sạn", "Hotel"),
        KHO_LUONG_THUC("Kho lương thực", "Food Warehouse"),
        NHA_GA("Nhà ga", "Terminal"),
        NHA_HANG_NHAT("Nhà hàng\nNhật", "Japan Restaurant"),
        TRUONG_HOC("Trường học", "School"),
        VAN_PHONG("Văn phòng", "Office"),
        VUON_HOA("Vườn hoa", "Flower Garden"),
        VUON_THU("Vườn thú", "Zoo"),
        CAP_DO("Cấp độ: ", "Level: "),
        HUONG_DAN("Hướng dẫn", "Guide"),
        HUONG_DAN_1("Cửa hàng: nơi bán hạt giống và vật phẩm", ""),
        HUONG_DAN_2("Kho chứa: giúp kiểm tra số cây trồng còn lại", ""),
        HUONG_DAN_3("Hạt giống: lấy hạt giống để trồng cây", ""),
        HUONG_DAN_4("Cái xẻng: giúp nhổ bỏ cây trồng", ""),
        HUONG_DAN_5("Người giúp việc: điều chỉnh thứ tự\nưu tiên trồng các loại cây", ""),
        HUONG_DAN_6("Cuộn giấy: Xem thông tin nhiệm vụ\nhiện tại", ""),
        HUONG_DAN_7("Thị trường sáng màu:\n    có thể giao dịch được", ""),
        HUONG_DAN_8("Thị trường tối màu:\n     chưa thể giao dich được", ""),
        HUONG_DAN_9("Kho lương thực quốc gia:\n  nhiệm vụ cần làm trước khi\n     hết thời gian quy định", ""),
        HUONG_DAN_10("Sau khi mở giao dịch\nbạn có thể tạm ngừng giao dịch nếu cần thiết", ""),

        TAM_DUNG_MUA_BAN("Tạm dừng\nmua bán", ""),
        TIEP_TUC_MUA_BAN("Tiếp tục\nmua bán", ""),


        //message
        NONG_TRAI("Nông trại", "Farm"),
        NONG_TRAI_DA_QUA("Nông trại đã qua %d mùa hạnh phục", ""),
        XD_NT_TO_DEP("Tiếp tục xây dựng nông trại to đẹp hơn nữa!", ""),
        LEN_CAP("Lên cấp", "Level up"),
        HT_NHIEM_VU("Hoàn thành nhiệm vụ", "Mission success"),
        DANG_YEU("Đáng yêu", "Lovely"),
        GIAU_CO("Giàu có", "Rich"),
        HIEU_KHACH("Hiếu khách", ""),
        VUI_VE("Vui vẻ", "Happy"),
        HANH_PHUC("Hạnh phúc", ""),
        THAN_THIEN("Thân thiện", ""),

        TANG_1_NC_THAN("Tặng 1 nước thần", ""),
        QUA_TANG_TAN_GIA("QÙA TẶNG TÂN GIA", ""),
        PHUT_GIAY_VUI_VE("Hi vọng bạn sẽ có\nnhững phút giây\nthật sự vui vẻ !", ""),
        HET_GIO_SD_NHAN_CONG("\"Hết thời gian sử dụng\nnhân công tự động.\n\n Để sử dụng \t\t\t\t\t\nhân công tự \nđộng bạn hãy vào cửa hàng \nđể mua nhé !", ""),
        NUOC_THAN("Nước thần", ""),

        MUA_XUAN("Mùa xuân", "Spring"),
        MUA_HA("Mùa hạ", "Summer"),
        MUA_THU("Mùa thu", ""),
        MUA_DONG("Mùa đông", ""),

        TAM_DUNG("Tạm dừng", ""),
        NANG_CAP("Nâng cấp", ""),
        CHI_PHI("Chi phí", ""),
        SU_DUNG_THEM("Sử dụng thêm", ""),
        TANG("Tăng", ""),
        GIAM("Giảm", ""),
        KINH_NGHIEM_KHI_THU("Kinh nghiệm khi thu hoạch", ""),
        THOI_GIAN_KHI_THU("Thời gian khi thu hoạch", ""),

        MO_RONG_DAT("Mở rộng đất", ""),
        MO_RONG("Mở rộng", ""),
        BN_MUON_NHO_CAY("Bạn muốn nhổ cây đi hay không?", ""),

        GIA_BAN_NHANH("Giá bán nhanh", ""),
        NHAP_SO_LUONG("Nhập số lượng", ""),
        TONG_SO_TIEN("Tổng số tiền", ""),
        NAP_XU("Nạp xu", ""),
//        YEAR("Năm", ""),
//        YEAR("Năm", ""),
//        YEAR("Năm", ""),
//        YEAR("Năm", ""),
//        YEAR("Năm", ""),
//        YEAR("Năm", ""),
        TXT_NHAN("Nhận", ""),
        Confirm("Xác nhận", "Confirm"),
        Accept("Đồng ý", "Accept");

        String languageVN, languageEn;

        General(String strVn, String strEn) {
            this.languageVN = strVn;
            this.languageEn = strEn;
        }

        public String getStr() {
            switch (F.language) {
                case EN:
                    return languageEn;
                default:
                    return languageVN;
            }
        }

    }


}
