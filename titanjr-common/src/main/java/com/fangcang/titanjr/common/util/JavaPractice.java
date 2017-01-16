package com.fangcang.titanjr.common.util;

import java.util.regex.Pattern;

public class JavaPractice {
	
	private static String usage ="usage:\n"
			+ "JavaPractice qualified.class.name\n";
	
	private static Pattern p =Pattern.compile("\\w+\\.");
	
	public static void main(String[] args){
		System.out.println(args.length);
		
		if(args.length<1){
			System.out.print(usage);
			System.exit(0);
		}
		
		for(int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}
		
		
		
		
	}
	

}
