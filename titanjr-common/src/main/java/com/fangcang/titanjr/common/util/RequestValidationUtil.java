package com.fangcang.titanjr.common.util;

import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.exception.RSValidateException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 请求参数常规校验工具类
 * Created by zhaoshan on 2016/4/9.
 */
public class RequestValidationUtil {

    /**
     * 校验是否为空，统一以异常形式抛出处理
     *
     * @param value
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkNotEmpty(Object value, String fieldName) throws RSValidateException {
        if (value == null) {
            throw new RSValidateException(RSInvokeErrorEnum.PARAM_EMPTY.returnMsg,
                    "error:Missing required arguments:" + fieldName + ".");
        } else if (value instanceof String) {
            if (((String) value).trim().length() == 0) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_EMPTY.returnMsg,
                        "error:Missing required arguments:" + fieldName + ".");
            }
        } else if (value instanceof List) {
			if (((List<?>) value).size() == 0) {
				throw new RSValidateException(RSInvokeErrorEnum.PARAM_EMPTY.returnMsg,
						"error:Missing required arguments:" + fieldName + ".");
			}
		}
    }
    /**
     * 根据注解检查属性值是否非空
     * @param obj
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws RSValidateException
     */
    public static void check(Object obj) throws RSValidateException{
		Field[] f = obj.getClass().getDeclaredFields();
		for(Field fieldItem : f){
			NotNull notNull = fieldItem.getAnnotation(NotNull.class);
			if(notNull!=null){//有注解，开始检查
				try {
					fieldItem.setAccessible(true);
					RequestValidationUtil.checkNotEmpty(fieldItem.get(obj), fieldItem.getName());
				} catch (IllegalArgumentException e) {
					throw new RSValidateException(obj.getClass()+"，param:["+fieldItem.getName()+"] is null, must have value ",e);
				} catch (IllegalAccessException e) {
					throw new RSValidateException(obj.getClass()+"，param:["+fieldItem.getName()+"] is error ",e);
				}
			}
		}
	}
    /**
     * 长度校验，校验字段不超过最大长度
     *
     * @param value
     * @param maxLength
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkMaxLength(String value, int maxLength, String fieldName) throws RSValidateException {
        if (value != null) {
            if (value.length() > maxLength) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_INVALID.returnMsg,
                        "error:Invalid arguments:the string length of " + fieldName +
                                " can not be larger than " + maxLength + ".");
            }
        }
    }

    /**
     * 对于合作方有字符串长度校验时需要
     * @param value
     * @param maxSize
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkMaxListSize(String value, int maxSize, String fieldName) throws RSValidateException {
        if (value != null) {
            String[] list = value.split(",");
            if (list != null && list.length > maxSize) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_INVALID.returnMsg,
                        "error:Invalid arguments:the array size of " + fieldName +
                                " must be less than " + maxSize + ".");
            }
        }
    }

    /**
     * 对于合作方有列表长度校验时需要
     * @param value
     * @param maxSize
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkMaxListSize(List<?> value, int maxSize, String fieldName) throws RSValidateException {
        if (value != null) {
            if (value.size() > maxSize) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_INVALID.returnMsg,
                        "error:Invalid arguments:the list size of " + fieldName +
                                " must be less than " + maxSize + ".");
            }
        }
    }

    /**
     * 针对long类型的校验最大值
     * @param value
     * @param maxValue
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkMaxValue(Long value, long maxValue, String fieldName) throws RSValidateException {
        if (value != null) {
            if (value > maxValue) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_INVALID.returnMsg,
                        "error:Invalid arguments:the value of " + fieldName +
                                " can not be larger than " + maxValue + ".");
            }
        }
    }

    /**
     * 针对long类型的校验最小值
     * @param value
     * @param minValue
     * @param fieldName
     * @throws com.fangcang.titanjr.common.exception.RSValidateException
     */
    public static void checkMinValue(Long value, long minValue, String fieldName) throws RSValidateException {
        if (value != null) {
            if (value < minValue) {
                throw new RSValidateException(RSInvokeErrorEnum.PARAM_INVALID.returnMsg,
                        "error:Invalid arguments:the value of " + fieldName +
                                " can not be less than " + minValue + ".");
            }
        }
    }
    
}
