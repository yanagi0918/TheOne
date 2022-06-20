package config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//改寫,相當於mvc-servlet.xml的java程式組態
//改java把下面3註解去掉
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
@Configuration
@ComponentScan(basePackages = {"java"})
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "gogomember");
		registry.addViewController("/funny.sweet").setViewName("form");
		registry.addViewController("/showresources.info").setViewName("showResources");
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver irViewResolver() {
		InternalResourceViewResolver irvr1 = new InternalResourceViewResolver();
		irvr1.setPrefix("/WEB-INF/");
		irvr1.setSuffix(".jsp");
		irvr1.setOrder(6);
		return irvr1;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr1 = new CommonsMultipartResolver();
		cmr1.setDefaultEncoding("UTF-8");
		return cmr1;
	}
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView json2View = new MappingJackson2JsonView();
		json2View.setPrettyPrint(true);
		return json2View;
	}
	@Bean
	public Jaxb2Marshaller jxJaxb2Marshaller() {
		Jaxb2Marshaller jaxb2 = new Jaxb2Marshaller();
		jaxb2.setPackagesToScan("tw.lee");
		return jaxb2;
	}
	@Bean
	public ContentNegotiatingViewResolver negotiatingViewResolver() {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		
		ArrayList<View> list = new ArrayList<View>();
		list.add(jsonView());
		
		cnvr.setDefaultViews(list);
		return cnvr;
	}
}
