package com.leadpms.thirdpart.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leadpms.thirdpart.dao.entity.auto.PartnerRequestLog;
import com.leadpms.thirdpart.dao.mapper.auto.PartnerRequestLogMapper;

@Component
public class PartnerRequestLogDao {
	
	@Autowired
	PartnerRequestLogMapper partnerRequestLogMapper;
	
	public void insert(PartnerRequestLog log) {
		partnerRequestLogMapper.insert(log);
	}

}
