package com.example.ipc_demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class IpcService {
    public String RequestSet(String tId, String stat){
        //Hexa String(별도 정의)(4)
        String sigid = "0201";
        //Hexa String(별도 정의)(8)
        String flag = "00000000";
        //(8)
        String destPrcs = "00000001";
        //(8)
        String srcPrcs= "0010002";
        //Base64 encoded data
        String encodedData= encodeDataSet(tId, stat);
        log.info("encodedData: {}", encodedData);
        //전체길이 -> 16진수(4)
        int len = 4 + sigid.length() + flag.length() + destPrcs.length() + srcPrcs.length() + encodedData.length();
        log.info("len: {}", len);
        String lenHexa = IpcUtil.alterHexa(len);

        String str = String.format("%s,%s,%s,%s,%s,%s", IpcUtil.zeroLeftAdd(lenHexa, 4), sigid, flag, destPrcs, srcPrcs, encodedData);
        log.info("str: {}", str);
        return str;
    }

    public String encodeDataSet(String tId, String stat){
        //Response받을 Key(UUID) (36-자리수까지 0으로 채움)
        String reqId = UUID.randomUUID().toString().replaceAll("-", "");
        //TID(AGW 장비) (36-자리수까지 0으로 채움)
        //요청 command (256)
        String command = stat;

        String data = String.format("%s,%s,%s", IpcUtil.zeroRightAdd(reqId, 36), IpcUtil.zeroRightAdd(tId, 36), IpcUtil.zeroRightAdd(command, 256));
        log.info("data: {}", data);
        return new String(Base64.encodeBase64(data.getBytes()));
    }
}
