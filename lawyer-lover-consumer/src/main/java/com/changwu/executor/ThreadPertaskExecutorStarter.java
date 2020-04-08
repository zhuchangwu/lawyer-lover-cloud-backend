package com.changwu.executor;

import com.changwu.config.ConfigProperty;
import com.changwu.dto.CheckDeferredTaskDto;
import com.changwu.dto.PyDeferredTaskDto;
import com.changwu.service.AnalyserService;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.ThreadPerTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Changwu
 * @Date: 2019/11/7 21:13
 */
@Component
public class ThreadPertaskExecutorStarter {
    // 读取配置
    @Autowired
    private static ConfigProperty configProperty;
    // 用于轮询自增
    private static final AtomicInteger idx = new AtomicInteger();
    // 线程执行器
    private static ThreadPerTaskExecutor perTaskExecutor = new ThreadPerTaskExecutor(new DefaultThreadFactory(AnalyserService.class));
    // 任务队列
    public static LinkedBlockingQueue<PyDeferredTaskDto> pyTaskQueue = new LinkedBlockingQueue<>();
    public static LinkedBlockingQueue<CheckDeferredTaskDto> checkTaskQueue = new LinkedBlockingQueue<>();
    // 线程池与任务队列
    private static  BlockingQueue queue = new ArrayBlockingQueue<>(2000);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30, 50, 1, TimeUnit.MINUTES, queue, new ThreadPoolExecutor.CallerRunsPolicy());
    // 存放ip和端口的映射
    private static List<Map<String, String>> ipMap ;

    /**
     * 轮询获取ip
     * @return
     */
    public static Map<String, String> chooseIp () {
        return  ipMap.get(Math.abs(idx.getAndIncrement() % ipMap.size()));
    }

    static {
        try {
            new Thread(() -> {
                while (true) {
                    try {
                        PyDeferredTaskDto pyDto = pyTaskQueue.poll();
                        if (pyDto != null) {
                            ipMap=pyDto.getProperty().getIps();
                            Map<String, String> map = chooseIp();
                            threadPoolExecutor.execute(new PyTask(pyDto,map));
                        }
                        CheckDeferredTaskDto checkTask = checkTaskQueue.poll();
                        if (checkTask != null) {
                            threadPoolExecutor.execute(new CheckTask(checkTask));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
