package kr.ac.jejunu;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestLisner implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("*************** request Destroy *****************");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("*************** requestInit *****************");
    }
}
