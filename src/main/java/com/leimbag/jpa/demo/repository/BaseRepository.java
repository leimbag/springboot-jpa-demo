package com.leimbag.jpa.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author leimbag
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends BaseInternalRepository<T, ID>, JpaSpecificationExecutor<T> {
    <S extends T> S save(S s);

    <S extends T> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAllById(Iterable<ID> iterable);

    void flush();

    <S extends T> S saveAndFlush(S s);

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);
}
