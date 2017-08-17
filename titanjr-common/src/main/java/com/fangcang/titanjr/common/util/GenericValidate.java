package com.fangcang.titanjr.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.bean.CommRes;
import com.fangcang.util.StringUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

public class GenericValidate {
	
	private static final Log log = LogFactory.getLog(GenericValidate.class);
	
	private static Validator validator;
	
	private GenericValidate() {
		
	}
	
	public static boolean validate(Object obj,String  ... paraNames) {
		if(obj == null) {
			log.error("the validate object is null!");
		}
		
		validator = getValidator();
		boolean validateResult = true;
		
		if(paraNames != null && paraNames.length > 0) {
			for(String paraName : paraNames) {
				Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(obj, paraName);
				for(ConstraintViolation<?> constraintViolation : constraintViolations) {
					log.error(constraintViolation.getRootBeanClass().getSimpleName() + "." + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
					validateResult = false; 
				}
			}
		}else {
			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);

			if(constraintViolations != null && constraintViolations.size() > 0) {
				for(ConstraintViolation<?> constraintViolation : constraintViolations) {
					log.error(constraintViolation.getRootBeanClass().getSimpleName() + "." + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
					validateResult = false;
				}
			}
		}
		
		return validateResult;
	}
	
	
	public static CommRes validateObj(Object obj) {
		
		CommRes res = new CommRes();
		res.putSuccess();
		
		if(obj == null) {
			res.putError("request object is null");
			return res;
		}
		
		validator = getValidator();
		StringBuilder validateResult = new StringBuilder();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
		if(constraintViolations != null && constraintViolations.size() > 0) {
			for(ConstraintViolation<?> constraintViolation : constraintViolations) {
				validateResult.append(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()+"; ");
			}
		}
        if(StringUtil.isValidString(validateResult.toString())) {
        	res.putError(validateResult.toString());
			return res;
        }
        
        return res;
	}
	
	
	private static Validator getValidator() {
		if(validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}
	
}
