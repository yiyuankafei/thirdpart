package com.leadpms.thirdpart.dao.mapper.auto;

import com.leadpms.thirdpart.dao.entity.auto.PartnerRequestLog;
import com.leadpms.thirdpart.dao.entity.auto.PartnerRequestLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartnerRequestLogMapper {
    long countByExample(PartnerRequestLogExample example);

    int deleteByExample(PartnerRequestLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartnerRequestLog record);

    int insertSelective(PartnerRequestLog record);

    List<PartnerRequestLog> selectByExampleWithBLOBs(PartnerRequestLogExample example);

    List<PartnerRequestLog> selectByExample(PartnerRequestLogExample example);

    PartnerRequestLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartnerRequestLog record, @Param("example") PartnerRequestLogExample example);

    int updateByExampleWithBLOBs(@Param("record") PartnerRequestLog record, @Param("example") PartnerRequestLogExample example);

    int updateByExample(@Param("record") PartnerRequestLog record, @Param("example") PartnerRequestLogExample example);

    int updateByPrimaryKeySelective(PartnerRequestLog record);

    int updateByPrimaryKeyWithBLOBs(PartnerRequestLog record);

    int updateByPrimaryKey(PartnerRequestLog record);
}