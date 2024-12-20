# CRUD de Entregas 🚛
## Sobre o projeto
Este projeto é uma API desenvolvida em Spring Boot para gerenciar entregas. Ele implementa operações de CRUD (Create, Read, Update, Delete), incluindo tratamento de exceções e testes unitários. O banco de dados utilizado é um PostgreSQL remoto.

## Funcionalidades
Base URL: ```/api/entregas```
| Método | Endpoint | Descrição                      | Corpo da Requisição  | Resposta Sucesso      |
|--------|----------|--------------------------------|----------------------|-----------------------|
| GET    | /{id}    | Busca um item por ID           | -                    | Objeto do item        |
| POST   | /        | Cria um novo item              | JSON do item         | Objeto criado         |
| PUT    | /{id}    | Atualiza um item existente     | JSON atualizado      | Objeto atualizado     |
| DELETE | /{id}    | Remove um item por ID          | -                    | Mensagem de sucesso   |

## Pré requisitos
Certifique-se de que você possui as ferramentas e configurações a seguir:
1. Java Development Kit (JDK) versão 17 ou superior.
2. Maven (para gerenciamento de dependências).
3. Uma IDE ou editor de código de sua escolha (ex.: IntelliJ, VSCode, Eclipse).
4. Git para clonar o repositório.
5. Opcional: Postman ou Insomnia

## Banco de dados
O banco de dados utilizado para o projeto é remoto. Os seguintes dados de conexão já estão configurados no arquivo application.properties
### Conexão do banco
**IP**: 147.79.107.77     
**Porta**: 5432     
**Username**: dba     
**Password**: Dba12271000  

### Script de criação da tabela 'Entrega'
O script é executado automaticamente ao rodar o projeto.
```
create table entrega (
        id bigint generated by default as identity,
        cpf_cliente varchar(255) not null,
        data_limite_entrega date not null,
        bairro varchar(255) not null,
        cep varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255),
        numero varchar(255) not null,
        rua varchar(255) not null,
        uf varchar(255) not null,
        nome_cliente varchar(255) not null,
        quantidade_pacotes integer not null,
        primary key (id)
    )
```

## Passo a Passo para Executar o Projeto
1. Clone o repositório 
2. Certifique-se de que todas as dependências estão instaladas corretamente.
``` 
mvn clean install
```
3. Execute o projeto
``` 
mvn spring-boot:run
```
4. Interagir com a API: Você pode usar o Postman/Insomnia ou o Swagger para interagir com a API

## Documentação com Swagger
O projeto inclui integração com Swagger para fornecer uma interface interativa de documentação da API.
Após iniciar o servidor, a documentação estará disponível no endpoint:
[Link para o Swagger](http://localhost:8080/docs)

## Testes Unitários 
Os testes unitários foram desenvolvidos com JUnit e Mockito para validar o comportamento da aplicação.
Para executar os testes:
```
mvn test
```

## Tratamento de Exceções
A API implementa tratamentos de exceções utilizando @ControllerAdvice e classes específicas para respostas padronizadas de erro. Exemplos de erros tratados:
- 404 NOT FOUND: Entrega com ID 1 não encontrada.
- 400 BAD REQUEST: O corpo da requisição está vazio ou inválido.
- 500 INTERNAL SERVER ERROR: Um erro de tempo de execução ocorreu.

## Contato 🚀
Responsável: Gabriella Marreto Rodrigues   
E-mail: marretogabriella@gmail.com   
Linkedin: https://www.linkedin.com/in/gabriella-marreto-3bab1722b/

