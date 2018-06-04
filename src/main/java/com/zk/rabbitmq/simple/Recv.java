package com.zk.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zk.rabbitmq.util.ConnectionUtil;

public class Recv {

	private final static String QUEUE_NAME = "simple_pattern_queue";

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//消息队列声明或者绑定
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//接收消息
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//监听消息队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println("receive msg: " + msg);
		}
	}
}
