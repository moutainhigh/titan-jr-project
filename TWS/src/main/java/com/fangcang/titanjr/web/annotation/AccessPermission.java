package com.fangcang.titanjr.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 访问权限
 * @author luoqinglong
 * @2016年7月14日
 */

@Target( { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessPermission {
	/**
	 * 允许访问的角色code
	 * @return
	 */
	String[] allowRoleCode() default {};
}
