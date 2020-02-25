package com.gilxyj.hystrix;

import com.gilxyj.commons.User;
import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-24 21:33
 **/
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Integer> {


    private UserService userService;

    private Integer id;

    public UserCollapseCommand(UserService userService, Integer id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserCollapseCmd")).andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.userService = userService;
        this.id = id;
    }

    /***
     * 请求参数
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并的方法
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {

        List<Integer> ids= new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            ids.add(userIntegerCollapsedRequest.getArgument());
        }

        return new UserBatchCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserBatchCmd")), ids, userService);
    }

    /**
     * 请求结果分发
     * @param users
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {

        int count=0;

        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            userIntegerCollapsedRequest.setResponse(users.get(count++));
        }
    }
}
