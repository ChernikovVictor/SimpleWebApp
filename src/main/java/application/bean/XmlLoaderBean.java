package application.bean;

import application.dao.XmlPathDAO;
import application.exception.InsertionFailedException;
import application.model.Route;
import application.model.XmlPath;
import org.xml.sax.SAXException;

import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/* Бин, экспортирующий маршруты в xml файлы, и наоборот */
@Stateless
public class XmlLoaderBean {

    private XmlPathDAO xmlPathDAO = new XmlPathDAO();

    /* Загрузить список из файла xml */
    public Optional<List<Route>> loadFromXml(String filepath) {

        File file = new File(filepath);
        if (!isValid(file)) {
            return Optional.empty();
        }

        try {
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

    /* Проверить xml-файл на соответствие схеме xsd*/
    public boolean isValid(File xmlFile) {
        try {
            // Поиск и создание экземпляра фабрики для языка XML Schema
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Компиляция схемы
            URL schemaLocation = new URL("http:\\\\localhost:8080\\xsd\\RouteListSchema.xsd");
            Schema schema = schemaFactory.newSchema(schemaLocation);

            // Создание валидатора для схемы
            Validator validator = schema.newValidator();

            // Разбор проверяемого документа
            Source source = new StreamSource(xmlFile);

            // Валидация документа
            try {
                validator.validate(source);
                return true;
            } catch (SAXException e) {
                return false;
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
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
