package com.zk.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zk.rabbitmq.util.ConnectionUtil;

public class Send {

	private final static String QUEUE_NAME = "work_pattern_queue";

	public static void main(String[] args) throws Exception {
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//消息队列声明或者绑定
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//发送消息
		for(int i=0;i<1000;i++){
			String msg = i+" Hello RabbitMQ!";
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			System.out.println("send msg: " + msg);
		}
		//关闭连接
		channel.close();
		connection.close();
	}
}
