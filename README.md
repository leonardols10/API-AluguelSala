# API de Aluguel de Sala

Bem-vindo à API de Aluguel de Sala, uma aplicação desenvolvida em Kotlin com Spring Boot. Esta API oferece funcionalidades completas de CRUD, sistema de logs, autenticação, validações e segurança para gerenciar o aluguel de salas.

## Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Banco de Dados Mysql
- [SpringDoc](https://springdoc.org/): Geração automática de documentação OpenAPI.
- [JWT (JSON Web Token)](https://github.com/jwtk/jjwt): Implementação para autenticação baseada em token.
- [MockK](https://mockk.io/): Framework para mock em Kotlin.
- [Logback](http://logback.qos.ch/): Framework de logging para Java.
- [Flyway](https://flywaydb.org/): Controle de versão para migrações de banco de dados.
- [MySQL Connector](https://dev.mysql.com/downloads/connector/j/): Conector JDBC para MySQL.
- [JUnit 5](https://junit.org/junit5/): Framework de teste para Java/Kotlin.

## Funcionalidades

1. **CRUD Completo:**
   - Crie, leia, atualize e exclua salas de maneira eficiente.

2. **Sistema de Logs:**
   - Utiliza o Logback para registrar eventos importantes e mensagens de log para facilitar a depuração.

3. **Autenticação e Segurança:**
   - Implementa um sistema robusto de autenticação baseado em tokens JWT.
   - Utiliza Spring Security para controle de acesso e segurança.
   - Aplica medidas de segurança, incluindo validações e controle de versão no banco de dados com o Flyway.

4. **Documentação da API:**
   - Acesse a documentação da API em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html), gerada automaticamente com o SpringDoc.

## Configuração

1. **Requisitos:**
   - Java 11 ou superior.
   - Banco de dados configurado (se necessário).

2. **Configuração do Projeto:**
   - Clone o repositório.
   - Configure as propriedades do banco de dados em `application.properties`.

3. **Execução:**
   - Execute o aplicativo usando sua IDE ou com o comando `./mvnw spring-boot:run`.

4. **Contribuição:**
   - Sinta-se à vontade para contribuir para o desenvolvimento desta API. Abra problemas (issues) ou envie pull requests com melhorias e correções.

5. **Licença:**
   - Este projeto está licenciado sob a [Licença MIT](LICENSE.md) - veja o arquivo LICENSE.md para detalhes.

---

**Leonardo Lima  - leonardosilvalima40@gmail.com**
