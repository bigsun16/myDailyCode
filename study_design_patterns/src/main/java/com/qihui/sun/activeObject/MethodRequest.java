package com.qihui.sun.activeObject;

public abstract class MethodRequest {

    protected final Servant servant;
    protected final FutureResult futureResult;

    protected MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();

}
