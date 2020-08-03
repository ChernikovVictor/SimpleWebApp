package application.service;

import application.dao.transport.TransportDAO;
import application.dto.transport.TransportDTO;
import application.dto.transport.TransportMapper;
import application.entity.Transport;
import application.exception.NoSuchElementException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class TransportService {

    @Inject
    private TransportDAO transportDAO;

    public TransportDTO findById(Long id) throws NoSuchElementException {
        Transport transport = transportDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return TransportMapper.INSTANCE.transportToTransportDto(transport);
    }

    public List<TransportDTO> findAll() {
        return TransportMapper.INSTANCE.transportsToTransportDTOs(transportDAO.findAll());
    }
}
