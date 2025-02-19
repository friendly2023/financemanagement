# CRM-система для управления товарами, заказами и клиентами

## Описание проекта

Это CRM-система, предназначенная для управления товарами, заказами и клиентами. Система позволяет:

- Хранить информацию о товарах, заказах и клиентах.
- Просматривать, сортировать и фильтровать данные.
- Получать статистику по товарам и заказам.

Проект реализован с использованием **Java**, **Spring Framework**, **PostgreSQL** и **Thymeleaf** для отображения веб-страниц.

## Функциональные возможности

- **Управление товарами**: Добавление, редактирование и удаление товаров.
- **Управление заказами**: Создание заказов, привязка товаров и клиентов.
- **Управление клиентами**: Добавление и редактирование информации о клиентах.
- **Просмотр и сортировка**: Возможность сортировки и фильтрации данных по разным параметрам.
- **Статистика**: Отображение статистики по товарам и заказам (например, количество проданных товаров, общая сумма и доля от общей выручки).

## Установка и запуск

### Требования:

- Java 17 или выше
- Spring Boot 3.4.2
- PostgreSQL

### Шаги для запуска:

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/yourusername/your-repository.git
   ```
2. Создайте базу данных в PostgreSQL и настройте подключение в application.properties:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```
   Пример создания DB:
   ```bash
   CREATE DATABASE yourdbname;
   CREATE USER yourusername WITH PASSWORD 'yourpassword'; 
   GRANT ALL PRIVILEGES ON DATABASE yourdbname TO yourusername;
   ```
3. Запустите приложение:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Откройте браузер и перейдите по адресу:
   ```bash
   http://localhost:8080
   ```
## Используемые технологии
- Java 17
- Spring Boot
- PostgreSQL
- Thymeleaf для отображения данных на веб-страницах
- Maven для управления зависимостями

## Структура проекта
   ```bash
      src/
      ├── main/
      │   ├── java/
      │   │   ├── com/
      │   │   │   └── yourname/
      │   │   │       ├── controller/
      │   │   │       ├── model/
      │   │   │       ├── repository/
      │   │   │       └── service/
      │   ├── resources/
      │   │   ├── application.properties
      │   │   └── templates/
      │   └── test/
      ├── pom.xml
   ```

## Установка и запуск с использованием Docker

### Шаги для запуска с Docker:

1. Соберите Docker-образ для приложения: Перейдите в корневую директорию проекта и выполните команду для создания Docker-образа:
   ```bash
   docker build -t finance-manager:1.0.0 .
   ```
2. Создайте файл .env в корне с таким содержимым:
   ```bash
   POSTGRES_DB=yourdbname
   POSTGRES_USER=yourusername
   POSTGRES_PASSWORD=yourpassword
   ```
   
3. Запустите контейнеры с помощью Docker Compose: В директории с файлом docker-compose.yml выполните команду для запуска контейнеров:
   ```bash
   docker-compose up -d
   ```
   
4. Остановите контейнеры: Для остановки контейнеров выполните команду:
   ```bash
   docker-compose down
   ```
   
5. Посмотреть логи: Чтобы увидеть логи контейнеров, выполните команду:
   ```bash
   docker-compose logs -f
   ```
   
### Примечания:

- База данных PostgreSQL будет храниться в volume postgres-data, чтобы данные сохранялись между перезапусками контейнеров.
- Приложение будет доступно по адресу http://localhost:8080 после запуска контейнеров.