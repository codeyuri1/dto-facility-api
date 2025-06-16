
# 🚀 DTO Facility API - JSON to DTO/Record Generator

Uma ferramenta web **Spring Boot + Thymeleaf + Bootstrap** para gerar **DTOs Java (POJOs com Lombok)** ou **Java Records** automaticamente a partir de um JSON de entrada.

---

## ✅ Funcionalidades

- 🎯 Interface Web interativa com **Thymeleaf + Bootstrap 5**
- 🎯 Geração de **DTO padrão (POJO com Lombok-style)**
- 🎯 Geração de **Java Records**
- 🎯 Suporte a **JSON aninhado (nested objects)**
- 🎯 Tratamento de erros com **ControllerAdvice**
- 🎯 Totalmente Dockerizável

---

## 🌐 Acesso Web

Após iniciar o projeto:

👉 Acesse no navegador:

```
http://localhost:8080/thymeleaf
```

Você verá uma interface moderna e responsiva para colar seu JSON e gerar o código.

---

## 💻 Como executar localmente

### Requisitos:

- Java 17 ou superior (recomendado Java 21)
- Gradle
- (Opcional) Docker para build containerizado

### Rodando local:

```bash
./gradlew clean build
./gradlew bootRun
```

---

## 🐳 Rodando com Docker:

### Build da imagem:

```bash
docker build -t dto-facility-api .
```

### Executando o container:

```bash
docker run -p 8080:8080 dto-facility-api
```

---

## 📦 Estrutura de Projeto:

```
src
 └── main
     ├── java
     │    └── com.codeyuri.dtofacility
     │          ├── controller       (Controllers REST e Web)
     │          ├── service          (Lógica de geração dos DTOs/Records)
     │          ├── dto              (DTOs)
     │          └── exception        (Handler de exceções personalizadas)
     └── resources
          ├── templates              (Arquivos Thymeleaf HTML)
          └── application.yml
```

---

## 🧪 Testes Unitários

Exemplo de cobertura:

- Testes de geração de DTO
- Testes de geração de Record
- Testes de validação de JSON inválido
- Testes de Exception Handling

```bash
./gradlew test
```

---

## ✅ Como contribuir:

Se quiser contribuir:

1. Crie um fork
2. Crie sua branch: `feature/minha-feature`
3. Faça o commit
4. Abra um pull request

**📌 Obs:** Para sugestões de features, crie uma Issue.

---

## ✨ Próximas melhorias (To Do):

- Exportação automática para `.java`
- Download via botão
- Validação de nome de classe
- Customização de pacote

---

Feito por [@codeyuri1](https://github.com/codeyuri1)
