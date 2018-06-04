package com.zk.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("testAdmin");
        factory.setPassword("testAdmin");
        factory.setVirtualHost("/test");	//和创建的虚拟机名称要一致
        //创建新连接
        return factory.newConnection();
    }

}
