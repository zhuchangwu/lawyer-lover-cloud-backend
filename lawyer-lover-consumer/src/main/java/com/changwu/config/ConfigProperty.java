package com.changwu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**'
 * 加载配置文件中的ip集合
 * @Author: Changwu
 * @Date: 2019/12/1 14:14
 */
@Service
@ConfigurationProperties(prefix = "changwu")
public class ConfigProperty {

    List<Map<String,String>> ips = new ArrayList<>();

    public List<Map<String, String>> getIps() {
        return ips;
    }

    public void setIps(List<Map<String, String>> ips) {
        this.ips = ips;
    }
}
