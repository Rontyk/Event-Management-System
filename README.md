# Система Управления Мероприятиями

## Установка и запуск

1. ### Запуск Docker
   Запустите `docker-compose.yml` для подготовки окружения.

2. ### Создание таблиц, если они отсутствуют:
   ```sql
   CREATE TABLE Users (
       user_id SERIAL PRIMARY KEY,
       username VARCHAR(255) UNIQUE NOT NULL,
       password_hash VARCHAR(255) NOT NULL,
       email VARCHAR(255) UNIQUE NOT NULL
   );
   CREATE TABLE Events (
       event_id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       event_date TIMESTAMP NOT NULL,
       location VARCHAR(255) NOT NULL,
       available_seats INTEGER NOT NULL
   );
   CREATE TABLE Registrations (
       registration_id SERIAL PRIMARY KEY,
       user_id INTEGER NOT NULL,
       event_id INTEGER NOT NULL,
       seats INTEGER NOT NULL,
       FOREIGN KEY (user_id) REFERENCES Users(user_id),
       FOREIGN KEY (event_id) REFERENCES Events(event_id)
   );

  3. ### (Если вы сделали 2 пункт) Начальное заполнение таблицы Events:
   ```sql
  INSERT INTO Events (title, description, event_date, location, available_seats) VALUES
  ('Concert A', 'A great music concert', '2023-12-15 19:00:00', 'Concert Hall A', 150),
  ('Theatre B', 'An engaging play', '2023-12-20 18:00:00', 'Theatre Venue B', 75),
  ('Concert C', 'A rock music festival', '2023-12-25 20:00:00', 'Outdoor Venue C', 200);
  ```
  4. ### Запуск приложения:
    Перейдите по ссылке http://localhost:8080/concert/ после запуска приложения.
  5. ### Регистрация пользователя:
    Пройдите процесс регистрации для доступа к полному функционалу сайта.

### Архитектура и технологии

- **Spring Security**: Обеспечение безопасности, включая аутентификацию и авторизацию.
- **Thymeleaf**: Шаблонизатор для создания динамических веб-страниц.
- **Spring Web**: Разработка веб-приложений.
- **PostgreSQL**: Система управления базами данных для хранения всех данных.
- **Docker**: Используется для создания, развертывания и управления базой данных в изолированной среде.

### Компоненты проекта

- **Entity**: Классы, представляющие сущности базы данных.
- **Repositories**: Компоненты для взаимодействия с базой данных.
- **DTO (Data Transfer Objects)**: Используются для передачи данных между клиентом и сервером.
- **Controllers**: Обработка входящих HTTP запросов.
- **Mappers**: Преобразование данных между Entity и DTO.
- **Security**: Конфигурация безопасности.
- **Services**: Бизнес-логика приложения.
- **Templates**: Шаблоны Thymeleaf для веб-страниц.

### Основные возможности
  Уровень 1: Просмотр списка мероприятий на главной странице.
  Уровень 2: Просмотр деталей мероприятий и регистрация на них.
  Уровень 3: Управление учетной записью пользователя и возможность просмотра своих регистраций.

### Проблемы и их решения
  Исправлена ошибка, позволяющая пользователям изменять чужие регистрации.
  Внимание на потенциальные проблемы с сохранением состояния базы данных в Docker.

*Спасибо за уделенное время, надеюсь присоединиться к вашему коллективу этим летом*

  



