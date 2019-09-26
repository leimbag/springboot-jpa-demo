package com.leimbag.jpa.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * @author leimbag
 */
@NoRepositoryBean
public interface BaseInternalRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T findOneForUpdate(ID id);

    Page<T> findAll(Specification<T> spec, Pageable pageable);
}
