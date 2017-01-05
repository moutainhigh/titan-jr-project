package com.fangcang.titanjr.rs.request;



import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * 本地上传rsa加密文件
 * @author luoqinglong
 * @date   2016年11月29日
 */
public class RSFsFileUploadRequest extends BaseRequest {
	/**
	 * 文件路径 c:\123.txt
	 */
	@NotNull
	private String path;
	/**
	 * 文件类型
	 *  73. 天下房仓文件上传
	 *  74. 账户给融数钱包的对账文件(在线支付)
	 *	75. 账户给融数钱包的对账文件(在线支付以外)
	 *	76. 账户给融数钱包的对账文件(代收付)
	 */
	@NotNull
	private Integer type;
	/**
	 * 账期,yyyy-MM-dd
	 */
	@NotNull
	private Date invoiceDate;
	/**
	 * 批次,通常不加批次的话 每个账期只能上传一个文件
	 */
	private String bacth;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7455855967468038849L;

	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getBacth() {
		return bacth;
	}

	public void setBacth(String bacth) {
		this.bacth = bacth;
	}
	
}
