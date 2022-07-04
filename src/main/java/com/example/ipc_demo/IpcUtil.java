package com.example.ipc_demo;

import org.apache.commons.lang3.StringUtils;

public class IpcUtil {

    public static String alterHexa(int decimal){
        return Integer.toHexString(decimal);
    }

    public static String zeroRightAdd(String data, int len){
        return StringUtils.rightPad(data, len, "0");
    }
    public static String zeroLeftAdd(String data, int len){
        return StringUtils.leftPad(data, len, "0");
    }
}
