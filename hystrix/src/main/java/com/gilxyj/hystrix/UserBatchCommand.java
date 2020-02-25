package com.gilxyj.hystrix;

import com.gilxyj.commons.User;
import com.netflix.hystrix.HystrixCommand;

import java.util.List;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-24 19:58
 **/
public class UserBatchCommand extends HystrixCommand<List<User>> {

    private List<Integer> ids;

    private UserService userService;

    public UserBatchCommand(Setter setter, List<Integer> ids, UserService userService) {
        super(setter);
        this.ids = ids;
        this.userService = userService;
    }


    @Override
    protected List<User> run() throws Exception {
        return userService.getUserByIds(ids);
    }
}
