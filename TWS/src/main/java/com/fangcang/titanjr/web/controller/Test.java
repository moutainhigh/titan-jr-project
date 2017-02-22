package com.fangcang.titanjr.web.controller;

import java.net.URI;
import java.net.URISyntaxException;


public class Test {

	public static void main(String[] args) throws URISyntaxException {
		try {
			String urlString = "http://local_100.luoqinglong.com:8020/";
			URI uri = new URI(urlString);
			String host = uri.getHost();
			System.out.println("有下划线的域名取不到host---"+host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
