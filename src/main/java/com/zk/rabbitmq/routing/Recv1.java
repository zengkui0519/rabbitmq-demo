package com.zk.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zk.rabbitmq.util.ConnectionUtil;

public class Recv1 {

	private final static String QUEUE_NAME = "routing_pattern_queue1";

	private final static String EXCHANGE_NAME = "routing_pattern_exchange_direct";

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//消息队列声明或者绑定
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//绑定队列到交换机
		String routingKey = "test_routing_key";
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routingKey);
		//同一时刻服务器只会发送一条消息给消费者
		channel.basicQos(1);
		//定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//监听消息队列，手动返回完成
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println(msg);
			//手动确认消息消费完成
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
