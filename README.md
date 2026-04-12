# Student REST API

REST API для управления студентами, разработанный на Spring Boot.

## Технологии

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Docker**
- **Maven**

## Функциональность

- Регистрация и авторизация через JWT токен
- Полный CRUD для студентов
- DTO паттерн с MapStruct
- Валидация входящих данных
- Пагинация и сортировка
- Глобальная обработка ошибок
- Unit тесты (JUnit 5 + Mockito)
- Docker конфигурация

## Структура проекта

```
src/main/java/
├── controller/     — REST контроллеры
├── service/        — бизнес-логика
├── repository/     — JPA репозитории
├── entity/         — JPA сущности
├── dto/            — объекты передачи данных
├── mapper/         — MapStruct маппинг
├── security/       — JWT фильтр, утилиты
└── exception/      — обработка ошибок
```

## Endpoints

| Метод | URL | Описание |
|-------|-----|----------|
| POST | `/auth/register` | Регистрация |
| POST | `/auth/login` | Вход |
| GET | `/api/students` | Все студенты |
| GET | `/api/students/{id}` | Студент по ID |
| POST | `/api/students` | Создать студента |
| PUT | `/api/students/{id}` | Обновить студента |
| DELETE | `/api/students/{id}` | Удалить студента |

## Запуск проекта

### Без Docker

#### Требования
- Java 17+
- PostgreSQL

#### Настройка `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_secret_key_minimum_32_characters
jwt.expiration=86400000
```

#### Запуск

```bash
mvn spring-boot:run
```

### С Docker

```bash
docker-compose up
```

## Примеры запросов

### Регистрация

```json
POST /auth/register
{
  "email": "user@example.com",
  "password": "Password123"
}
```

### Создание студента

```json
POST /api/students
Authorization: Bearer <token>

{
  "firstName": "Miki",
  "lastName": "Daribaev",
  "email": "miki@example.com",
  "age": 21
}
```

## Автор

**Mirlanbek** — [GitHub](https://github.com/Mikilango)
