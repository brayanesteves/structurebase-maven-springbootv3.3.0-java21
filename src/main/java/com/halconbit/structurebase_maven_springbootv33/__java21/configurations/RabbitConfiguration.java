package com.halconbit.structurebase_maven_springbootv33.__java21.configurations;

import com.halconbit.structurebase_maven_springbootv33.__java21.utils.QueueListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class RabbitConfiguration {

    @Value("${app.config.queue.name}")
    private String queueName = null;
    @Value("${app.config.queue.topic}")
    private String topic = null;
    @Value("${app.config.queue.routing-key}")
    private String routingKey = null;
    @Value("#{new Boolean('$app.config.queue.durable}')}")
    private Boolean isDurable;
    private QueueListener listenerService;

    @Value("${app.config.push.queue.name}")
    private String pushQueueName = null;
    @Value("${app.config.push.queue.topic}")
    private String pushTopic = null;
    @Value("${app.config.push.queue.routing-key}")
    private String pushRoutingKey = null;
    private static final Boolean IS_EXCLUSIVE = false;
    private static final Boolean IS_AUTO_DELETE = false;

    @Bean("pushQueue")
    Queue pushQueue() {
        return new Queue(pushQueueName, isDurable, IS_EXCLUSIVE, IS_AUTO_DELETE);
    }

    @Bean("pushExchange")
    TopicExchange pushExchange() {
        return new TopicExchange(pushTopic);
    }

    @Bean("pushBinding")
    Binding pushBinding() {
        return BindingBuilder.bind(pushQueue()).to(pushExchange()).with(pushRoutingKey);
    }

    @Autowired
    private void setListenerService(QueueListener listenerService) {
        this.listenerService = listenerService;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, isDurable, IS_EXCLUSIVE, IS_AUTO_DELETE);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topic);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setMessageListener(listenerService);
        return container;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public SmartValidator smartValidator() {
        return new SmartValidator() {
            @Override
            public void validate(Object target, Errors errors, Object... validationHints) {
                //Custom validation not necessary
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return false;
            }

            @Override
            public void validate(Object target, Errors errors) {
                //another unnecessary validation
            }
        };
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        var factory = new DefaultMessageHandlerMethodFactory();
        factory.setValidator(smartValidator());
        return factory;
    }

}