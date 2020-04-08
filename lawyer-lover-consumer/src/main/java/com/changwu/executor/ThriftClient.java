package com.changwu.executor;

import com.changwu.config.ConfigProperty;
import com.changwu.thrift.Message;
import com.changwu.thrift.MessageService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池执行器
     * @Author: Changwu
 * @Date: 2019/12/3 14:48
 */

public class ThriftClient {

    public static String sendMessageToPy(String msg,String ip,String port){
        // 帮点端口号和超时时间
        TTransport tTransport = new TFramedTransport(new TSocket(ip,Integer.valueOf(port)),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        MessageService.Client client = new MessageService.Client(protocol);
        try{
            // 打开socket
            tTransport.open();
            // 发送
            Message  p = client.getResultFromPy(msg);
            return p.getMsg();
        }catch (Exception  e){
            e.printStackTrace();
        }finally {
            tTransport.close();
        }
        return null;
    }

}
