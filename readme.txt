# Проекты из книги "Spring Boot 2: лучшие практики для профессионалов"

### create todo
curl -i -X POST http://localhost:8080/api/todo -H "Content-Type: application/json" -d "{\"description\":\"my descrpition\"}"

### edit todo
curl -i -X PUT http://localhost:8080/api/todo -H "Content-Type: application/json" -d "{\"description\":\"Take the dog and the cat for a walk\", \"id\":\"f4f26f1e-67dd-49d0-9f2b-8bac5be990a1\"}" 

### mark todo as done
curl -i -X PATCH http://localhost:8080/api/todo/f4f26f1e-67dd-49d0-9f2b-8bac5be990a1

### delete todo
curl -i -X DELETE http://localhost:8080/api/todo/f4f26f1e-67dd-49d0-9f2b-8bac5be990a1

# Полезные ссылки:
Spring Data JDBC (новая фича 2018 года, Spring Data без JPA) https://habr.com/ru/post/423697/

# Если включен Spring Security
$ curl localhost:8080/api/toDos -u user:4af35dba-0645-4c30-b1d2-740e6225d118

