package com.fangcang.titanjr.pay.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.dto.BaseResponseDTO.ReturnCode;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("/trade")
public class FinancialTradeController extends BaseController {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialTradeController.class);

	private static final String TRADE_PAY_ERROR_PAGE = "/checkstand-pay/cashierDeskError";
	
	private String prv = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100d8b6e03dd8f9bf45157f0d14aedf9a696665641da90cab5114a22b7f6c711f22429c32c99ab76e3ce74de00145bcd50b9d2e7c60cd97a4979a5d0ce4ead9ba61baca1495758d69cc1f76e69db43f1ef1f9c33cd2edb8c726ed17c297a7b9fa3f18e58aef9d3f33f8431a41cc3c0ca7bc5151d33a8691e6506e0439363aec0063020301000102818027846d6e89b6bce48d8f6de4b420ad0904357fe492b36f37e941cb19c0bdfdf5e2dc95bc427ca95aecb8bc1caf4948360672f81634d72e99c079b044bbf878ee4c3c6050d319fc545736e392fa7dcf2761d23551663d3844f3f2f61f652bec45eedf6d398a7e0916944bba8c64dd70f770ba4e213764e97c2aeaf46e66a3b591024100f7e35b74120740e22c85c2ddae516bcde62648447f2269b3701503afe4775749d7ffba66eff7024b2b6e879b6b9f3945508eb189d7fe602488575dae609bf151024100dfce5d97595fce458c1769fea32b175bb594a404ea070009c1c139d63907acad0433cc55ffcbf7c81359057efbda4f968813ed34dfedd9edb9079fdfd486c97302406832ad9290b173d89e966b5efb9346197a90c4f7e5e8f53d73f3a1652247f7ed165a6c6430a247d8891d20eb77c5aa3134b7867146d5aa5c30e3688190227cc1024100ad298b8a75d145d4d3aeae0922104e335c0c14d7e486e405a88f2b83cf7e5ba14676196c94cd28faf9d550064f313b9119da691717077e2d8b9315a4e6581f770240177a20e4fc96cb896a899ef9a5bc4c54c24b416e2fda7debf7536e851fb33fdeb95750742b0f09154acd53d73af8750461bca5bd7cc0de58a8fa635453152e78";

	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;


	/**
	 * @Title: titanPay
	 * @Description: TODO
	 * @param orderInfo
	 * @param businessInfo
	 * @param response
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/titanPay", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String titanPay(String orderInfo, String businessInfo, Model model) {
		
		
		if (!StringUtil.isValidString(orderInfo)) {
			log.error("orderInfo is not null!");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getResMsg());
			return TRADE_PAY_ERROR_PAGE;
		}

		try {


			String deInfo = RSADecryptString.decryptString(orderInfo, prv);
			if (!StringUtil.isValidString(deInfo)) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}
			
			
			log.info("decrypt orderInfo is ok .");

			TitanOrderRequest dto = JsonConversionTool.toObject(deInfo,
					TitanOrderRequest.class);

			if (dto == null) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;
			}
			
			log.info("auth user is ok .");
			
			Map<String, String> busMap =  JsonConversionTool.toObject(businessInfo, Map.class);
			dto.setBusinessInfo(busMap);
			
			log.info("begin sava titan trans order ...");
			//保存金融订单
			TransOrderCreateResponse orderCreateResponse = titanFinancialTradeService
					.saveTitanTransOrder(dto);
			
			if (orderCreateResponse == null
					|| (!orderCreateResponse.getReturnCode().equals(
							ReturnCode.CODE_SUCCESS.getCode())) || 
							!StringUtil.isValidString(orderCreateResponse.getOrderNo())) 
			{
				log.error("orderCreateResponse "
						+ orderCreateResponse.getReturnCode() + ":"
						+ orderCreateResponse.getReturnMessage());
				
				model.addAttribute("msg",
						TitanMsgCodeEnum.AUTHENTITCATION_FAILED.getResMsg());
				return TRADE_PAY_ERROR_PAGE;	
			}
			
			log.info("end sava titan trans order... [orderNo: "
					+ orderCreateResponse.getOrderNo() + "]");


		} catch (Exception ex) {
			log.error("", ex);
		}
		
		model.addAttribute("msg",
				TitanMsgCodeEnum.UNEXPECTED_ERROR.getResMsg());
		return TRADE_PAY_ERROR_PAGE;
	}

	@ResponseBody
	@RequestMapping(value = "/checkOrderStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String checkOrderStatus(String orderId) {
		return "{\"status\":\"0\"}";
	}
}
