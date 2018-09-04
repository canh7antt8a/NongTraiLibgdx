package com.leaptechjsc.game.happyfarm.assets;

import com.leaptechjsc.game.happyfarm.nature.F;

public class Language {
    public enum LANGU {
        VN,
        EN,
        CHINA,
        KOREAN,
        KHMER
    }

    public enum General {
        APP_NAME("Nông trại vui vẻ"
                , "Happy farm"),
        SETTING("Cài đặt"
                , "Setting"),
        MUSIC("Nhạc nền"
                , "Music background"),
        SOUND("Nhạc hiệu ứng"
                , "Sound effect"),
        BUY_GAME("Bạn có muốn mua 30.000 xu bằng tin nhắn giá 15.000 đồng không?\nHoặc nhấn \"khác\" để được mua ưu đãi hơn!"
                , "Do you want to purchase 30.000 coins by a message costs only 15.000 vnd?\nOr click \"other\" button to get other promotions"),
        Information("Thông tin"
                , "Information"),
        NOT_ENOUGH_MONEY("Bạn không đủ tiền"
                , "You do not have enough money"),
        NOT_ENOUGH_DIAMON("Bạn không đủ xu. Bạn có muốn mua xu không ?"
                , "You do not have enough coin. Do you want to purchase more coins?"),
        BUY_GAME_OFF_AD("Bạn có muốn mua game với 30.000 xu để tắt quảng cáo không ?"
                , "Do you want to purchase 30.000 coins to turn off ads in game?"),
        EXIT_GAME("Bạn có chắc chắn muốn thoát game đang chơi không?"
                , "Are you sure to exit the game?"),
        SHARE_GAME("Chia sẻ game"
                , "Share this game"),
        SHARE_GAME_USER("Bạn có muốn giới thiệu game này cho người thân không?"
                , "Do you want to recommend this game to your family and friends?"),
        TITLE_SHARE("Chơi game này đi, game này hay lắm"
                , "Let's play this game. It's fun."),
        DOWNLOAD_HOT_GAME("Tải hot game"
                , "Install hot game"),
        WANT_DOWNLOAD_HOT_GAME("Bạn có muốn tải hot game khác không?"
                , "Do you want to install other hot game?"),
        GAME_OTHER("Game khác"
                , "Other games"),
        CLOSE("Đóng"
                , "Close"),
        YES("Có"
                , "Yes"),
        NO("Không"
                , "No"),
        FLOWER("Hoa"
                , "Flower"),
        HOA_DONG_TIEN("Đồng tiền"
                , "Gerbera"),
        HOA_LILY("Lily"
                , "Lily"),
        HOA_SEN("Sen"
                , "Lotus"),
        HOA_TU_CAU("Tú cầu"
                , "Hydrangea"),
        HOA_CAM_CHUONG("Cẩm chướng"
                , "Carnation"),
        HOA_LILY_LOA_KEN("Lily loa ken"
                , "Lily"),
        HOA_TRANG_NGUYEN("Trạng nguyên"
                , "Poinsettia"),
        HOA_HONG("Hồng"
                , "Rose"),
        HOA_HUONG_DUONG("Hướng dương"
                , "Sunflower"),
        HOA_VIOLET("Violet"
                , "Violet"),
        HOA_BO_CONG_ANH("Bồ công anh"
                , "Dandelion"),
        HOA_TULIP("Tulip"
                , "Tulip"),
        BI_NGO("Bí ngô"
                , "Pumpkin"),
        CA_CHUA("Cà chua"
                , "Tomato"),
        CA_TIM("Cà tím"
                , "Eggplant"),
        CAM("Cam"
                , "Orange"),
        CHUOI("Chuối"
                , "Banana"),
        CU_CAI_TRANG("Củ cải trắng"
                , "White radish"),
        DUA_HAU("Dưa hấu"
                , "Watermelon"),
        NHO("Nho"
                , "Grape"),
        TAO("Táo"
                , "Apple"),
        XOAI("Xoài"
                , "Mango"),
        DAU_TAY("Dâu tây"
                , "Strawberry"),
        LE("Lê"
                , "Pear"),
        NHAN_CONG("Nhân công"
                , "Farmer"),
        BAN_BAN_NONG_SAN("Bàn bán nông sản"
                , "Farmer's kiosk"),
        NGOC_MAY_MAN("Ngọc may mắn"
                , "Lucky gem"),
        GIAY_BAO_DAM("Giấy bảo đảm"
                , "Warranty certificate"),
        GIA_MO_GD("Giá mở cửa giao dịch"
                , "The opening price of the transaction"),
        CHUA_DC_MO_TT("Bạn chưa thể mở thị trường này được"
                , "You can not open this market"),
        HTNV_TANG_LO_NUOC("Hoàn thành nhiệm vụ bạn sẽ được tặng \n một lọ nước nâng cấp !"
                , "You will be given an upgrade level bottle \nwhen you complete the mission"),
        TT_TAM_DUNG_GD("Trạng thái: Tạm dừng giao dịch"
                , "Status: Transaction closed"),
        TT_DANG_GD("Trạng thái: Đang giao dịch"
                , "Status: Transaction is progressing"),
        GD_HOAN_THANH("Giao dịch hoàn thành"
                , "Transaction completed"),
        NANG_CAP_TC("Nâng cấp thành công !"
                , "Upgraded successfully!"),
        DAT_LV_MO_DAT("Để mở ô đất hiện tại bạn phải đạt level %d"
                , "You need to get level %d to open a new block"),
        COMMING_SOON("Chức năng này hiện đang được phát triển !"
                , "This function is developing!"),
        MO_RONG_TC("Mở rộng thành công !"
                , "Expansion success!"),
        MO_RONG_O_DAT("Mở rộng ô đất: "
                , "Expansion new block: "),
        HOAN_THANH_NV("Bạn đã hoàn thành nhiệm vụ"
                , "You have completed the mission"),
        NHIEM_VU("Nhiệm vụ"
                , "Mission"),
        DAP_UNG_NC_LT_QG("Đáp ứng nhu cầu kho lương thực quốc gia"
                , "Meet the demand of Nation Food Storage"),
        DAT_SOI("Đất sỏi"
                , "Gravel soil"),
        BUI_CAY_XANH("Bụi cây xanh"
                , "Green bush"),
        BUI_CAY_TRANG("Bụi cây trắng"
                , "White bush"),
        BUI_CAY_VANG("Bụi cây vàng"
                , "Yellow bush"),
        BUI_CAY_DO("Bụi cây đỏ"
                , "Red bush"),
        BUI_CAY_TIM("Bụi cây tím"
                , "Purple bush"),
        NANG_CAP_TC_LEN_CAP("Nâng cấp thành công lên cấp %d!"
                , "Upgraded successfully to level %d!"),
        NANG_CAP_THAT_BAI("Nâng cấp thất bại !"
                , "Upgrade failed!"),
        NANG_CAP_RUONG_D_LEN_CAP("Nâng cấp ruộng %d lên cấp!"
                , "Upgrade farm %d to level!"),
        KHO_CHUA_HAT_GIONG("Kho chứa hạt giống"
                , "Store for seed"),
        THUE_1_NHAN_CONG("Thuê 1 nhân công tự động trồng trọt, chăn nuôi\nthu hoạch. Thuê trong 1 mùa"
                , "Hire 1 farmer to plant, to breed and harvest. Hire in 1 farming season."),
        THEM_1_CHO_BAN("Thêm 1 chỗ bán nông phẩm trong 1 mùa"
                , "Add 1 more kiosk in 1 farming season"),
        NGOC_MM_TANG_TC("Ngọc may mắn tăng thêm 20%\nkhả năng thành công"
                , "Lucky gem to get 20% of successful "),
        NC_TB_VUONG_0_GIAM("Nếu nâng cấp vườn thất bại thì\nvườn không bị giảm cấp"
                , "The farm will not be down level\nfor failing in upgrade level"),
        KHO_CHUA_NONG_PHAM("Kho chứa nông phẩm"
                , "Farm produce storage"),
        CHON_NP_DE_BAN("Chọn nông phẩm để bán"
                , "Choose product to sell"),
        MUA_GAME_TC("Chúc mừng bạn đã mua game thành công !"
                , "Congratulation on purchasing game successfully!"),
        KHONG_DU_XU_MUA_("Bạn không đủ xu để mua game !"
                , "You do not have enough coin to purchase this game!"),
        YEAR("Năm"
                , "Year"),
        CHUNG_CU("Chung cư"
                , "Condominium"),
        CONG_VIEN("Công viên"
                , "Park"),
        CUA_HANG_CAFE("Cửa hàng\nCafe"
                , "Coffee shop"),
        CUA_HANG_FASTFOOD("Cửa hàng\nFastfood"
                , "Fastfood\nRestaurant"),
        KHACH_SAN("Khách sạn"
                , "Hotel"),
        KHO_LUONG_THUC("Kho lương thực"
                , "National Food Storage"),
        NHA_GA("Nhà ga"
                , "Station"),
        NHA_HANG_NHAT("Nhà hàng\nNhật"
                , "Japanese\nRestaurant"),
        TRUONG_HOC("Trường học"
                , "School"),
        VAN_PHONG("Văn phòng"
                , "Office"),
        VUON_HOA("Vườn hoa"
                , "Garden"),
        VUON_THU("Vườn thú"
                , "Zoo"),
        CAP_DO("Cấp độ: "
                , "Level: "),
        HUONG_DAN("Hướng dẫn"
                , "Guide"),
        HUONG_DAN_1("Cửa hàng: nơi bán hạt giống và vật phẩm"
                , "Shop: sell seeds and products"),
        HUONG_DAN_2("Kho chứa: giúp kiểm tra số cây trồng còn lại"
                , "Storage: keeps the plants you have"),
        HUONG_DAN_3("Hạt giống: lấy hạt giống để trồng cây"
                , "Seed: seeds to plant the tree"),
        HUONG_DAN_4("Cái xẻng: giúp nhổ bỏ cây trồng"
                , "Shovel: helps you to root up the trees"),
        HUONG_DAN_5("Người giúp việc: điều chỉnh thứ tự\nưu tiên trồng các loại cây"
                , "Housekeeper: Helps you to arrange\nthe priority position of trees"),
        HUONG_DAN_6("Cuộn giấy: Xem thông tin nhiệm vụ\nhiện tại"
                , "Roll paper: to read the information\nof quests"),
        HUONG_DAN_7("Thị trường sáng màu:\n    có thể giao dịch được"
                , "The bright market:\n    be able to trade"),
        HUONG_DAN_8("Thị trường tối màu:\n     chưa thể giao dich được"
                , "The dark market:\n    Not be able to trade"),
        HUONG_DAN_9("Kho lương thực quốc gia:\n  nhiệm vụ cần làm trước khi\n     hết thời gian quy định"
                , "The National Food Storage:\n  Quest need to be done\n   before running out of time"),
        HUONG_DAN_10("Sau khi mở giao dịch\nbạn có thể tạm ngừng giao dịch nếu cần thiết"
                , "After opening a trade\nyou can pause it (if it is necessary)"),
        TAM_DUNG_MUA_BAN("Tạm dừng\nmua bán"
                , ""),
        TIEP_TUC_MUA_BAN("Tiếp tục\nmua bán"
                , ""),
        NONG_TRAI("Nông trại"
                , "Farm"),
        NONG_TRAI_DA_QUA("Nông trại đã qua %d mùa hạnh phục"
                , "The farm has passed %d the happy season"),
        XD_NT_TO_DEP("Tiếp tục xây dựng nông trại to đẹp hơn nữa!"
                , "Let's keep building a bigger and prettier farm!"),
        LEN_CAP("Lên cấp"
                , "Level up"),
        HT_NHIEM_VU("Hoàn thành nhiệm vụ"
                , "Mission success"),
        DANG_YEU("Đáng yêu"
                , "Cute"),
        GIAU_CO("Giàu có"
                , "Wealth"),
        HIEU_KHACH("Hiếu khách"
                , "Hospitable"),
        VUI_VE("Vui vẻ"
                , "Cheerful"),
        HANH_PHUC("Hạnh phúc"
                , "Happy"),
        THAN_THIEN("Thân thiện"
                , "Nice"),
        TANG_1_NC_THAN("Tặng 1 nước thần"
                , ""),
        QUA_TANG_TAN_GIA("QÙA TẶNG TÂN GIA"
                , ""),
        PHUT_GIAY_VUI_VE("Hi vọng bạn sẽ có\nnhững phút giây\nthật sự vui vẻ !"
                , ""),
        HET_GIO_SD_NHAN_CONG("Hết thời gian sử dụng\nnhân công tự động.\n\n Để sử dụng \t\t\t\t\t\nhân công tự \nđộng bạn hãy vào cửa hàng \nđể mua nhé !"
                , ""),
        NUOC_THAN("Nước thần"
                , ""),
        MUA_XUAN("Mùa xuân"
                , "Spring"),
        MUA_HA("Mùa hạ"
                , "Summer"),
        MUA_THU("Mùa thu"
                , "Autumn"),
        MUA_DONG("Mùa đông"
                , "Winter"),
        TAM_DUNG("Tạm dừng"
                , "Pause"),
        NANG_CAP("Nâng cấp"
                , "Upgrade"),
        CHI_PHI("Chi phí"
                , "Cost"),
        SU_DUNG_THEM("Sử dụng thêm"
                , "Use more"),
        TANG("Tăng"
                , "Up"),
        GIAM("Giảm"
                , "Down"),
        KINH_NGHIEM_KHI_THU("Kinh nghiệm khi thu hoạch"
                , "Experience in harvest"),
        THOI_GIAN_KHI_THU("Thời gian khi thu hoạch"
                , "Time to harvest"),
        MO_RONG_DAT("Mở rộng đất"
                , "Expand the land"),
        MO_RONG("Mở rộng"
                , "Expand"),
        BN_MUON_NHO_CAY("Bạn muốn nhổ cây đi hay không?"
                , "Do you want to root up the tree?"),
        GIA_BAN_NHANH("Giá bán nhanh"
                , ""),
        NHAP_SO_LUONG("Nhập số lượng"
                , "Enter the quantity"),
        TONG_SO_TIEN("Tổng số tiền"
                , "Total Money"),
        NAP_XU("Nạp xu"
                , "Deposit coin"),
        TXT_NHAN("Nhận"
                , "Receive"),
        Confirm("Xác nhận"
                , "Confirm"),
        Accept("Đồng ý"
                , "Accept");

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
