package blacksys.generic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import blacksys.Hello;

/**
 * GenericXmlApplicationContextTest
 * <p>
 * XML 메타설정파일을 읽어서 빈을 등록하는 예제
 * </p>
 * 
 * @author hgkim
 * @version 1.0
 * <li>(2017.04.20) 최초작성.</li>
 *
 */
public class GenericXmlApplicationContextTest {

	/**
	 * GenericXmlApplicationContextTest
	 */
	@Test
	public void genericXmlApplicationContextTest() {
		GenericApplicationContext ac = new GenericXmlApplicationContext("blacksys/generic/genericApplicationContext.xml");
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}

}
