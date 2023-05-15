package com.proxyseller.notes.dto;

public interface Mapper<E, D> {

	D mapEntityToDto(E entity);
}
