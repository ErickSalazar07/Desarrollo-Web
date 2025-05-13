package puj.veterinaria.DTOs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import puj.veterinaria.DTOs.ClienteDTO;
import puj.veterinaria.entidades.Cliente;

@Mapper
public interface ClienteMapper {
  ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

// Mapping
  ClienteDTO convert(Cliente cliente);
}
