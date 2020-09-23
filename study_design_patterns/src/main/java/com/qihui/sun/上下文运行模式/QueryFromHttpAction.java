package com.qihui.sun.上下文运行模式;

public class QueryFromHttpAction {

    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123456789 " + Thread.currentThread().getId();
    }
}
