package application.dto.transport;

import application.entity.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TransportMapper {

    TransportMapper INSTANCE = Mappers.getMapper(TransportMapper.class);

    TransportDTO transportToTransportDto(Transport transport);

    Transport transportDtoToTransport(TransportDTO transportDTO);

    default List<TransportDTO> transportsToTransportDTOs(List<Transport> transports) {
        return transports.stream()
                .map(this::transportToTransportDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
