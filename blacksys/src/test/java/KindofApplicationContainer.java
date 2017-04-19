import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import blacksys.Hello;


public class KindofApplicationContainer {

	/**
	 * GenericApplication - XML파일
	 */
	@Test
	public void genericApplicationContext() {
		GenericApplicationContext ac = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions("blacksys/generic/genericApplicationContext.xml");
		/**
		 * Reader는 기본적으로 클래스패스로 정의된 리소스부터 파일을 읽는다.
		 * 모든 메타정보가 등록이 완료됐으니 애플리케이션 컨테이너를 초기화한다. refresh
		 */
		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	
	/**
	 * PropertiesBeanDefinitionReader로 프로퍼티 파일을 읽어서
	 * 애플리케이션컨텍스트가 Bean을 등록한다.
	 */
	@Test
	public void propertyApplicationContext() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		Properties p = new Properties();
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(bf);
		reader.loadBeanDefinitions(new ClassPathResource("blacksys/generic/propertyApplicationContext.properties", getClass()));
		Hello hello = (Hello)bf.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(bf.getBean("printer").toString(), is("Hello Spring"));
	}

}
