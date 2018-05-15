package snsoftboot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import snsoft.commons.spring.SpringBeanUtils;
import snsoft.commons.spring.SpringXmlApplicationContext;

/**
 * <p>标题：SpringBoot中将webContext的parent设置为底层的context</p>
 * <p>功能：在使用SpringBoot启动的环境中，webContext中就可以直接注入底层bean</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：朱郁琼</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年4月12日下午1:32:40</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class BootSpringApplication extends SpringApplication
{
	public BootSpringApplication(Object... sources)
	{
		super(new SpringXmlApplicationContext(), sources);
	}


	@Override
	protected ConfigurableApplicationContext createApplicationContext()
	{
		ConfigurableApplicationContext context = super.createApplicationContext();
		context.getBeanFactory().setParentBeanFactory(SpringBeanUtils.getBeanFactory());
		return context;
	}
}
