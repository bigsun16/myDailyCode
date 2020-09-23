package com.qihui.sun.activeObject;

class ActiveObjectProxy implements ActiveObject {
    private final ScheduleThread scheduleThread;
    private final Servant servant;

    public ActiveObjectProxy(ScheduleThread scheduleThread, Servant servant) {
        this.scheduleThread = scheduleThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fillChar) {
        FutureResult future = new FutureResult();
        scheduleThread.invoke(new MakeStringRequest(servant, future, count, fillChar));
        return future;
    }

    @Override
    public void displayString(String text) {
        scheduleThread.invoke(new DisplayStringRequest(servant, text));
    }
}
