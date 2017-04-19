package blacksys;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.io.support.ResourcePatternResolver;

public interface ApplicationContext extends ListableBeanFactory,
		HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher,
		ResourcePatternResolver {

}
