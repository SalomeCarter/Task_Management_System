# Название проекта: Task Management System

Система обеспечивает создание, редактирование, удаление и просмотр задач. Каждая задача содержbn заголовок, 
описание, статус и приоритет, а также автора задачи и исполнителя. 

## Требования

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (версия 17)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/) (если используется)

## Локальный запуск

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/SalomeCarter/Task_Management_System.git
   
2. Перейдите в директорию проекта:

cd Task_Management_System

3. Запустите контейнеры Docker:

   docker-compose up -d

4. Соберите и запустите приложение:

./mvnw clean install
./mvnw spring-boot:run

5. Ваше приложение должно быть доступно по адресу http://localhost:8080.

   Завершение работы
1. Остановите контейнеры Docker:

docker-compose down

2. Завершите работу приложения.





