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

# usuário
cd ../usuario-service && mvn package && docker build -t usuario . && docker run -d -p 8082:8080 usuario

# agendamento
cd ../agendamento-service && mvn package && docker build -t agendamento . && docker run -d -p 8083:8080 -e CATALOGO_URL=http://host.docker.internal:8081 agendamento
