package com.leadpms.thirdpart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leadpms.partner.common.type.RecordStatus;
import com.leadpms.thirdpart.dao.entity.auto.Partner;
import com.leadpms.thirdpart.dao.entity.auto.PartnerExample;
import com.leadpms.thirdpart.dao.mapper.auto.PartnerMapper;

@Component
public class PartnerDao {
	
	@Autowired
	PartnerMapper partnerMapper;
	
	public Partner getbySecretId(String secretId) {
		PartnerExample example = new PartnerExample();
		example.createCriteria().andSecretIdEqualTo(secretId).andRecordStatusEqualTo(RecordStatus.NORMAL.toString());
		List<Partner> list = partnerMapper.selectByExample(example);
		if (list.size() == 0) {
			throw new RuntimeException("合作方不存在");
		}
		return list.get(0);
		
	}

}
