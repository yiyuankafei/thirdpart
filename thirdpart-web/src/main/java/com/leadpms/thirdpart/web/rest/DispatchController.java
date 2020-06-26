package com.leadpms.thirdpart.web.rest;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.leadpms.thirdpart.common.dto.BaseResponse;
import com.leadpms.thirdpart.web.request.BaseRequest;
import com.leadpms.thirdpart.web.util.SignUtil;

@RestController
@Slf4j
public class DispatchController {
	
	private static final String SERVICE_SUFFIX = "Service";
	
	private static String secretKey="aSCPfFfCsMFNBQzGVmpUMTx0EtRhhdoCsqBB1pXRGHvRWpmIt2Wd06iopJylzwBg";
	
	@Autowired
	ApplicationContext applicationContext;
	
	@RequestMapping("/")
	public BaseResponse<?> partner(HttpServletRequest request) throws Exception {
		
		String reqString = new String(IOUtils.toByteArray(request.getInputStream()), "UTF-8");
		
		//1.验签
		String checkSign = SignUtil.getSign(reqString, secretKey);
		if (!checkSign.equals(request.getHeader("Sign"))) {
			throw new RuntimeException("验签失败");
		}
		
		//2.防重放
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
		
		return BaseResponse.success(invoke);
		
	}
	
	public static void main(String[] args) {
		String x = "/enterprise/get";
		String[] split = x.split("/");
		System.out.println(split.toString());
		System.out.println(split[0]);
		System.out.println(split[1]);
		System.out.println(split[2]);
	}

}
