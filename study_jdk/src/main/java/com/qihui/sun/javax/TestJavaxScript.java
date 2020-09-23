package com.qihui.sun.javax;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJavaxScript {
    public static void main(String[] args) throws ScriptException {
        testJavaxScript();
    }

    private static void testJavaxScript() throws ScriptException {
        String jsStr = "var expression = '6 / 3';"
                +  "var res = eval(expression);";
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine javascript = engineManager.getEngineByName("javascript");
        javascript.eval(jsStr);
        String numStr = String.valueOf(javascript.get("res"));
        Integer integer = Integer.parseInt(numStr);
        System.out.println(integer);
    }
}
