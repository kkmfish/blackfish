package blacksys.generic;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import blacksys.Hello;

public class ApplicationStart {
	static final Logger LOGGER = Logger.getLogger(ApplicationStart.class);

	public static void main(String[] args) {

		ApplicationContext ac = new GenericXmlApplicationContext("blacksys/generic/genericApplicationContext.xml");
		/**
		 * IoC 컨테이너가 구성한 빈 오브젝트로 이뤄진 애플리케이션을 기동할
		 * 오브젝트를 getBean()으로 가져온다.
		 */
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		String result =  ac.getBean("printer").toString();
		
		LOGGER.info(result);
		
	}

}
