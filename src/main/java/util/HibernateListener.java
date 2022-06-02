package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

@WebListener
public class HibernateListener implements ServletContextListener {
	SessionFactory factory;
	
	public HibernateListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		factory.close();
	}
	
}
