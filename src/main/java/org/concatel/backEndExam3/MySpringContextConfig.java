package org.concatel.backEndExam3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySources({
	@PropertySource(value= {"classpath:application.properties"})
})
public class MySpringContextConfig {
	
	@Autowired
	private Environment env;
}
