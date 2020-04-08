package com.gilxyj.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-07 00:55
 **/

//@EnableBinding 表示绑定Sink消息通道
@EnableBinding(Sink.class)
public class MsgReceiver {

    private static final Logger LOGGER= LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        LOGGER.info("Received:"+payload);
    }
}
