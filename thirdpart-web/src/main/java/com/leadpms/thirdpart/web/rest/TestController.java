package com.leadpms.thirdpart.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadpms.thirdpart.dao.entity.auto.Enterprise;
import com.leadpms.thirdpart.dao.entity.auto.EnterpriseExample;
import com.leadpms.thirdpart.dao.entity.auto.Partner;
import com.leadpms.thirdpart.dao.entity.auto.PartnerExample;
import com.leadpms.thirdpart.dao.mapper.auto.EnterpriseMapper;
import com.leadpms.thirdpart.dao.mapper.auto.PartnerMapper;

@RestController
public class TestController {
	
	@Autowired
	PartnerMapper mapper;
	
	@Autowired
	EnterpriseMapper eMapper;
	
	@RequestMapping("test")
	public String x(String x) {
		return "hello:" + x;
	}
	
	@RequestMapping("test1")
	public List<Partner> x1(String x) {
		PartnerExample ex = new PartnerExample();
		ex.createCriteria().andSecretIdEqualTo("52nRzpzYoRCPWaCv");
		return mapper.selectByExample(ex);
	}
	
	@RequestMapping("test2")
	public List<Enterprise> x2(String x) {
		EnterpriseExample ex = new EnterpriseExample();
		ex.createCriteria().andNameEqualTo("上海市地震灾害防御中心");
		return eMapper.selectByExample(ex);
	}
	
	@RequestMapping("test3")
	public Partner x3(String x) {
		return mapper.selectByPrimaryKey(1);
	}

}
