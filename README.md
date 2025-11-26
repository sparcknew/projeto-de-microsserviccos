# Sistema de Agendamento de Serviços

**3 microsserviços em Java 17 + Spring Boot 3.3.4**  
**Docker + CI + REST + Validação**

| Serviço | Porta | Função |
|--------|-------|--------|
| `catalogo-service` | 8081 | CRUD de serviços |
| `usuario-service` | 8082 | Cadastro/login |
| `agendamento-service` | 8083 | Cria/lista agendamentos |

---

## Rodar com Docker

```bash
# catálogo
cd catalogo-service && mvn package && docker build -t catalogo . && docker run -d -p 8081:8080 catalogo



Claro! Aqui está **exatamente o conteúdo pronto** para você copiar e colar direto no **README.md** — sem nada extra, só o markdown final.

---

# README.md (copie a partir daqui)

```markdown
# Agendamento Service

Microserviço responsável pelo gerenciamento de agendamentos.  
Construído com **Spring Boot 3**, **Java 17** e **Maven**.

---

## 📦 Requisitos

- **Java 17+**
- **Maven 3.9+** (opcional se usar o Maven embutido do IntelliJ)
- **Docker** (opcional)
- **IntelliJ IDEA** (opcional)

---

# Como executar o projeto

---

## Executar no IntelliJ **com Maven**

O IntelliJ reconhece o Maven automaticamente ao abrir o projeto.

### ✔️ Passos:

1. Abra o IntelliJ → **Open** → selecione a pasta do projeto  
2. Aguarde o IntelliJ importar as dependências do `pom.xml`  
3. No painel **Maven** à direita, execute:
```

Lifecycle → clean
Lifecycle → install

```
4. Abra:
```

src/main/java/com/meuprojeto/agendamento/AgendamentoServiceApplication.java

```
5. Clique no botão ▶️ **Run**

### 📌 O serviço ficará disponível em:

```

[http://localhost:8080](http://localhost:8080)

````

---

##  Executar no IntelliJ **sem Maven instalado no sistema**

O IntelliJ usa um Maven embutido por padrão.

### Passos:

1. Abra o projeto no IntelliJ  
2. Verifique:  
   **File → Settings → Build Tools → Maven → Maven home directory**  
   → Deve estar como *"Bundled (Maven x.x.x)"*  
3. Execute a aplicação normalmente com o botão ▶️

---

##  Executar com Maven pelo terminal

###  Compilar:

```bash
mvn clean install
````

###  Rodar:

```bash
mvn spring-boot:run
```

---

## 4️ Executar **sem Maven** (usando o JAR)

Primeiro gere o JAR:

```bash
mvn clean package
```

O arquivo será gerado em:

```
target/agendamento-service-1.0.0.jar
```

###  Rodar o JAR:

```bash
java -jar target/agendamento-service-1.0.0.jar
```

---

# Executar com Docker

---

## 5.1 Criar a imagem Docker

Crie um arquivo **Dockerfile** na raiz do projeto:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/agendamento-service-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

### Build da imagem:

```bash
docker build -t agendamento-service .
```

---

## 5.2 Executar o container

```bash
docker run -p 8080:8080 --name agendamento agendamento-service
```

---

# Executar com Docker Compose

Crie um arquivo **docker-compose.yml**:

```yaml
version: '3.8'

services:
  agendamento:
    build: .
    container_name: agendamento-service
    ports:
      - "8080:8080"
```

### Rodar:

```bash
docker compose up --build
```

---

# Testando os endpoints

## Criar um agendamento (POST)

```bash
curl -X POST "http://localhost:8080/agendamentos" \
  -H "Content-Type: application/json" \
  -d '{
    "clienteId": "11111111-1111-1111-1111-111111111111",
    "servicoId": "22222222-2222-2222-2222-222222222222",
    "dataHora": "2025-12-10T14:30:00"
  }'
```

---

## Listar agendamentos por cliente

```bash
curl http://localhost:8080/agendamentos/cliente/11111111-1111-1111-1111-111111111111
```

---

# Estrutura do projeto

```
src/
 ├── main/
 │   ├── java/com/meuprojeto/agendamento
 │   │   ├── api/AgendamentoController.java
 │   │   ├── domain/
 │   │   ├── infra/
 │   │   └── AgendamentoServiceApplication.java
 │   └── resources/application.properties
 └── test/
```

---

# Pronto!

O serviço está pronto para ser executado localmente, em Docker ou via IntelliJ.

```



# usuário
cd ../usuario-service && mvn package && docker build -t usuario . && docker run -d -p 8082:8080 usuario

# agendamento
cd ../agendamento-service && mvn package && docker build -t agendamento . && docker run -d -p 8083:8080 -e CATALOGO_URL=http://host.docker.internal:8081 agendamento
