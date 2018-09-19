package com.xiaochen.middleware.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="zookeeper.server")
public class ZooKeeperConfig {
    private String url;
    private int port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ZooKeeperConfig{" +

                "url='" + url + '\'' +
                ", port=" + port +
                '}';
    }
}
