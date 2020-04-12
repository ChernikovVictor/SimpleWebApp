<h1>SimpleWebApp</h1>
<h4>Что это такое?</h4>
Учебное web-приложение для УНЦ NetCracker

<h4>Изучаемые технологии:</h4>
JDBC, Servlets, JSP

<h4>Как заставить работать?</h4>

+ Создать базу данных и таблицы в ней (sql-скрипты для
создания таблиц в директории: _/sql_)
+ Установить пользователя и пароль от БД в файле: _src/main/resources/datasorce.properties_
+ Установить имя БД и драйвер для доступа к ней в файле: _src/main/resources/database.properties_ 
(выбранный драйвер также указать в зависимостях pom.xml)
+ Выполнить: `mvn tomcat7:run`
+ Начальная страница приложения: _localhost:8888/_
+ Прекратить работу: `Ctrl + C`
