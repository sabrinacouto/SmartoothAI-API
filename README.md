# 🦷 Smartooth AI 🦷

![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

## ✨ Sobre

O Smartooth AI é um sistema desenvolvido em Java com o framework Spring Boot, focado na otimização de serviços odontológicos utilizando inteligência artificial e machine learning. O sistema visa oferecer uma experiência personalizada aos usuários, facilitando a escolha de procedimentos odontológicos e destacando planos de saúde disponíveis. Além disso, conta com um programa de pontos que incentiva a fidelização dos pacientes.

Neste repositório encontra-se a API responsável pelo gerenciamento de profissionais, pacientes, planos, e demais funcionalidades voltadas para a área odontológica.


## 🪐 Developers 

- [Sabrina Couto](https://github.com/sabrinacouto) - Backend Developer - RM552728 - 2TDSZ
- [Juliana Mo.](https://github.com/julianamo93) - Modelagem de Dados e Cloud - RM554113 -2TDSPR
- [Kevin Nobre](https://github.com/KevinNobre) - Backend e Front - RM552590 - 2TDSZ
  

## Modelo DER
![Captura de tela 2025-03-20 011231](https://github.com/user-attachments/assets/36285977-ae30-4380-9130-78dac38aca5b)


## Diagrama de Classes

![Captura de tela 2025-03-19 231027](https://github.com/user-attachments/assets/3a23b5c3-2b11-4f0e-ae2b-79eaebf75e56)

## Arquitetura da Solução

[Smartooth-Archi.pdf](https://github.com/user-attachments/files/19358853/Smartooth-Archi.pdf)


## 💬 Rodando localmente

### Clone o projeto

```bash
  git clone https://github.com/sabrinacouto/SmartoothAI-API.git
```

### Configurar o Projeto no IntelliJ IDEA
<ul>
  <li>Selecione a opção "Open" e navegue até o diretório do projeto Smartooth AI clonado.</li>
  <li>Em Project Structure garanta que o SDK esteja para o Java 17.</li>
  <li>Verifique no IntelliJ se a aba do Gradle está presente na barra lateral.</li>
  <li>Caso o projeto não seja automaticamente reconhecido como Gradle, abra o arquivo build.gradle e aceite a importação do Gradle quando o IntelliJ perguntar.</li>
</ul>

### Configurar o JDBC para o Oracle Database
Configurar as credenciais do banco de dados Oracle no application.properties:
  ```bash
  spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```


### Rodando a Aplicação

Vá até a classe principal que possui a anotação @SpringBootApplication e clique no botão Run ou Debug no IntelliJ.

Acesse a rota para visualizar no navegador:

```endpoint aplicação
   http://localhost:8080/
```

### Rodando a Mensageria (RabbitMQ)
🔧 Pré-requisitos
Certifique-se de que o RabbitMQ esteja instalado e rodando localmente na porta 5672. Caso não esteja, você pode rodá-lo rapidamente via Docker:

```bash
docker run -d --hostname rabbitmq-host --name rabbitmq \
-p 5672:5672 -p 15672:15672 \
-e RABBITMQ_DEFAULT_USER=guest \
-e RABBITMQ_DEFAULT_PASS=guest \
rabbitmq:3-management
```
A interface de gerenciamento estará disponível em:
```endpoint rabbitmq
   http://localhost:15672/
```

### Rodando o Spring Boot Actuator
O Spring Boot Actuator expõe endpoints para monitoramento da aplicação. No projeto Smartooth AI, o Actuator está disponível na porta 8081, separada da aplicação principal.

### 🔁 Configuração no application.properties

```bash
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

smartooth.broker.queue.usuarioPaciente=usuarioPacienteQueue
smartooth.broker.exchange.usuarioPaciente=usuarioPacienteExchange
smartooth.broker.routingkey.usuarioPaciente.created=usuarioPaciente.created.routingkey

management.server.port=8081
management.endpoints.web.exposure.include=health,info,metrics,httptrace,loggers
management.endpoint.health.show-details=always
management.endpoint.httptrace.enabled=true
```

## 📌 Endpoints para o UsuarioPaciente

| Método  | Endpoint                           | Descrição |
|---------|------------------------------------|-----------|
| `POST`  | `/usuario/cadastro`               | Processa o cadastro de um novo usuário. |
| `GET`   | `/usuario/editarUsuario/{id}`     | Exibe os dados do formulário de edição do usuário. |
| `PATCH` | `/usuario/editarUsuario/{id}`     | Atualiza os dados do usuário. |
| `DELETE`| `/usuario/deletarUsuario/{id}`    | Exclui um usuário do sistema. |
| `GET`   | `/usuario/logout`                 | Encerra a sessão do usuário logado. |


## 📌 Endpoints para o Plano


| Método  | Endpoint                | Descrição |
|---------|-------------------------|-----------|
| `POST`  | `/planos/cadastro`      | Processa o cadastro de um novo plano. |
| `GET`   | `/planos/{id}/editar`   | Exibe o formulário de edição do plano. |
| `PUT`   | `/planos/{id}/editar`   | Atualiza os dados de um plano. |
| `DELETE`| `/planos/{id}/excluir`  | Exclui um plano do sistema. |


## 👩‍💻 Exemplo de teste
 
![CadastroLoginJava](https://github.com/user-attachments/assets/3a49ad66-68d5-4904-8a0c-b4ef54c418b1)


## 📍 Dependências
```gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-hateoas'
	compileOnly 'org.projectlombok:lombok'
	implementation 'org.springframework.boot:spring-boot-starter-amqp'
	implementation"org.springframework.boot:spring-boot-starter-actuator"
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'org.springframework.boot:spring-boot-starter-aop'
	implementation 'org.springframework.security:spring-security-crypto'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	runtimeOnly 'com.oracle.database.jdbc:ojdbc11'
	annotationProcessor 'org.projectlombok:lombok'
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
}
```


  
