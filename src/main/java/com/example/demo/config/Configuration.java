package com.example.demo.config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

import com.example.demo.receiver.Receiver;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	private static final String QUEUE_NAME = "coffee.queue";
	private static final String JIEUN_QUEUE_NAME = "jieun.queue";
	private static final String RESULT_QUEUE_NAME = "result.queue";
	
	@Bean
	public TopicExchange topic() {
		return new TopicExchange("tut.topic");
	}
	@Bean
	public DirectExchange direct() {
		return new DirectExchange("tut.direct");
	}
	
	private static class ReceiverConfig {

        @Bean
        public Receiver receiver() {
            return new Receiver();
        }

        @Bean
        public Queue queue() {
            return new Queue(QUEUE_NAME);
        }
        
        @Bean
        public Queue jieunqueue() {
            return new Queue(JIEUN_QUEUE_NAME);
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }
        @Bean
        public Queue resultQueue() {
            return new Queue(RESULT_QUEUE_NAME);
        }
        
        @Bean
        public Binding resultbinding(DirectExchange direct) {
            return BindingBuilder.bind(resultQueue())
                    .to(direct)
                    .with("result");
        }

        @Bean
        public Binding binding1a(TopicExchange topic) {
            return BindingBuilder.bind(queue())
                    .to(topic)
                    .with("*.orange.*");
        }
        @Bean
        public Binding jieunbinding(TopicExchange topic) {
            return BindingBuilder.bind(jieunqueue())
                    .to(topic)
                    .with("jieun.#");
        }

        @Bean
        public Binding binding1b(TopicExchange topic2,
                                 Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(topic2)
                    .with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic,
                                 Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2)
                    .to(topic)
                    .with("lazy.#");
        }

    }

}
