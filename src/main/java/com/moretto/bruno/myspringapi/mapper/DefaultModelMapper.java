package com.moretto.bruno.myspringapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component
public class DefaultModelMapper<D, E> {

    private final ParameterizedTypeReference<D> dtoClass;
    private final ParameterizedTypeReference<E> entityClass;

    public DefaultModelMapper() {
        this.dtoClass = new ParameterizedTypeReference<>() { };
        this.entityClass = new ParameterizedTypeReference<>() { };
    }

    private final ModelMapper modelMapper = new ModelMapper();

    public D toDTO(E entity) {
        return modelMapper.map(entity, dtoClass.getType());
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass.getType());
    }

}
