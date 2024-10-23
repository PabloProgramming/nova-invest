package com.pablodev9.novainvest.service.mapper;

import java.util.List;

public interface IMapper <E, D> {
    E dtoToEntity(final D dto);
    D entityToDto(final E entity);

    List<E> dtosToEntities(final List<D> dtos);
    List<D> entitiesToDtos(final List<E> entities);

}
