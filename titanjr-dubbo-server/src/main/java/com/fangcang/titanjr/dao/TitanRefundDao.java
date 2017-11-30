package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.entity.TitanRefund;

public interface TitanRefundDao {
	
	int insert(TitanRefund entity) throws DaoException;
	
	/**
	 * 查询
	 * @param refundDTO
	 * @return
	 */
	List<RefundDTO> queryRefundDTO(RefundDTO refundDTO);


	/**
	 * 查询某个时间单内的退款记录，扩展了refundDTO的参数
	 * @param refundDTO
	 * @return
	 */
	List<RefundDTO> queryRefundDTODetail(RefundDTO refundDTO);
	
	/**
	 * 修改
	 * @param refundDTO
	 * @return
	 */
	int updateRefundDTO(RefundDTO refundDTO);
	
}
