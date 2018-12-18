package com.api.dse.poc.audit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.api.dse.poc.audit.service.AuditService;

@Component
@ConditionalOnProperty(name="audit.enabled",  havingValue="true", matchIfMissing=false)
public class AuditEventFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(AuditEventFilter.class);
	
	@Autowired private AuditService service;
    private final SimpleDateFormat gDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private final String AUDIT_START_TIME="aud_start";
	private final String HTTP_GET = "GET";
	private Map<String, String> resourceMap = null;
	
	@Value("${audit.enabled}")
	private Boolean isAuditEnabled;	
	@Value("${audit.db}")
	private Boolean isDbEnabled;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
    	
    	if(isAuditEnabled) {
	        try {
	            request.setAttribute(AUDIT_START_TIME, System.currentTimeMillis());
	            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	
	    	    AuditRequestWrapper bufferedRequest = new AuditRequestWrapper(httpServletRequest);
	
	            AuditLog logBean = handleRequest(bufferedRequest);
	            
	            chain.doFilter(bufferedRequest, httpServletResponse);
	            
	            logBean = handleResponse(httpServletResponse, logBean, new Long(request.getAttribute(AUDIT_START_TIME).toString()));
	            audit(logBean);
	        } catch (Throwable a) {
	        	throw a;
	        }
    	} else {
    		chain.doFilter(request, response);
    	}
    }
 

	@Override
    public void destroy() {
    }
    
 	private AuditLog handleRequest(AuditRequestWrapper bufferedRequest) throws IOException {
 		try {
			 AuditLog logBean = new AuditLog();
			 logBean.setResource(resourceMap.get(bufferedRequest.getRequestURI()));
			 logBean.setId(UUID.randomUUID().toString());
			 logBean.setHttpMethod(bufferedRequest.getMethod());
	         if(bufferedRequest.getRequestURI() != null) {
	         	logBean.setRequestUri(bufferedRequest.getRequestURI());
	         }
	         if(bufferedRequest.getRemoteHost() != null) {
	         	logBean.setRemoteHost(bufferedRequest.getRemoteHost());
	         }
	         if(bufferedRequest.getRemoteAddr() != null) {
	         	logBean.setIpAddr(bufferedRequest.getRemoteAddr());
	         }
			 logBean.setInput(bufferedRequest.getRequestBody());
			 logBean.setRequestParams(this.getRequestData(bufferedRequest));
			 return logBean;
 		} catch (Throwable a) {
        	throw a;
        }
 	} 
 	
 	private AuditLog handleResponse(HttpServletResponse httpServletResponse, AuditLog logBean, Long startTime) {
 		AuditLog bean = logBean;
 		
 	   	 if(startTime != null && startTime > 0) {
 	   		bean.setStartTime(gDateFormat.format(new Date(startTime)));
 	   		bean.setElapsedTime(this.getElapsedTime(startTime));
 	   	 }
 	   	 bean.setHttpStatusCode(httpServletResponse.getStatus());
		 return bean;
	}
 	
     private String getRequestData(HttpServletRequest request) {
         StringBuffer sb = new StringBuffer();
         Enumeration<?> requestParamNames = request.getParameterNames();
         while (requestParamNames.hasMoreElements()) {
             String requestParamName = (String) requestParamNames.nextElement();
             String requestParamValue;
             if (requestParamName.equalsIgnoreCase("password")) {
                 requestParamValue = "********";
             } else {
                 requestParamValue = request.getParameter(requestParamName);
             }
             sb.append(requestParamName).append(" : ").append(requestParamValue);
         }
         return sb.toString();
     }

	private String getElapsedTime(Long timeStart) {
		//---- Audit elapsed time
		final int TIMEINMILLISECONDS = 1000;
		return Float.toString((float) (System.currentTimeMillis() - timeStart) / TIMEINMILLISECONDS);
	}
	
	@Async
    private void audit(AuditLog logBean) {
        if(!HTTP_GET.equalsIgnoreCase(logBean.getHttpMethod()) && isDbEnabled) {
        	service.execute(logBean);
        } else {
        	log.error(logBean.toString());
        }
	}
	
	@Bean
	public Object createResourceMap() {
		resourceMap = new HashMap<>();
		resourceMap.put("/v1/payments", "payments");
		resourceMap.put("/v1/payments/accounts", "accounts");
		resourceMap.put("/v1/billing", "billing");
		return resourceMap;
	}
}
