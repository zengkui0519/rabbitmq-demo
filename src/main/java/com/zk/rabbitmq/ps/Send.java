package com.zk.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zk.rabbitmq.util.ConnectionUtil;

public class Send {

	private final static String EXCHANGE_NAME = "ps_pattern_exchange_fanout";

	public static void main(String[] args) throws Exception {
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//声明exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//发送消息
		for(int i=0;i<100;i++){
			String msg = i+" Hello RabbitMQ!";
			channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
			System.out.println("send msg: " + msg);
		}
		//关闭连接
		channel.close();
		connection.close();
	}
}
