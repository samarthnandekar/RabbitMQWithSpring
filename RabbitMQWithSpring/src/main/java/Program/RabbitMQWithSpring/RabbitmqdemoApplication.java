package Program.RabbitMQWithSpring;

import java.util.concurrent.TimeUnit;

import javax.validation.OverridesAttribute;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;

@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitListener listener;


	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitmqdemoApplication.class, args);
		
	}

	public void run(String... strings) throws Exception {
		System.out.println("iiiiiiiiiii");
		//rabbitTemplate.convertAndSend(RabbitConfig.getQueue_name(), "Hello from RabbitMQ");
		rabbitTemplate.convertAndSend("MyTopicExchange","topic","Hello from RabbitMQ111");
		listener.getCountDownLatch().await(10000, TimeUnit.MICROSECONDS);
	}
}
