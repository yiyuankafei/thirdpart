package com.leadpms.thirdpart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadpms.thirdpart.dao.PartnerRequestLogDao;
import com.leadpms.thirdpart.dao.entity.auto.PartnerRequestLog;

@Service
public class PartnerRequestLogService {
	
	@Autowired
	PartnerRequestLogDao partnerRequestLogDao;
	
	public void insert(PartnerRequestLog log) {
		partnerRequestLogDao.insert(log);
	}

}
