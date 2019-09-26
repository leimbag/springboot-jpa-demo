package com.leimbag.jpa.demo.repository.user;

import com.leimbag.jpa.demo.entity.user.UserEntity;
import com.leimbag.jpa.demo.repository.BaseRepository;

import java.util.List;

/**
 * @author leimbag
 */
public interface UserRepository extends BaseRepository<UserEntity, Long> {
    List<UserEntity> findAllByName(String name);
    void deleteById(Long id);
}
