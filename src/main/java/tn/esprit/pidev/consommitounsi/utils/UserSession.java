package tn.esprit.pidev.consommitounsi.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;

import javax.servlet.http.HttpSession;

public class UserSession {

    private static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public static void setUser(User user) {
        getSession().setAttribute("user", user);
    }

    public static User getUser() {
        return (User)getSession().getAttribute("user");
    }

    public static boolean isLogged() {
        return getUser()!=null;
    }

    public static boolean isCustomer() {
        return (isLogged() && getUser().getType()==UserType.CUSTOMER);
    }

    public static boolean isDeliverer() {
        return (isLogged() && getUser().getType()== UserType.DELIVERER);
    }

    public static boolean isAdmin() {
        return (isLogged() && getUser().getType()== UserType.ADMIN);
    }

    public static boolean hasId(long id) {
        return isLogged() && getUser().getId()==id;
    }
}
