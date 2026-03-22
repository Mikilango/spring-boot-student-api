# Шаг 1 — Берём образ Java
FROM eclipse-temurin:17-jdk-alpine

# Шаг 2 — Рабочая папка внутри контейнера
WORKDIR /app

# Шаг 3 — Копируем jar файл
COPY target/*.jar app.jar

# Шаг 4 — Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]