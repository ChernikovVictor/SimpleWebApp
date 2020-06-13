package application.service;

import application.dao.TransportDAO;
import application.dto.transport.TransportDTO;
import application.dto.transport.TransportMapper;
import application.entity.Transport;
import application.exception.NoSuchElementException;

import java.util.List;

public class TransportService {

    private final TransportDAO transportDAO = new TransportDAO();

    public TransportDTO findById(Long id) throws NoSuchElementException {
        Transport transport = transportDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return TransportMapper.INSTANCE.transportToTransportDto(transport);
    }

    public List<TransportDTO> findAll() {
        return TransportMapper.INSTANCE.transportsToTransportDTOs(transportDAO.findAll());
    }
}
