package kr.ac.jejunu;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserLisner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** lisnerInit *****************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("*************** lisner Destroy *****************");
    }
}
