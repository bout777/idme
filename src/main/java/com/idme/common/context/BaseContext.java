package com.idme.common.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadRole = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void setCurrentRole(String role) {
        threadRole.set(role);
    }

    public static String getCurrentRole() {
        return threadRole.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
