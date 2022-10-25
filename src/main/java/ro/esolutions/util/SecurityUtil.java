package ro.esolutions.util;

public class SecurityUtil {
    public static String getLoggedUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return (authentication == null) ? "none" : authentication.getName();
        return "user";
    }
}
