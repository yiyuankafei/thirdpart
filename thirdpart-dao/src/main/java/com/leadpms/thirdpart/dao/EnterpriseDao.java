package com.leadpms.thirdpart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leadpms.thirdpart.dao.entity.auto.Enterprise;
import com.leadpms.thirdpart.dao.entity.auto.EnterpriseExample;
import com.leadpms.thirdpart.dao.mapper.auto.EnterpriseMapper;

@Component
public class EnterpriseDao {
	
	@Autowired
	EnterpriseMapper mapper;
	
	public Enterprise getbyName(String name) {
		EnterpriseExample example = new EnterpriseExample();
		example.createCriteria().andNameEqualTo(name);
		List<Enterprise> list = mapper.selectByExample(example);
		if (list.size() == 0) {
			throw new RuntimeException("公司名不存在");
		}
		return list.get(0);
		
	}

}
