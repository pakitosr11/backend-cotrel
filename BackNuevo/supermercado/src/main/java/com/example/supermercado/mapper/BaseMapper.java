package com.example.supermercado.mapper;

import java.util.List;

public interface BaseMapper<Entity, Dto>{
	
	Dto entityToDto(Entity source);
	Entity dtoToEntity(Dto source);
	List<Dto> listEntityToDto(List<Entity> source);

}
