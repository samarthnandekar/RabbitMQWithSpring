package Program.RabbitMQWithSpring2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {

	// one way to create new queue
	@Bean
	Queue exampleQueue()
	{
		return new Queue("ExampleQueue",false);
	}
	
	// 2nd way to create queue
	@Bean
	Queue example2ndQueue()
	{
		return QueueBuilder.durable("Example2ndQueue")
				.autoDelete()
				.exclusive()
				.build();
	}
}
