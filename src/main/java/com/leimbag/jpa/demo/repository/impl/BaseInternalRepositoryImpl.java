package com.leimbag.jpa.demo.repository.impl;

import com.leimbag.jpa.demo.repository.BaseInternalRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.io.Serializable;

/**
 * @author leimbag
 */
public class BaseInternalRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseInternalRepository<T, ID> {
    private final EntityManager entityManager;

    public BaseInternalRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseInternalRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public T findOneForUpdate(ID id) {
        return entityManager.find(this.getDomainClass(), id, LockModeType.PESSIMISTIC_WRITE);
    }
}
