package com.qihui.sun.上下文运行模式;

public class QueryFromDBAction {

    public void execute() {

        try {
            Thread.sleep(1_000);
            String name = "Jack " + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
