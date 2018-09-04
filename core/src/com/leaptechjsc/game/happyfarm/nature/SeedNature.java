package com.leaptechjsc.game.happyfarm.nature;


import com.leaptechjsc.game.happyfarm.assets.Language;

public class SeedNature {

	public static final int DONG_TIEN = 0, HOA_CAM_CHUONG = 1, HOA_SEN = 2, HYDRANGEA = 3, LILY = 4, 
			LYLY_LOA_KEN = 5, POINSENTIA = 6, ROSE = 7, SUNFLOWER = 8, VIOLET = 9;
	public static final int BI_NGO = 0, CA_CHUA = 1, CA_TIM = 2, CAM = 3, CHUOI = 4, CU_CAI = 5,
			DUA_HAU = 6, NHO = 7, TAO = 8, XOAI = 9;
	public static String flname[] = {
			Language.General.HOA_DONG_TIEN.getStr()
			, Language.General.HOA_LILY.getStr()
			, Language.General.HOA_SEN.getStr()
			, Language.General.HOA_TU_CAU.getStr()
			, Language.General.HOA_CAM_CHUONG.getStr()
			, Language.General.HOA_LILY_LOA_KEN.getStr()
			, Language.General.HOA_TRANG_NGUYEN.getStr()
			, Language.General.HOA_HONG.getStr()
			, Language.General.HOA_HUONG_DUONG.getStr()
			, Language.General.HOA_VIOLET.getStr()
			, Language.General.HOA_BO_CONG_ANH.getStr(),
			Language.General.HOA_TULIP.getStr()};
	public static String flname_[] = {
			Language.General.HOA_DONG_TIEN.getStr()
			, Language.General.HOA_LILY.getStr()
			, Language.General.HOA_SEN.getStr()
			, Language.General.HOA_TU_CAU.getStr()
			, Language.General.HOA_CAM_CHUONG.getStr()
			, Language.General.HOA_LILY_LOA_KEN.getStr()
			, Language.General.HOA_TRANG_NGUYEN.getStr()
			, Language.General.HOA_HONG.getStr()
			, Language.General.HOA_HUONG_DUONG.getStr()
			, Language.General.HOA_VIOLET.getStr()
			, Language.General.HOA_BO_CONG_ANH.getStr(),
			Language.General.HOA_TULIP.getStr()};
	//{"Đồng tiền", "Lily", "Sen", "Tú cầu", "Cẩm chướng", "Lily loa ken",
//		"Trạng nguyên", "Hoa hồng", "Hướng dương", "Violet", "Bồ công anh", "Tulip"};
	
	public static String frname[] = {
			Language.General.BI_NGO.getStr()
			, Language.General.CA_CHUA.getStr()
			, Language.General.CA_TIM.getStr()
			, Language.General.CAM.getStr()
			, Language.General.CHUOI.getStr()
			, Language.General.CU_CAI_TRANG.getStr()
			, Language.General.DUA_HAU.getStr()
			, Language.General.NHO.getStr()
			, Language.General.TAO.getStr()
			, Language.General.XOAI.getStr()
			, Language.General.DAU_TAY.getStr(),
			Language.General.LE.getStr()};//{"Bí ngô", "Cà chua", "Cà tím", "Cam", "Chuối", "Củ cải trắng", "Dưa hấu",
//		"Nho", "Táo", "Xoài", "Dâu tây", "Lê"};

	public static String flower[] = {"dong-tien", "lily", "hoa-sen", "tu-cau", "cam-chuong",
			"lily-loa-ken", "trang-nguyen", "hoa-hong", "huong-duong", "violet", "bo-cong-anh", "tulip"};
	public static String fruit[] = {"bi-ngo", "ca-chua", "ca-tim", "cam", "chuoi", "cu-cai", "dua-hau", 
			"nho", "tao", "xoai", "dau-tay", "le"};
	public static String tool[] = {"nhan-cong", "ban", "ngoc-may-man", "giay-bao-dam"};
	public static String tool_name[] = {
			Language.General.NHAN_CONG.getStr()
			, Language.General.BAN_BAN_NONG_SAN.getStr()
			, Language.General.NGOC_MAY_MAN.getStr()
			, Language.General.GIAY_BAO_DAM.getStr()};//{"Nhân công", "Bàn bán nông sản", "Ngọc may mắn", "Giấy bảo đảm"};
	public static String fertilizer[] = {"phan-bon-vang", "phan-bon-xanh", "phan-bon-do"};
	
