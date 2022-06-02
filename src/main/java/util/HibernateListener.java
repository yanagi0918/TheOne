package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtils.close();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		new HibernateUtils();
	}

}
