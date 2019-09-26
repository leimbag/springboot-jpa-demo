package com.leimbag.jpa.demo.service.impl.user;

import com.leimbag.jpa.demo.bean.response.Response;
import com.leimbag.jpa.demo.bean.user.User;
import com.leimbag.jpa.demo.entity.user.UserEntity;
import com.leimbag.jpa.demo.repository.user.UserRepository;
import com.leimbag.jpa.demo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leimbag
 */
@Service
public class UserServiceImpl implements UserService {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Transient
    public void save(User user) {
        userRepository.save(beanToEntity(user));
    }

    @Override
    public User get(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return entityToBean(userEntity);
    }

    @Override
    @Transient
    public User update(User user) {
        UserEntity userEntity = userRepository.findOneForUpdate(user.getId());
        userEntity.setAge(user.getAge());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEnableStatus(user.getEnableStatus());

        userEntity = userRepository.save(userEntity);
        return entityToBean(userEntity);
    }

    @Override
    @Transient
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByName(String name) {
        List<UserEntity> userEntityList = userRepository.findAllByName(name);
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(entityToBean(userEntity));
        }
        return userList;
    }

    @Override
    public Response<User> findByName(String name, Pageable pageable) {
        Specification<UserEntity> specification = new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (name != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
                    predicatesList.add(predicate);
                }

                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

                Predicate[] predicates = new Predicate[predicatesList.size()];

                return criteriaBuilder.and(predicatesList.toArray(predicates));
            }
        };
        Page<UserEntity> page = userRepository.findAll(specification, pageable);

        Response<User> response = new Response<>();
        response.setPage(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotal(page.getTotalElements());
        List<UserEntity> userEntityList = page.getContent();
        List<User> userList = new ArrayList<>(userEntityList.size());
        for(UserEntity userEntity : userEntityList){
            userList.add(entityToBean(userEntity));
        }
        response.setPagedData(userList);

        return response;
    }

    private User entityToBean(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setPassword(userEntity.getPassword());
        user.setAge(userEntity.getAge());
        user.setCreatedAt(userEntity.getCreatedAt());
        user.setEnableStatus(userEntity.getEnableStatus());
        return user;
    }

    private UserEntity beanToEntity(User user) {
        if (user == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAge(user.getAge());
        userEntity.setId(user.getId());
        userEntity.setEnableStatus(user.getEnableStatus());
        return userEntity;
    }
}
