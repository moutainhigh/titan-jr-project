package com.fangcang.titanjr.web.handler;

import com.fangcang.titanjr.common.exception.RSValidateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共异常处理的handler
 * 将全局拦截controller中发生的异常并进行拦截跳转至公共异常页面
 * Created by zhaoshan on 2016/4/18.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    private static Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    private final String CommonErrorPage = "commonErrorInfo";

    /**
     * 局部异常特殊处理（返回页面），现在主要处理信息校验异常等
     * 后续需封装更详细信息，可能需要更多类似的异常信息处理
     * 将错误信息传递给view
     * @param exception
     * @return
     */
    @ExceptionHandler(RSValidateException.class)
    public ModelAndView handleInvalidRequestError(RSValidateException exception)
    {
        log.error("融数参数校验异常: ",exception);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errMessage", exception.toString());
        ModelAndView mv = new ModelAndView(this.CommonErrorPage, map);
        return mv;
    }

    /**
     * 针对非捕获的运行时异常的统一处理，全局性。
     * 这种需要发邮件出来进行告警处理
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnexpectedServerError(RuntimeException exception)
    {
        log.error("发生非捕获的运行时异常: ",exception);
//        EmailInfo emailInfo = new EmailInfo();
//        String emailAddress = "3175803351@qq.com";
//        Notice notice = new Notice();
//        notice.setTitle("运行异常RuntimeException");
//        notice.setContent(exception.toString());
//
//        emailInfo.setEmailAddress(emailAddress);
//        emailInfo.setNotice(notice);
//        messageService.sendEmailNotice(emailInfo);//发送告警邮件
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errMessage", exception.getMessage() + "，异常详细：" + exception.getCause().getMessage());
        ModelAndView mv = new ModelAndView(this.CommonErrorPage, map);
        return mv;
    }

    /**
     * 全局其它非已定义异常处理方法
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleExpectedServerError(Exception exception)
    {
        log.error("发生未定义的全局异常信息: ",exception);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("errMessage", exception.getMessage() + "，异常详细：" + exception.getCause().getMessage());
        ModelAndView mv = new ModelAndView(this.CommonErrorPage, map);
        return mv;
    }

    //=====================================  返回字符串  begin =====================================//
    //返回字符串，主要是为  ajax 查询准备的
    /**
     * 局部异常特殊处理（返回字符串）
     * @param exception
     * @return
     */
    /*
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)//返回状态码
    @ResponseBody
    public String handleInvalidRequestError(BusinessException exception)
    {
        return exception.getMessage();//如果需要json格式的字符串，可以调整这里
    }
    */

    /**
     * 运行时异常，全局性。（返回字符串）
     * @param exception
     * @return
     */
	/*
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleUnexpectedServerError(RuntimeException exception)
    {
        return exception.getMessage();//如果需要json格式的字符串，可以调整这里
    }
    */


    /**
     * Exception异常类处理（返回字符串）
     * @param exception
     * @return
     */
	/*
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleExpectedServerError(Exception exception)
    {
        return exception.getMessage();//如果需要json格式的字符串，可以调整这里
    }
    */
    //=====================================  返回字符串 end   =====================================//

}
