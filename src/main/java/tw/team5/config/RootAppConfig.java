package tw.team5.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//相當於beans.config.xml的java程式組態
@Configuration
@ComponentScan(basePackages = "tw.team5.bean")
@EnableWebMvc
@EnableTransactionManagement
public class RootAppConfig {
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
		jndiBean.afterPropertiesSet();
		DataSource ds = (DataSource)jndiBean.getObject();
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("tw.lee.model");
		factoryBean.setHibernateProperties(addtionalProperties());
		return factoryBean;
	}

	private Properties addtionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		props.put("hibernate.hbm2ddl.auto", "update");
		return props;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory);
		return txMgr;
	}
	
}
