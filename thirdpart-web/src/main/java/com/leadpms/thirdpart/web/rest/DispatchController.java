package com.leadpms.thirdpart.web.rest;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.leadpms.thirdpart.common.dto.BaseResponse;
import com.leadpms.thirdpart.common.utils.RandomUUID;
import com.leadpms.thirdpart.dao.entity.auto.Partner;
import com.leadpms.thirdpart.dao.entity.auto.PartnerRequestLog;
import com.leadpms.thirdpart.service.PartnerRequestLogService;
import com.leadpms.thirdpart.service.PartnerService;
import com.leadpms.thirdpart.web.request.BaseRequest;
import com.leadpms.thirdpart.web.util.SignUtil;

@RestController
@Slf4j
public class DispatchController {
	
	private static final String SERVICE_SUFFIX = "Service";
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	PartnerService partnerService;
	
	@Autowired
	PartnerRequestLogService partnerRequestLogService;
	
	@RequestMapping("/")
	public BaseResponse<?> partner(HttpServletRequest request) throws Exception {
		
		String reqString = new String(IOUtils.toByteArray(request.getInputStream()), "UTF-8");
		String secretId = request.getHeader("SecretId");
		String sign =request.getHeader("Sign");
		PartnerRequestLog requestLog = new PartnerRequestLog();
		
		try {
			requestLog.setCode(RandomUUID.getUUID());
			requestLog.setCreateTime(new Date());
			requestLog.setSecretId(secretId);
			requestLog.setSign(sign);
			requestLog.setRequest(reqString);
			Partner partner = partnerService.getBySecretId(secretId);
			requestLog.setPartnerName(partner.getName());
			requestLog.setPartnerCode(partner.getCode());
			
			//验签
			String checkSign = SignUtil.getSign(reqString, partner.getSecretKey());
			if (!checkSign.equals(sign)) {
				throw new RuntimeException("验签失败");
			}
			
			//防重放
			BaseRequest baseRequest = JSON.parseObject(reqString, BaseRequest.class);
			Long currentTime = System.currentTimeMillis()/1000;
			Long interval = currentTime - baseRequest.getTimestamp();
			log.info("时间间隔：{}", interval);
			if (interval > 5) {
				throw new RuntimeException("请求超时");
			}
			
			//3.分发
			String action = baseRequest.getAction();
			String[] split = action.split("/");
			String serviceName = split[1] + SERVICE_SUFFIX;
			String methodName = split[2];
			
			Object bean = applicationContext.getBean(serviceName);
			Method method = bean.getClass().getMethod(methodName, String.class);
			Object invoke = method.invoke(bean, reqString);
			requestLog.setResponse(JSON.toJSONString(invoke));
			
			return BaseResponse.success(invoke);
		} catch (Exception e) {
			e.printStackTrace();
			requestLog.setException(e.getMessage());
			return BaseResponse.error(e.getMessage());
		} finally {
			partnerRequestLogService.insert(requestLog);
		}
	}
}
