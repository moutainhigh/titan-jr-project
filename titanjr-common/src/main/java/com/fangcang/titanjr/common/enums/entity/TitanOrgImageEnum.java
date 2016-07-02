package com.fangcang.titanjr.common.enums.entity;


public class TitanOrgImageEnum {
	public enum ImageType{
		YYZZ(1,"营业执照图片"),SFZ(2,"身份证件图片"),ZZJGDM(3,"组织机构代码证图片"),SWDJ(4,"税务登记证图片"),JYCS(5,"经营场所认证");
		private int key;
		private String des;
		
		private ImageType(int key,String des){
			this.key = key;
			this.des = des;
		}

		public static ImageType  getEnumByKey(int key){
			ImageType entity = null;
			for(ImageType item : ImageType.values()) {
				if(item.key == key) {
					entity = item;
					break;
				}
			}
			return entity;
		}
		
		public int getKey() {
			return key;
		}
		public String getDes() {
			return des;
		}
		
	}
}
