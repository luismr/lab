package com.byteslounge.spring.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.byteslounge.spring.tx.model.User;
import com.byteslounge.spring.tx.test.OuterBean;

public class Main 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    	OuterBean testBean = (OuterBean) ctx.getBean("outerBeanImpl");
    	
    	User user = new User();
    	user.setUsername("johndoe");
    	user.setName("John Doe");
    	
    	try{
    		testBean.testRequired(user);
    	} catch(Exception e){
    		// catch exception raised from transaction rollback
    	}
    	
    	testBean.testRequiresNew(user);
    	
    }
}
