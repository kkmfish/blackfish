import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

import blacksys.Hello;


public class KindofApplicationContainer {

	@Test
	public void genericApplicationContext() {
		GenericApplicationContext ac = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions("blacksys/generic/genericApplicationContext.xml");
		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}

}
