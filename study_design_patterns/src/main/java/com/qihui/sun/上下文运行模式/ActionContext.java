package com.qihui.sun.上下文运行模式;

public class ActionContext {
    private static final ThreadLocal<Context> threadLocal = ThreadLocal.withInitial(Context::new);

    private ActionContext() {
    }

    public static class ContextHolder {
        public static final ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
