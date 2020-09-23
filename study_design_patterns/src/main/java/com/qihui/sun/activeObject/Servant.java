package com.qihui.sun.activeObject;

class Servant implements ActiveObject {
    @Override
    public Result makeString(int count, char fillChar) {
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(new String(buff));
    }

    @Override
    public void displayString(String text) {
        System.out.println("Display: " + text);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
