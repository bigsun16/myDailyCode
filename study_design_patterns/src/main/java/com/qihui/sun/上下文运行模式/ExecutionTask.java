package com.qihui.sun.上下文运行模式;

public class ExecutionTask implements Runnable {
    private QueryFromDBAction queryAction = new QueryFromDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        queryAction.execute();
        System.out.println("The name query successful...");
        httpAction.execute();
        System.out.println("The cardId query successful...");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is " + context.getName() + " and card id is " + context.getCardId());
    }
}
