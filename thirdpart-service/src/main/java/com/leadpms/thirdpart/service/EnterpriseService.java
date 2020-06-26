package com.leadpms.thirdpart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.leadpms.thirdpart.dao.EnterpriseDao;
import com.leadpms.thirdpart.dao.entity.auto.Enterprise;
import com.leadpms.thirdpart.service.request.EnterpriseRequest;

@Service
public class EnterpriseService {
	
	@Autowired
	EnterpriseDao enterpriseDao;
	
	public Enterprise get(String name) {
		EnterpriseRequest request = JSON.parseObject(name, EnterpriseRequest.class);
		return enterpriseDao.getbyName(request.getCompanyName());
	}

}
