package cl.challenge.tenpo.dtos.mapper;

import cl.challenge.tenpo.dtos.RegistrationOutDTO;
import cl.challenge.tenpo.entities.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    List<RegistrationOutDTO> mapToRegistrationOutDTOList(List<RegistrationRequest> registrationRequestList);
}
