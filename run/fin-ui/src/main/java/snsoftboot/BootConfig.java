package snsoftboot;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import snsoft.servlet.SessionTrace;
import snsoft.servlet.WebStartServlet;
import snsoft.servlet.cxf.CXFWebServiceServlet;
import snsoft.servlet.filter.DefaultFilter;
import snsoft.servlet.filter.UserSessionFilter;
import snsoft.servlet.fs.FileSystemServlet;
import snsoft.servlet.jdbc.JdbcServlet;
import snsoft.ui.servlet.UIInvokeServlet;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2017年8月10日下午2:03:38</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class BootConfig
{
	@Autowired
	protected Environment env;

	@Bean
	public DefaultFilter filter_DefaultFilter()
	{
		DefaultFilter filter = new DefaultFilter();
		return filter;
	}

	@Bean
	public FilterRegistrationBean fitlerRegister(DefaultFilter myFilter)
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(myFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("filters", "*.~gzip,*.$gzip:snsoft.servlet.filter.AddGZipHeaderFilter");
		return filterRegistrationBean;
	}

	@Bean
	public UserSessionFilter filter_userSession()
	{
		return new UserSessionFilter();
	}

	@Bean
	public FilterRegistrationBean filter_userSession_register(UserSessionFilter myFilter)
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(myFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/ui/*");
		filterRegistrationBean.addUrlPatterns("/cxfservices/*");
		filterRegistrationBean.addUrlPatterns("/uiinvoke/*");
		filterRegistrationBean.addUrlPatterns("/fs/*");
		filterRegistrationBean.addUrlPatterns("/ws/*");
		filterRegistrationBean.addUrlPatterns("/do/*");
		return filterRegistrationBean;
	}
	//	@Bean
	//	public RequestMappingHandlerMapping handlerMapping()
	//	{
	//		return new RequestMappingHandlerMapping();
	//	}

	//	@Bean
	//	public RequestMappingHandlerAdapter RequestMappingHandlerAdapter()
	//	{
	//		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
	//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	//		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
	//		messageConverters.add(new MappingJackson2HttpMessageConverter());
	//		adapter.setMessageConverters(messageConverters );
	//		return adapter;
	//	}
	@Bean
	public SessionTrace listener_SessionTrace()
	{
		return new SessionTrace();
	}

	@Bean
	public CXFWebServiceServlet servlet_cxf()
	{
		return new CXFWebServiceServlet();
	}

	@Bean
	public ServletRegistrationBean servlet_cxf_mapping(CXFWebServiceServlet servlet)
	{
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(servlet);
		bean.addUrlMappings("/cxfservices/*");
		return bean;
	}

	@Bean
	public UIInvokeServlet servlet_uiinvoke()
	{
		return new UIInvokeServlet();
	}

	@Bean
	public ServletRegistrationBean servlet_uiinvoke_mapping(UIInvokeServlet servlet)
	{
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(servlet);
		bean.setOrder(0);
		bean.addUrlMappings("/uiinvoke/*");
		return bean;
	}

	@Bean
	public FileSystemServlet servlet_fileSystem()
	{
		return new FileSystemServlet();
	}

	@Bean
	public ServletRegistrationBean servlet_fileSystem_mapping(FileSystemServlet servlet)
	{
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(servlet);
		bean.setOrder(0);
		bean.addUrlMappings("/fs/*");
		return bean;
	}

	@Bean
	public WebStartServlet servlet_webStart()
	{
		return new WebStartServlet();
	}

	@Bean
	public ServletRegistrationBean servlet_webStart_mapping(WebStartServlet servlet)
	{
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(servlet);
		bean.addUrlMappings("/webstart.jnlp");
		return bean;
	}

	@Bean
	public JdbcServlet servlet_jdbc()
	{
		return new JdbcServlet();
	}

	@Bean
	public ServletRegistrationBean servlet_jdbc_mapping(JdbcServlet servlet)
	{
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(servlet);
		bean.addUrlMappings("/jdbc/*");
		return bean;
	}

	//	@Bean
	//	public UIController servlet_uicontroller()
	//	{
	//		return new UIController();
	//	}
	//	
	//	@Bean
	//	public ServletRegistrationBean servlet_uicontroller_mapping(UIController servlet)
	//	{
	//		ServletRegistrationBean bean = new ServletRegistrationBean();
	//		bean.setServlet(servlet);
	//		bean.addUrlMappings("/ui/*");
	//		return bean;
	//	}
	@Bean
	public BootFactory bootFactory()
	{
		return new BootFactory();
	}

	@Bean
	public WebMvcConfigurerAdapter webMvcConfig()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addViewControllers(ViewControllerRegistry registry)
			{
				registry.addRedirectViewController("/", "/Login.html");
				registry.addRedirectViewController("/admin", "/admin/index.html");
				//			registry.addRedirectViewController("/error", "/error.html");
				registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
				super.addViewControllers(registry);
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry)
			{
			}
		};
	}

	//	@Bean
	//	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	//	{
	//		Properties properties = new Properties();
	//		properties.setProperty("java.lang.Throwable", "/Login.html");
	//		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
	//		exceptionResolver.setExceptionMappings(properties);
	//		return exceptionResolver;
	//	}
	public class BootFactory extends TomcatEmbeddedServletContainerFactory
	{
		@Override
		protected void configureContext(Context context, ServletContextInitializer[] initializers)
		{
			super.configureContext(context, initializers);
			context.addPropertyChangeListener(new PropertyChangeListener()
			{
				@Override
				public void propertyChange(PropertyChangeEvent evt)
				{
					if ("resources".equals(evt.getPropertyName()))
					{
						StandardContext context = (StandardContext) evt.getSource();
						WebResourceRoot root = context.getResources();
						StandardRoot xx = (StandardRoot) root;
						for (int i = 1;; i++)
						{
							String path = env.getProperty("web.path" + i);
							if (path == null)
							{
								break;
							}
							xx.addPreResources(new DirResourceSet(root, "/", path, "/"));
						}
					}
				}
			});
		}
	}
}
