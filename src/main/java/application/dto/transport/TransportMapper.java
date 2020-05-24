package application.dto.transport;

import application.entity.Transport;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TransportMapper {

    public TransportDTO transportToTransportDto(Transport transport) {
        return TransportDTO.builder()
                .id(transport.getId())
                .name(transport.getName())
                .kind(transport.getKind())
                .capacity(transport.getCapacity())
                .routes(transport.getRoutes())
                .build();
    }

    public Transport transportDtoToTransport(TransportDTO transportDTO) {
        return Transport.builder()
                .id(transportDTO.getId())
                .kind(transportDTO.getKind())
                .name(transportDTO.getName())
                .capacity(transportDTO.getCapacity())
                .routes(transportDTO.getRoutes())
                .build();
    }

    public List<TransportDTO> transportsToTransportDTOs(List<Transport> transports) {
        return transports.stream()
                .map(this::transportToTransportDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
