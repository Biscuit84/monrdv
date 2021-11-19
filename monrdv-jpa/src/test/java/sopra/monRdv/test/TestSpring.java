package sopra.monRdv.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sopra.monRdv.config.ApplicationConfig;

public class TestSpring {
	public static void main(String[] args){
		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		
		spring.close();
	}
}
