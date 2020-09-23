package com.qihui.sun.activeObject;

public class MakerClientThread extends Thread {

    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(200);
                String resultValue = String.valueOf(result.getResultValue());
                System.out.println(Thread.currentThread().getName() + " resultValue: " + resultValue);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
