package application.bean;

import application.dao.XmlPathDAO;
import application.exception.InsertionFailedException;
import application.model.Route;
import application.model.XmlPath;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/* Бин, экспортирующий маршруты в xml файлы, и наоборот */
@Stateless
public class XmlLoaderBean {

    private XmlPathDAO xmlPathDAO = new XmlPathDAO();

    /* Загрузить список из файла xml */
    public Optional<List<Route>> loadFromXml(String filepath) {
        try {
            File file = new File(filepath);
            JAXBContext jaxbContext = JAXBContext.newInstance(RouteListXmlDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RouteListXmlDTO dto = (RouteListXmlDTO) unmarshaller.unmarshal(file);
            return Optional.ofNullable(dto.getRoutes());
        } catch (JAXBException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /* Сохранить список в файл xml */
    public void saveAsXml(List<Route> routes) {

        String filepath = createFilepath();
        boolean successfully = saveRoutes(routes, filepath);

        if (!successfully) {
            return;
        }

        /* Добавить информацию о файле в БД */
        try {
            xmlPathDAO.add(XmlPath.builder().path(filepath).build());
        } catch (InsertionFailedException e) {
            e.printStackTrace();
        }

    }

    /** Сохрнатиь список в файл filepath
     *   @return true, если сохранение прошло успешно */
    private boolean saveRoutes(List<Route> routes, String filepath) {
        try {
            RouteListXmlDTO dto = new RouteListXmlDTO(routes);
            JAXBContext context = JAXBContext.newInstance(RouteListXmlDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(dto, new File(filepath));
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /* Получить путь для файла */
    private String createFilepath() {
        String currentDateAndTime = LocalDateTime.now().toString().replace(":", "-");
        return String.format("C:\\XML_STORAGE\\%s.xml", currentDateAndTime);
    }
}
