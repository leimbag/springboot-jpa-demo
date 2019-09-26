package com.leimbag.jpa.demo.enums.converter;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author leimbag
 */
public abstract class BaseEnumConverter<T> implements AttributeConverter<T, Integer> {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<T> getActualType() {
        ParameterizedType paramType = (ParameterizedType)this.getClass().getGenericSuperclass();

        return  (Class<T>)paramType.getActualTypeArguments()[0];
    }

    public Integer convertToDatabaseColumn(T type) {
        try {
            return Integer.valueOf(BeanUtils.getProperty(type, "value"));
        } catch (Exception e) {
            logger.error("自定义enum序列化出错, value={}", type);
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public T convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }

        Method getMethod = ReflectionUtils.findMethod(this.getActualType(), "get", int.class);
        if (getMethod == null) {
            logger.error("未找到转换方法");
            return null;
        }

        try {
            return (T) ReflectionUtils.invokeMethod(getMethod, null, integer);
        } catch (IllegalStateException e) {
            logger.error(String.format("数据库记录值转实体字段值出错, 数据库值=%s, 实体字段类型=%s", integer, this.getActualType()));
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
