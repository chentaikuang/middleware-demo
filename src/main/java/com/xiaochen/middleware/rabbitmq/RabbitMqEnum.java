package com.xiaochen.middleware.rabbitmq;

/**
 * 定义rabbitMq需要的常量
 */
public class RabbitMqEnum {

    /**
     * 定义数据交换方式
     */
    public enum Exchange {
        FANOUT("xiaochen.fanout", "消息分发"),
        TOPIC("xiaochen.topic", "消息订阅"),
        DIRECT("xiaochen.direct", "点对点");

        private String code;
        private String name;

        Exchange(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 定义队列名称
     */
    public enum QueueName {
        XIAOCHEN_QUEUE("XIAOCHEN_QUEUE", "小臣测试队列"),
        TEST_TOPIC1("TEST_TOPIC1", "topic1测试队列"),
        TEST_TOPIC2("TEST_TOPIC2", "topic2测试队列");

        private String code;
        private String name;

        QueueName(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * 定义routing_key
     */
    public enum BindQueue {
        BIND_QUEUE("BIND_QUEUE", "BIND_QUEUE测试队列"),
        ROUTING_QUEUE1("*.TEST.*", "ROUTING_QUEUE1测试队列key"),
        ROUTING_QUEUE2("lazy.#", "ROUTING_QUEUE2测试队列key");

        private String code;
        private String name;

        BindQueue(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}