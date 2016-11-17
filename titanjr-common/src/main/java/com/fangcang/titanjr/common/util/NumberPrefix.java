package com.fangcang.titanjr.common.util;

public class NumberPrefix {

	static char[] ch = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	         'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
	         'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	         'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
	         'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	         'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	         'w', 'x', 'y', 'z'};
	
	public static String getPayOrderCodePrefix(String basrStr){
		String nextStr = "";
		char[] cs = basrStr.toCharArray();
		if(cs[1]=='z'){
			if(cs[0]=='z'){
				System.out.println("错误");
			}else{
				for(int i=0;i<ch.length;i++){
					if(ch[i]==cs[0]){
						nextStr = new String(new char[]{ch[i+1],ch[0]});
					}
				}
			}
			
		}else{
			for(int i=0;i<ch.length;i++){
				if(ch[i]==cs[1]){
					nextStr = new String(new char[]{cs[0],ch[i+1]});
				}
			}
		}
		return 	nextStr;
	}
	
	public static void main(String[] args){
		System.out.println(getPayOrderCodePrefix("Az"));
	}
}
