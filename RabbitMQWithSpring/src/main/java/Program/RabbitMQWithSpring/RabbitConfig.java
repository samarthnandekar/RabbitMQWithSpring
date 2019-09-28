package Program.RabbitMQWithSpring;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import Program.RabbitMQWithSpring.RabbitListener;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	 static final String queue_name = "MyQueue";

	    @Bean
	    public Queue queue() {
	        return new Queue(queue_name, false);
	    }

	    /*
	    @Bean
	    public TopicExchange topicExchange() {
	        return new TopicExchange("rabbit_test_exchange");
	    }

	    @Bean
	    public Binding queueBinding() {
	        return BindingBuilder.bind(queue()).to(topicExchange()).with(queue_name);
	    }
	    */
	    
	    @Bean
	    Exchange myExchange()
	    {
	    	return ExchangeBuilder.topicExchange("MyTopicExchange")
	    			.durable(true)
	    			.build();
	    }
	    
	    
	    @Bean
	    Binding binding()
	    {
	    	// one way to create binding
	    	//return new Binding(queue_name,Binding.DestinationType.QUEUE,"MyTopicExchange","topic",null);
	    	
	    	// second way to create binding
	    	return BindingBuilder
	    			.bind(queue())
	    			.to(myExchange())
	    			.with("topic")
	    			.noargs();
	    }
	    
	    @Bean
	    ConnectionFactory connectionFactory()
	    {
	    	CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
	    	cachingConnectionFactory.setUsername("guest");
	    	cachingConnectionFactory.setPassword("guest");
	    	return cachingConnectionFactory;
	    }

	    @Bean
	    SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
	                                                            MessageListenerAdapter listenerAdapter) {
	        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
	        messageListenerContainer.setConnectionFactory(connectionFactory);
	        messageListenerContainer.setMessageListener(listenerAdapter);
	        messageListenerContainer.setQueueNames(queue_name);
	        return messageListenerContainer;
	    }

	    @Bean
	    MessageListenerAdapter messagelistenerAdapter(RabbitListener rabbitListener) {
	        return new MessageListenerAdapter(rabbitListener, "receiveMessage");
	    }

	    public static String getQueue_name() {
	        return queue_name;
	    }
}
