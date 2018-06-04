package com.zk.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zk.rabbitmq.util.ConnectionUtil;

public class Send {

	private final static String EXCHANGE_NAME = "routing_pattern_exchange_direct";

	public static void main(String[] args) throws Exception {
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//声明exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		//发送消息
		String msg = "Hello RabbitMQ!";
		String routingKey = "test_routing_key";
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
		System.out.println(msg);
		//关闭连接
		channel.close();
		connection.close();
	}
}
