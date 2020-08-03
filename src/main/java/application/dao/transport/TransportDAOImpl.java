package application.dao.transport;

import application.dao.GenericDAOImpl;
import application.entity.Transport;

public class TransportDAOImpl extends GenericDAOImpl<Transport, Long> implements TransportDAO {

    public TransportDAOImpl() {
        super(Transport.class);
    }
}