	public static int index = 0;
	public static final int max_seed = 9;
	public static final boolean LOCK = false, UNLOCK = true;
	public static int[] fllevel_unlock = {1, 5, 10, 16, 23, 31, 40, 52, 61, 73, 86, 100};
	public static int[] frlevel_unlock = {1, 3, 8, 13, 20, 27, 36, 46, 57, 67, 80, 93};
		
	private static int flgcost[] = {4, 6, 11, 20, 32, 53, 91, 166, 329, 723, 1778, 4928};
	private static int flxcost[] = {1, 1, 2, 3, 4, 6, 10, 17, 33, 73, 178, 493};
	private static int frgcost[] = {8, 7, 11, 18, 29, 45, 73, 125, 230, 466, 1050, 2661};
	private static int frxcost[] = {1, 1, 2, 2, 3, 5, 8, 13, 24, 47, 105, 267};
	
	private static int flvalue[] = {14, 21, 39, 69, 113, 186, 318, 581, 1152, 2530, 6224, 7248};	
	private static int frvalue[] = {28, 25, 29, 63, 101, 158, 256, 437, 806, 1631, 3676, 9314};
	private static int flexperience[] = {30, 45, 84, 146, 241, 398, 679, 1242, 2465, 5413, 13318, 36905};
	private static int frexperience[] = {9, 34, 75, 131, 212, 336, 545, 932, 1721, 3481, 7849, 19887};
	public static int ftlzvalue[] = {10, 20, 30};
	
	private static float[][] flgrowth_period = {{10, 10, 10}, {15, 15, 18}, {25, 25, 28}, {42, 42, 42},
		{64, 64, 64}, {96, 96, 96}, {136, 136, 136}, {188, 188, 188}, {248, 248, 248}, {322, 322, 322},
		{408, 408, 408}, {508, 508, 508}};
	private static float[][] frgrowth_period = {{10, 10, 10}, {14, 14, 14}, {22, 22, 22}, {34, 34, 34},
		{54, 54, 54}, {82, 82, 82}, {118, 118, 118}, {164, 164, 164}, {220, 220, 220}, {288, 288, 288},
		{368, 368, 368}, {460, 460, 460}};
	
	public static int getflgrowthTime(int kind) {
		int time = 0;
		for(int i = 0; i < 3; i++)
			time += flgrowth_period[kind][i]; 
		return time;
	}
	
	public static int getfrgrowthTime(int kind) {
		int time = 0;
		for(int i = 0; i < 3; i++)
			time += frgrowth_period[kind][i]; 
		return time;
	}
	
	public static int getflowerUnlock(int kind) {
		return fllevel_unlock[kind];
	}
	
	public static int getfruitUnlock(int kind) {
		return frlevel_unlock[kind];
	}
	
	public static float[] getflowerNature(int kind) {
		float[] flgowth = new float[3];
		for(int i = 0; i < 3; i++)
			flgowth[i] = flgrowth_period[kind][i];
		return flgowth;
	}
	
	public static float[] getfruitNature(int kind) {
		float[] frgowth = new float[3];
		for(int i = 0; i < 3; i++)
			frgowth[i] = frgrowth_period[kind][i];
		return frgowth;
	}
	
	public static int getgflowerCost(int kind) {
		return flgcost[kind];
	}
	
	public static int getxflowerCost(int kind) {
		return flxcost[kind];
	}
	
	public static int getgfruitCost(int kind) {
		return frgcost[kind];
	}
	
	public static int getxfruitCost(int kind) {
		return frxcost[kind];
	}
	
	public static int getflowerValue(int kind) {
		return flvalue[kind];
	}
	
	public static int getfruitValue(int kind) {
		return frvalue[kind];
	}
	
	public static int getflowerExperience(int kind) {
		return flexperience[kind];
	}
	
	public static int getfruitExperience(int kind) {
		return frexperience[kind];
	}
}