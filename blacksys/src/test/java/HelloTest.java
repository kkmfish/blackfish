
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import blacksys.Hello;
import blacksys.StringPrinter;


public class HelloTest {

	@Test
	public void test() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("hello1",  Hello.class);
		
		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()));
	}
	
	@Test
	public void beanDefinitionTest() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("hello1",  Hello.class);
		
		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()));
		
		/**
		 * 빈 메타정보를 담은 오브젝트를 만든다.
		 * 빈 클래스는 Hello로 지정
		 * <bean class="apato2.springbook.lv2.Hello" /> 에 해당하는 메타정보
		 */
		BeanDefinition helloDf = new RootBeanDefinition(Hello.class);
		/**
		 * 빈의 name 프로퍼티에 들어갈 값을 지정
		 * <property name="name" value="spring" /> 에 해당
		 */
		helloDf.getPropertyValues().addPropertyValue("name", "Spring");
		
		/**
		 * 빈 메타정보를 hello2라는 이름을 가진 빈으로 해서 등록
		 * <bean id="hello2" ... />에 해당
		 */
		ac.registerBeanDefinition("hello2", helloDf);
		
		/**
		 * 등록된 빈을 컨테이너에서 가져와 사용
		 * ** 빈은 오브젝트 단위로 등록
		 * ** 같은 클래스 타입이더라도 두개를 등록하면 서로 다른 빈 오브젝트가 생성
		 */
		
		/**
		 * BeanDefinition으로 등록된 빈이 컨테이너에 의해 만들어지고
		 * 프로퍼티 설정이 됐는지 확인
		 */
		Hello hello2 = ac.getBean("hello2", Hello.class);
		/**
		 * 처음 등록한 빈과 두 번째 등록한 빈이 모두 동일한 Hello 클래스지만 별개의 오브벡드로 생성됏다.
		 */
		assertThat(hello2.sayHello(), is("Hello Spring"));
		
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
	}
	
	@Test
	public void registerBeanWithDependency() {
		StaticApplicationContext ac = new StaticApplicationContext();
		
		/**
		 * StringPrinter 클래스타입이며 printer라는 이름을 가진 빈을 등록
		 */
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		/**
		 * 단순한 값을 갖는 프로퍼티 등록
		 */
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		/**
		 * ID가 printer인 빈에 대한 레퍼런스를 프로퍼티로 등록
		 */
		helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		/**
		 * Hello 클래스의 print()메소드는 DI된 Printer 타입의 오브젝트에게 요청해서 인사말을
		 * 출력한다. 이 결과를 스트링으로 저장해두는 printer 빈을 통해 확인한다.
		 */
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}

}
