package com.changwu.executor;

import com.changwu.doc.Crime;
import com.changwu.doc.CrimeAnalyzed;
import com.changwu.dto.PyDeferredTaskDto;
import com.changwu.repository.CrimeAnalyzedRepository;
import com.changwu.repository.CrimeRepository;
import com.changwu.utils.JsonUtils;
import com.changwu.utils.ResultVOUtil;
import org.springframework.web.context.request.async.DeferredResult;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

/**
 * 连接py获取结果
 *
 * @Author: Changwu
 * @Date: 2019/11/6 10:34
 */
public class PyTask implements Runnable {
    private DeferredResult deferredResult;
    private CrimeAnalyzedRepository crimeAnalyzedRepository;
    private CrimeRepository crimeRepository;
    private String content;
    private Map<String, String> ipPortMap;

    public PyTask(PyDeferredTaskDto deferredTaskDto, Map<String, String> ipPortMap) {
        this.ipPortMap = ipPortMap;
        this.deferredResult = deferredTaskDto.getDeferredResult();
        this.content = deferredTaskDto.getContent();
        this.crimeRepository = deferredTaskDto.getCrimeRepository();
        this.crimeAnalyzedRepository = deferredTaskDto.getCrimeAnalyzedRepository();
    }

    @Override
    public void run() {
        connnect(this.deferredResult, this.content, this.crimeRepository);
    }

    public void connnect(DeferredResult deferredResult, String content, CrimeRepository crimeRepository) {
        Socket socket = null;
        try {
            // 随机获取一个ip
            String ip = ipPortMap.get("ip");
            String port = ipPortMap.get("port");
            //发送消息
            String targetName = ThriftClient.sendMessageToPy(content, ip, port);
            if (targetName == null) {
                deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.error(503, "对不起, 由于网络原因,未收到机器学习集群中的恢复.")));
                crimeAnalyzedRepository.save(new CrimeAnalyzed(content, "空结果..."));
                return;
            }
            // 查询数据库
            Crime byCrimeName = this.crimeRepository.findByCrimeName(targetName + "罪");
            if (byCrimeName == null) {
                Crime crime = new Crime(targetName + "罪");
                deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.success(crime)));
                return;
            }
            byCrimeName.setId(null);
            // 将值填充进 deferredResult
            deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.success(byCrimeName)));
            // 存根
            crimeAnalyzedRepository.save(new CrimeAnalyzed(content, targetName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
