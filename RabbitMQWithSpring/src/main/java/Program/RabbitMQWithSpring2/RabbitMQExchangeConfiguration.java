package Program.RabbitMQWithSpring2;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration {

	// One way to create exchange
	@Bean
	Exchange exampleExchange()
	{
		return new TopicExchange("ExampleExchange");
	}
	
	// Second way to create exchange
	@Bean
	Exchange example2ndExchange()
	{
		return ExchangeBuilder.directExchange("Example2ndExchange")
				.autoDelete()
				.internal()
				.build();
	}
	
	@Bean
	Exchange newExchange()
	{
		return ExchangeBuilder.topicExchange("TopicTestExchange")
				.autoDelete()
				.durable(true)
				.internal()
			    .build();
	}
	
	@Bean
	Exchange fanoutExchange()
	{
		return ExchangeBuilder.fanoutExchange("FanoutTestExchange")
				.autoDelete()
				.durable(true)
				.internal()
				.build();
	}
	
	@Bean
	Exchange headerExchange()
	{
		return ExchangeBuilder.headersExchange("HeadersTestExchange")
				.internal()
				.durable(true)
				.build();
	}
}
