package com.qihui.sun.io.fileHandler2;

import java.util.List;

//其实就是初始化父类传个encode
public class ProcessDataByPostgisListeners extends ReaderFileListener {
    public ProcessDataByPostgisListeners(String encode) {
        super.setEncode(encode);
    }

    @Override
    public void output(List<String> stringList) throws Exception {
        // 这个方法记得写 要不nio没有输出
    }
}
