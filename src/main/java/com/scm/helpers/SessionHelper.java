package com.scm.helpers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    public static void removeMessage() {
        try {
            System.out.println("Message remove from sessionHelper");
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attr != null) {
                HttpSession session = attr.getRequest().getSession();
                session.removeAttribute("message");
            }
        } catch (Exception e) {
            System.out.println("Error in SessionHelper" + e);
            e.printStackTrace();
        }
    }
}
