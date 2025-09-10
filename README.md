# ☕ SpringEspresso

---

Este projeto foi, originalmente desenvolvido no contexto da disciplina de Desenvolvimento Web 1 - 1o. semestre de 2025 - no DC/UFSCar, por [ronanpjr](https://github.com/ronanpjr), [Fekenji](https://github.com/Fekenji), [GriseldaJusto](https://github.com/GriseldaJusto), [jooj-arthur](https://github.com/jooj-arthur) e [RenanZago](https://github.com/RenanZago).

[![Java CI with Maven](https://github.com/andreendo/SpringEspresso/actions/workflows/maven.yml/badge.svg)](https://github.com/andreendo/espresso-testing/actions/workflows/maven.yml)

## 🚀 Tecnologias utilizadas
**Back-end:**

- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf

**Front-end:**
- Javascript
- HTML
- CSS

**Banco de Dados:**

- MySQL 

---

## ✅ Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- Java JDK 11 ou superior
- Apache Maven
- MySQL Server
- IDE de sua preferência (Eclipse, IntelliJ ou VS Code)

---

## 1️⃣ Clonar o repositório

1. Abra o terminal e clone o projeto


**Execute esse comando:**  
**git clone https://github.com/ronanpjr/SpringEspresso.git**


2. Acesse a pasta do projeto


**Execute esse comando:**  
**cd SpringEspresso**  

---

## 2️⃣ Configuração do banco de dados
É necessário uma instância do SGBD MySQL executando com um banco de dados chamado `EspressoTestingJbaDB`. Veja a pasta [/docker](/docker) para iniciar uma instância usando o Docker. 

O banco de dados será criado automaticamente na primeira execução.

---

## 3️⃣ Build do projeto com Maven
No terminal, na raiz do projeto execute esse comando:  

```bash
	./mvnw clean install
```


---

## 4️⃣ Rodar o projeto
Agora, para iniciar a aplicação  


**Execute esse comando:**  

```bash
	./mvnw spring-boot:run
```


A aplicação Spring Boot iniciará na porta padrão 8080.  
Acesse no navegador:  
[http://localhost:8080](http://localhost:8080)

---

## 5️⃣ Executar os testes automatizados
No terminal, na raiz do projeto execute esse comando para os **testes de unidade**:  

```bash
	./mvnw test
```

Para os **testes de integração e E2E**, execute o comando:

```bash
	./mvnw verify
```