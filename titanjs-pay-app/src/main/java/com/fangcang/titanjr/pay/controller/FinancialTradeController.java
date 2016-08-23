package com.fangcang.titanjr.pay.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.request.TitanOrderRequest;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.pay.util.RSADecryptString;
import com.fangcang.util.StringUtil;
import com.google.gson.Gson;

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
			model.addAttribute("msg", "");
			return TRADE_PAY_ERROR_PAGE;
		}
		
		try {

			String prv = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100d8b6e03dd8f9bf45157f0d14aedf9a696665641da90cab5114a22b7f6c711f22429c32c99ab76e3ce74de00145bcd50b9d2e7c60cd97a4979a5d0ce4ead9ba61baca1495758d69cc1f76e69db43f1ef1f9c33cd2edb8c726ed17c297a7b9fa3f18e58aef9d3f33f8431a41cc3c0ca7bc5151d33a8691e6506e0439363aec0063020301000102818027846d6e89b6bce48d8f6de4b420ad0904357fe492b36f37e941cb19c0bdfdf5e2dc95bc427ca95aecb8bc1caf4948360672f81634d72e99c079b044bbf878ee4c3c6050d319fc545736e392fa7dcf2761d23551663d3844f3f2f61f652bec45eedf6d398a7e0916944bba8c64dd70f770ba4e213764e97c2aeaf46e66a3b591024100f7e35b74120740e22c85c2ddae516bcde62648447f2269b3701503afe4775749d7ffba66eff7024b2b6e879b6b9f3945508eb189d7fe602488575dae609bf151024100dfce5d97595fce458c1769fea32b175bb594a404ea070009c1c139d63907acad0433cc55ffcbf7c81359057efbda4f968813ed34dfedd9edb9079fdfd486c97302406832ad9290b173d89e966b5efb9346197a90c4f7e5e8f53d73f3a1652247f7ed165a6c6430a247d8891d20eb77c5aa3134b7867146d5aa5c30e3688190227cc1024100ad298b8a75d145d4d3aeae0922104e335c0c14d7e486e405a88f2b83cf7e5ba14676196c94cd28faf9d550064f313b9119da691717077e2d8b9315a4e6581f770240177a20e4fc96cb896a899ef9a5bc4c54c24b416e2fda7debf7536e851fb33fdeb95750742b0f09154acd53d73af8750461bca5bd7cc0de58a8fa635453152e78";

			Gson gson = new Gson();

			String deInfo = RSADecryptString.decryptString(orderInfo, prv);
			if (!StringUtil.isValidString(deInfo)) {
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg", "");
				return TRADE_PAY_ERROR_PAGE;
			}
			

			TitanOrderRequest dto = gson.fromJson(deInfo,
					TitanOrderRequest.class);

			if (dto == null)
			{
				log.error("validate user identity decrypt fail.");
				model.addAttribute("msg", "");
				return TRADE_PAY_ERROR_PAGE;
			}

			Map<String, String> busMap = gson.fromJson(businessInfo, Map.class);
			
			dto.setBusinessInfo(busMap);
			
			
			
			

			// if(dto != null && )
//
//			TitanTransOrder order = new TitanTransOrder();
//
//			order.setAdjustcontent(null);
//			order.setAdjusttype(null);
//
//			// orderInfo [name n, escrowedDate n, goodsId y, goodsdetail n,
//			// goodsname n, userId y, ruserId n, amount y, paytype ]
//			// businessInfo [inAccountCode n, outAccountCode n]
//
//			order.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
//			order.setCreatetime(new Date());
//			order.setCreator(dto.getName());// 订单信息的用户ID//用户姓名
//			// order.setEscrowedDate(dto.getEscrowedDate());从订单中获取
//			order.setGoodscnt(1);// 可以不传,默认位1
//			order.setGoodsdetail(dto.getGoodsdetail());// 从订单中获取 可选
//			order.setGoodsname(dto.getGoodsname());// 从订单中获取 可选
//
//			// order.setIsEscrowedPayment(isEscrowedPayment);//根据订单中的担保日期 不传
//			// order.setMerchantcode(merchantcode);//从订单中获取 付款方的用户ID
//
//			order.setOrderdate(new Date());
//			order.setOrdertime(new Date());
//			// order.setOrderid(orderid);//生成订单号
//			// order.setOrdertypeid(ordertypeid);// 固定写死B1
//
//			// order.setPayeemerchant(payeemerchant);//收款 订单信息指定
//			// order.setPayermerchant(payermerchant);//付款 建议不用传
//
//			order.setPayorderno(dto.getGoodsId());// 订单信息中的商品标示
//
//			// order.setProductid(productid);如果是GDP则采用配置的,其他统一
//
//			// order.setReceivablefee(receivablefee);//费率需要计算
//			// order.setReceivedfee(receivedfee);//费率需要计算
//
//			// order.setRemark(remark);//备注，暂时为空
//			order.setStatusid(OrderStatusEnum.ORDER_IN_PROCESS.getStatus());// 默认处理中
			// order.setTradeamount(dto.getAmount());//订单金额 ， 订单信息中携带
			// order.setAmount(amount);//网关充值金额

			// inAccountCode outAccountCode 业务信息
			// 收款方用户ID 付款方用户ID

			// response.sendRedirect("http://test.fangcang.com:80/ti/trade/showCashierDesk.action?paySource=5");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/checkOrderStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String checkOrderStatus(String orderId) {
		return "{\"status\":\"0\"}";
	}
}
