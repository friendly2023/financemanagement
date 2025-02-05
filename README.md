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
