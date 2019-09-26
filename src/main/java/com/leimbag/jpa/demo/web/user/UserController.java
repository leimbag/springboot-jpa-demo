package com.leimbag.jpa.demo.web.user;

import com.leimbag.jpa.demo.bean.response.Response;
import com.leimbag.jpa.demo.bean.user.User;
import com.leimbag.jpa.demo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leimbag
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // http://localhost:8080/user/save?name=%E5%93%87%E5%93%88%E5%93%88&password=1dada&age=18&enableStatus=ENABLE
    @RequestMapping(value = "/save")
    public String save(User user) {
        userService.save(user);
        logger.info("保存用户成功,name={}", user.getName());
        return "success";
    }

    @RequestMapping(value = "/update")
    public String update(User user) {
        userService.save(user);
        logger.info("更新用户成功,name={}", user.getName());
        return "success";
    }

    @RequestMapping(value = "/get")
    public User get(Long id) {
        User user = userService.get(id);
        logger.info("查询用户成功,name={}", user.getName());
        return user;
    }

    // http://localhost:8080/user/findByName?name=%E5%93%87%E5%93%88%E5%93%88&page=0&pageSize=10
    @RequestMapping(value = "/findByName")
    public Response<User> findByName(String name,Integer page, Integer pageSize){
        PageRequest pageRequest = new PageRequest(page, pageSize);
        return userService.findByName(name, pageRequest);
    }

}
