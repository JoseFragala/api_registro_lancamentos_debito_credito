# 💰 API REST - Controle de Lançamentos Financeiros

## 📌 Sobre o projeto

Este projeto consiste no desenvolvimento de uma API REST utilizando **Spring Boot**, com foco em boas práticas de backend, modelagem de domínio e construção de serviços RESTful.

O sistema permite o gerenciamento de **lançamentos financeiros** (receitas e despesas), associados a **categorias** e **pessoas**.

Este projeto foi desenvolvido com base no curso da AlgaWorks, com foco exclusivo no **backend**, visando consolidar conceitos fundamentais de APIs modernas.

---

## 🎯 Objetivo

* Consolidar conhecimentos em **Spring Boot**
* Entender a construção de uma **API REST profissional**
* Trabalhar com **JPA / Hibernate / Flyway **
* Aplicar boas práticas de arquitetura
* Evoluir no desenvolvimento backend

---

## 🧱 Arquitetura do projeto

A aplicação segue uma estrutura baseada em camadas:

```
Controller → Service → Repository → Banco de Dados
```

### 🔹 Controller

Responsável por expor os endpoints da API.

### 🔹 Service

Contém as regras de negócio.

### 🔹 Repository

Responsável pela comunicação com o banco via Spring Data JPA.

### 🔹 Entity

Representa as tabelas do banco de dados.

---

## 🗂️ Domínio da aplicação

### 📌 Entidades principais

#### 🟢 Categoria

* codigo (Long)
* nome (String)

#### 🟢 Pessoa

* codigo (Long)
* nome (String)
* ativo (Boolean)
* endereço:

  * logradouro
  * numero
  * complemento
  * bairro
  * cep
  * cidade
  * estado

#### 🟢 Lançamento

* codigo (Long)
* descricao (String)
* dataVencimento (LocalDate)
* dataPagamento (LocalDate)
* valor (BigDecimal)
* observacao (String)
* tipo (RECEITA ou DESPESA)
* categoria (ManyToOne)
* pessoa (ManyToOne)

---

## 🔗 Relacionamentos

* Um **Lançamento** pertence a:

  * uma **Categoria**
  * uma **Pessoa**

---

## ⚙️ Tecnologias utilizadas

* Java 17
* Spring Boot 2.5.x
* Spring Web
* Spring Data JPA
* Hibernate
* Flyway
* Bean Validation
* Maven
* Postman


---

## 🔥 Funcionalidades implementadas

* ✔️ Cadastro de categorias
* ✔️ Cadastro de pessoas
* ✔️ Cadastro de lançamentos
* ✔️ Validação de dados (Bean Validation)
* ✔️ Relacionamentos entre entidades
* ✔️ Tratamento de exceções
* ✔️ Paginação de resultados
* ✔️ Filtros e buscas

---

## 📡 Endpoints principais

### 📌 Lançamentos

* `GET /lancamentos` → Lista todos
* `GET /lancamentos/{id}` → Busca por ID
* `POST /lancamentos` → Cria novo
* `DELETE /lancamentos/{id}` → Remove

---

## 🧠 Conceitos aplicados

* REST (recursos, verbos HTTP, status codes)
* DTO e validação
* Separação de responsabilidades
* Eventos no Spring (ApplicationEvent)
* Tratamento de Optional
* Padrão Repository

---

## ⚠️ Observações

* O projeto foi desenvolvido focando apenas no backend
* O frontend (Angular) não faz parte deste repositório
* Futuramente será integrado com a interface fornecida no curso

---

## 🚀 Próximos passos

* Implementar segurança (Spring Security / OAuth2)
* Melhorar tratamento de erros
* Adicionar testes automatizados
* Integrar com frontend Angular

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto foi possível compreender:

* Como estruturar uma API REST do zero
* Como o Spring gerencia dependências e injeção
* Como o JPA abstrai o acesso ao banco
* Diferença entre versões do Spring e impacto no código


