package com.leimbag.jpa.demo.service.user;

import com.leimbag.jpa.demo.bean.response.Response;
import com.leimbag.jpa.demo.bean.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author leimbag
 */
public interface UserService {

    void save(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<User> findAllByName(String name);

    Response<User> findByName(String name, Pageable pageable);
}
