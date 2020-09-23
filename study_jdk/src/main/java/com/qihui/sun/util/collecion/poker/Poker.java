package com.qihui.sun.util.collecion.poker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Poker {
    public static String[] pokerColor = {"♥","♠","♦","♣"};
    public static String[] pokerNum = {"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
    public static List<String> pokers = new ArrayList<String>();
    public static Collection<String> player01 = new ArrayList<String>();
    public static Collection<String> player02 = new ArrayList<String>();
    public static Collection<String> player03 = new ArrayList<String>();
    public static Collection<String> diPai = new ArrayList<String>();

    static {
        pokers.add("大王");
        pokers.add("小王");
        for (String color : pokerColor) {
            for (String num : pokerNum) {
                pokers.add(color+num);
            }
        }
    }

    public static void sharePokers(){
        Collections.shuffle(pokers);
        diPai.add(pokers.get(0));
        diPai.add(pokers.get(1));
        diPai.add(pokers.get(2));
        for (int i = 3; i < pokers.size(); i++) {
            if (i%3==0){
                player01.add(pokers.get(i));
            }else if (i%3==1){
                player02.add(pokers.get(i));
            } else {
                player03.add(pokers.get(i));
            }
        }
    }

    public static void lookPokers(){
        System.out.println("player01:"+player01);
        System.out.println("player02:"+player02);
        System.out.println("player03:"+player03);
        System.out.println("diPai:"+diPai);
    }

}
