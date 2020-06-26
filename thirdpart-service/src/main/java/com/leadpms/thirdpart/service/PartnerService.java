package com.leadpms.thirdpart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadpms.thirdpart.dao.PartnerDao;
import com.leadpms.thirdpart.dao.entity.auto.Partner;

@Service
public class PartnerService {
	
	@Autowired
	PartnerDao partnerDao;
	
	public Partner getBySecretId(String secretId) {
		return partnerDao.getbySecretId(secretId);
	}

}
