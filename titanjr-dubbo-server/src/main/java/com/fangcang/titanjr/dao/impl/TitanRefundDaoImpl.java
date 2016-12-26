package com.fangcang.titanjr.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.entity.TitanRefund;
import com.fangcang.util.StringUtil;

public class TitanRefundDaoImpl  extends GenericDAOMyBatisImpl implements TitanRefundDao{

	@Override
	public int insert(TitanRefund entity) throws DaoException {
		try {
			return super.insertEntity("com.fangcang.titanjr.dao.TitanRefundDao.insertEntity", entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<RefundDTO> queryRefundDTO(RefundDTO refundDTO) {
		try {
			if(!StringUtil.isValidString(refundDTO.getRefundId()) && 
				!StringUtil.isValidString(refundDTO.getOrderNo()) &&
				!StringUtil.isValidString(refundDTO.getRefundOrderno())&&
				!StringUtil.isValidString(refundDTO.getUserOrderId())){
				return null;
			}
			return super.selectList("com.fangcang.titanjr.dao.TitanRefundDao.queryList", refundDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public int updateRefundDTO(RefundDTO refundDTO) {
		try {
			if(!StringUtil.isValidString(refundDTO.getRefundId()) && 
				!StringUtil.isValidString(refundDTO.getOrderNo()) &&
				!StringUtil.isValidString(refundDTO.getRefundOrderno())&&
				!StringUtil.isValidString(refundDTO.getUserOrderId())){
				return 0;
			}
			return super.updateEntity("com.fangcang.titanjr.dao.TitanRefundDao.updateEntity", refundDTO);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
