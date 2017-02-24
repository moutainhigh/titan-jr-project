package com.fangcang.titanjr.common.enums.entity;


/**
 * 个人担保
 * @author luoqinglong
 * @date   2016年12月3日
 */
public class LoanPersonEnsureEnum {
	/**
	 * 工作年限
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum YearsWorking{
		YEARS_1_3(1,"1-3年"),YEARS_3_5(2,"3-5年"),YEARS_5_10(3,"5-10年"),YEARS_10(4,"10年以上");
		private int type;
		private String des;
		private YearsWorking(int type,String des){
			this.type = type;
			this.des = des;
		}
		public static YearsWorking  getEnumByType(int type){
			YearsWorking entity = null;
			for(YearsWorking item : YearsWorking.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getDes() {
			return des;
		}
		public void setDes(String des) {
			this.des = des;
		}
		
	}
	/**
	 * 最高学历
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum HighestEducation{
		XIAOXUE_1(1,"小学"),CHUZHONG_2(2,"初中"),GAOZHONG_3(3,"高中"),ZHONGZHUAN_4(4,"中专"),DAZHUAN_5(5,"大专"),BENKE_6(6,"本科"),SHUOSHI_7(7,"硕士"),BOSHI_8(8,"博士");
		private int type;
		private String des;
		
		private HighestEducation(int type,String des){
			this.type = type;
			this.des = des;
		}

		public static HighestEducation  getEnumByType(int type){
			HighestEducation entity = null;
			for(HighestEducation item : HighestEducation.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	}
	/**
	 * 房产类型
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum HousePropertyType{
		NO_HOUSE_1(1,"无房"),BOTH_HOUSE_LOAN_2(2,"有房有贷"),HAVE_HOUSE_NO_LOAN_3(3,"有房无贷");
		
		private int type;
		private String des;
		
		private HousePropertyType(int type,String des){
			this.type = type;
			this.des = des;
			
		}

		public static HousePropertyType  getEnumByType(int type){
			HousePropertyType entity = null;
			for(HousePropertyType item : HousePropertyType.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		
		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
	}
	/**
	 * 婚姻状态
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum MarriageStatus{
		MARRY_NO_1(1,"未婚"),MARRY_YES_2(2,"已婚");
		private int status;
		private String des;
		
		private MarriageStatus(int status ,String des){
			this.status = status;
			this.des = des;
		}

		public static MarriageStatus  getEnumByStatus(int status){
			MarriageStatus entity = null;
			for(MarriageStatus item : MarriageStatus.values()) {
				if(item.status == status) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	}
	/**
	 * 有无子女
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum HaveChildren{
		CHILDREN_NO_1(1,"无子女"),CHILDREN_YES_2(2,"有子女");
		
		private int type;
		private String des;
		
		private HaveChildren(int type,String des){
			this.type = type;
			this.des = des;
			
		}

		public static HaveChildren  getEnumByType(int type){
			HaveChildren entity = null;
			for(HaveChildren item : HaveChildren.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
	}
	
	public enum CarPropertyType{
		NO_CAR_1(1,"无车"),HAVE_CAR_NO_LOAN_2(2,"有车无贷"),BOTH_CAR_LOAN_3(3,"有车有贷");
		private int type;
		private String des;
		
		private CarPropertyType(int type,String des){
			this.type = type;
			this.des = des;
		}
		
		public static CarPropertyType  getEnumByType(int type){
			CarPropertyType entity = null;
			for(CarPropertyType item : CarPropertyType.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
	}
	/**
	 * 关系
	 * @author luoqinglong
	 * @date   2016年12月3日
	 */
	public enum RelationToGuarantee{
		FUMU_1(1,"父母"),ZINV_2(2,"子女"),PEIOU_3(3,"配偶"),PENGYOU_4(4,"朋友"),TONGSHI_5(5,"同事"),QITA_6(6,"其他");
		private int type;
		private String des;
		
		private RelationToGuarantee(int type,String des){
			this.type = type;
			this.des = des;
		}
		
		public static RelationToGuarantee  getEnumByType(int type){
			RelationToGuarantee entity = null;
			for(RelationToGuarantee item : RelationToGuarantee.values()) {
				if(item.type == type) {
					entity = item;
					break;
				}
			}
			return entity;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	}
	
}
