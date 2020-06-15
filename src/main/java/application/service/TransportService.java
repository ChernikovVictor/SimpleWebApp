package application.service;

import application.dao.TransportDAO;
import application.dto.transport.TransportDTO;
import application.dto.transport.TransportMapper;
import application.entity.Transport;
import application.exception.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TransportService {

    @EJB
    private TransportDAO transportDAO;

    public TransportDTO findById(Long id) throws NoSuchElementException {
        Transport transport = transportDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return TransportMapper.INSTANCE.transportToTransportDto(transport);
    }

    public List<TransportDTO> findAll() {
        return TransportMapper.INSTANCE.transportsToTransportDTOs(transportDAO.findAll());
    }
}
