# 📄 JSON DTO Facility API

Uma API desenvolvida em Spring Boot que converte JSONs dinâmicos em **DTOs Java (POJOs)** ou **Records**, com suporte a **JSON aninhado (nested)** e documentação via **Swagger/OpenAPI**.

---

## ✅ Funcionalidades

- ✅ Geração de **DTO padrão (POJO + Lombok-style)**
- ✅ Geração de **Java Record**
- ✅ Suporte a **JSON aninhado (nested)**
- ✅ Validação automática com **Bean Validation**
- ✅ **Swagger/OpenAPI UI** para teste e documentação interativa
- ✅ Tratamento global de erros com **ControllerAdvice**

---

## 🚀 Como executar localmente

### Instruções de Uso:

- Enviar um JSON via **POST** para o endpoint `/api/dto` para gerar um DTO ou Record.
exemplo de JSON:

```json
{
  "json": {
    "name": "Yuri",
    "endereco": {
      "rua": "Av Paulista",
      "numero": 123
    },
    "ativo": true
  },
  "className" : "UsuarioDTO"
}
```

### Requisitos:

- Java 17 ou superior (Recomendado: Java 21)
- Gradle
- (Opcional) Postman ou Insomnia para testar

### Passo a passo:

```bash
# Na raiz do projeto
./gradlew clean build
./gradlew bootRun
