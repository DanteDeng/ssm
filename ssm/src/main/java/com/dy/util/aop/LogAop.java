package com.dy.util.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 配置中需要加入aop注解支持<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * 同时@Around("execution(* com.dy.controller..*Controller.*(..))")
 * 正则表达式必须正确
 * @author Administrator
 *
 */
@Component
@Aspect
@Scope
public class LogAop {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Around("execution(* com.dy.controller..*Controller.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
		String className = getClassName(joinPoint);
		String methodName = getMethodName(joinPoint);
		Object[] args = joinPoint.getArgs();
		boolean flag = true;
		for (Object object : args) {
			if(object instanceof HttpServletRequest){
				flag = false;
				HttpServletRequest request = (HttpServletRequest) object;
				Map<String, Object> mapString = getParameterMap(request);
				log.debug(className+" ------ "+methodName+" start .  params : "+mapString);
			}
		}
		if(flag){
			String paramsString = Arrays.toString(args);
			log.debug(className+" ------ "+methodName+" start .  params : "+paramsString);
			
		}
		
		Object proceed = joinPoint.proceed();
		
		log.debug(className+" ------ "+methodName+" end .  result : "+proceed);
		return proceed;
	}

	private String getMethodName(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		return signature.getName();
	}

	private String getClassName(ProceedingJoinPoint joinPoint) {
		String simpleName = joinPoint.getTarget().getClass().getSimpleName();
		return simpleName;
	}
	
	 /**
     *  request Map
     * 
     * @param request
     * @return
     */
    private Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<?, ?> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();
        Entry<?, ?> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

}
