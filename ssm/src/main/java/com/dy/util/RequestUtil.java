package com.dy.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class RequestUtil {  
    
    /** 
     * ConvertUtilsBean convertUtils = new ConvertUtilsBean();    
     * DateConverter dateConverter = new DateConverter();    
     * convertUtils.register(dateConverter,Date.class);     
     * */  
      
      
    /** 
     * @param <T> 
     * @param newSource 
     * @param source    
     */  
    public static <T> void  beanConvert(T newSource,T source){  
        try {  
            BeanUtils.copyProperties(newSource,source);  
        } catch (BeansException e) {              
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * @param request 
     * @param obj  Bean.class 
     * @return 
     */  
    @SuppressWarnings("unchecked")    
    public static <T> T requestToBean(HttpServletRequest request,Class<T> clazz){    
          
        Enumeration<String> enume = request.getParameterNames();     
        T beanObj = getObjectByClass(clazz);  
        try{     
            while(enume.hasMoreElements()){     
                String propertyName = enume.nextElement();                  
                if(isCheckBeanExitsPropertyName(clazz,propertyName)){  
                    Object propertyValue = request.getParameter(propertyName);  
                    setProperties(beanObj,propertyName,propertyValue);  
                }  
                  
            }     
        }catch(Exception e){
        	e.printStackTrace();
        }    
             
        return beanObj;     
    }    
      
    private static <T> T getObjectByClass(Class<T> clazz){  
        T t = null;  
        try {  
            t = clazz.newInstance();  
        } catch (InstantiationException e1) {             
            e1.printStackTrace();  
        } catch (IllegalAccessException e1) {             
            e1.printStackTrace();  
        }  
        return t;  
    }  
      
      
      
    /** 
     * @param clazz           Class 
     * @param propertyName    
     * @return true || false 
     */  
    private  static boolean isCheckBeanExitsPropertyName(Class<?> clazz,String propertyName){   
        boolean retValue = false;  
        try {  
            Field field =  clazz.getDeclaredField(propertyName);              
            if(null != field){  
                retValue = true;  
            }  
        } catch (NoSuchFieldException e) {            
            System.out.println("Class : " + clazz.getSimpleName()+",Property : "+propertyName+"  is not found.The message is : "+e.getMessage());          
        }  
        return retValue;  
          
    }  
      
     /**   
     * �����ֶ�ֵ       
     * @param obj        
     * @param propertyName  
     * @param value       
     * @return             
     */     
    public static void setProperties(Object object, String propertyName,Object value) throws IntrospectionException,     
            IllegalAccessException, InvocationTargetException {     
        PropertyDescriptor pd = new PropertyDescriptor(propertyName,object.getClass());     
        Method methodSet = pd.getWriteMethod();     
        methodSet.invoke(object,value);     
    }    
    
    /**
     *  request Map
     * 
     * @param request
     * @return
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
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