package com.gilxyj.stream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;


/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-07 23:36
 **/
@EnableBinding(MyChannel.class)
public class MsgReceiver2 {

    private static final Logger LOGGER= LoggerFactory.getLogger(MsgReceiver2.class);

    @StreamListener(MyChannel.INPUT)
    public void receive(Object payload){
        LOGGER.info("receive2:"+new Date() +":"+payload);
    }
}
