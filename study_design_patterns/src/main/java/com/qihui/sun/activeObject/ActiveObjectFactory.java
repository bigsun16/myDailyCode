package com.qihui.sun.activeObject;

public class ActiveObjectFactory {
    private ActiveObjectFactory() {
    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(queue);
        ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(scheduleThread, servant);
        scheduleThread.start();
        return activeObjectProxy;
    }
}
