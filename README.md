# SistemaMercado: Sistema Backend para Gerenciamento de Estoque e Pedidos

## Visão Geral

O **SistemaMercado** é uma aplicação backend desenvolvida em **Java** utilizando o framework **Spring Boot**. Ele simula um sistema de gestão de supermercado com foco no gerenciamento de clientes, produtos e pedidos. O sistema é projetado com uma arquitetura limpa que separa responsabilidades entre diferentes camadas, garantindo escalabilidade, facilidade de manutenção e aderência às boas práticas de desenvolvimento.

## Estrutura do Projeto

A arquitetura do projeto segue uma abordagem modular, organizada em pacotes, cada um responsável por uma funcionalidade específica:

```
br.ufrn.imd.sistemamercado
├── controllers      // Controladores REST para manipular requisições HTTP
├── dto              // Objetos de Transferência de Dados para modelos de requisição/resposta
├── enums            // Enumerações utilizadas no sistema
├── exceptions       // Exceções personalizadas e manipulação global de erros
├── model            // Entidades JPA que representam tabelas do banco de dados
├── repositories     // Interfaces para acesso a dados utilizando Spring Data JPA
├── security         // Configuração de segurança e autenticação JWT
└── services         // Camada de lógica de negócios
```

---

## Componentes Principais

### 1. **Controllers**

O pacote `controllers` contém os endpoints REST que expõem as funcionalidades do sistema. Esses controladores são responsáveis por manipular requisições e respostas HTTP.

- **ClienteController**: Gerencia operações relacionadas a clientes (criação, recuperação, atualização, exclusão).
- **ProdutoController**: Lida com operações de produtos (criação, gerenciamento de estoque, exclusão lógica).
- **PedidoController**: Gerencia pedidos (operações CRUD e processamento).

Cada controlador interage com a **camada de serviços** e delega tarefas de acesso a dados para a **camada de repositórios**.

---

### 2. **DTOs (Data Transfer Objects)**

O pacote `dto` contém classes usadas para transferir dados entre as camadas da aplicação e os endpoints da API.

- **ClienteDTO**: Define os campos necessários para criação/atualização de dados de clientes.
- **ProdutoDTO**: Define os campos necessários para criação/atualização de dados de produtos.
- **PedidoDTO**: Define os campos necessários para criação/atualização de pedidos.

Esses DTOs garantem que a estrutura interna das entidades fique escondida e que as requisições/respostas da API sejam bem definidas e consistentes.

---

### 3. **Enums**

O pacote `enums` contém as enumerações utilizadas no sistema. Por exemplo, a enumeração **UserRole** define os papéis que um usuário pode ter na aplicação:

```java
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
```

---

### 4. **Exceções Personalizadas**

- **ResourceNotFoundException**: Uma exceção personalizada lançada quando um recurso solicitado (cliente, produto ou pedido) não é encontrado.
- **GlobalExceptionHandler**: Um mecanismo centralizado de tratamento de erros que captura exceções lançadas na aplicação e as mapeia para respostas HTTP apropriadas (por exemplo, 404 para recurso não encontrado).

---

### 5. **Modelos (Entidades)**

O pacote `model` contém **entidades JPA** que mapeiam diretamente para tabelas do banco de dados:

- **ClienteEntity**: Representa um cliente, incluindo campos como `nome`, `cpf`, `genero` e `ativo`.
- **ProdutoEntity**: Representa um produto, com campos como `nome`, `quantidade`, `preço` e `ativo`.
- **PedidoEntity**: Representa um pedido, associando clientes a produtos e suas quantidades.

Cada entidade é anotada com mapeamentos JPA para integração com o banco de dados.

---

### 6. **Repositórios**

O pacote `repositories` contém **interfaces Spring Data JPA** para acesso a dados:

- **ClienteRepository**
- **ProdutoRepository**
- **PedidoRepository**

Esses repositórios abstraem as operações de banco de dados, permitindo que os desenvolvedores realizem operações CRUD usando métodos de alto nível, sem a necessidade de escrever consultas SQL.

---

### 7. **Services**

O pacote `services` contém a lógica de negócios do sistema. Cada serviço é responsável por implementar casos de uso específicos, coordenando as interações entre o controlador e o repositório:

- **ClienteService**: Lógica de negócios para gerenciamento de clientes, incluindo ativação/desativação.
- **ProdutoService**: Gerencia o estoque de produtos e realiza exclusões lógicas.
- **PedidoService**: Processa pedidos, incluindo validação de clientes e disponibilidade de produtos.

---

### 8. **Security**

O pacote `security` foi adicionado para garantir a **autenticação e autorização** dos usuários na aplicação. Utiliza o padrão **JWT (JSON Web Token)** para proteger as rotas da API e garantir que apenas usuários autenticados tenham acesso aos recursos.

#### Componentes de Segurança

- **TokenService**: Responsável por gerar e validar tokens JWT.
- **SecurityFilter**: Filtro que intercepta as requisições e verifica a validade do token JWT antes de permitir o acesso aos endpoints protegidos.
- **AuthorizationService**: Implementa a interface `UserDetailsService` do Spring Security para carregar detalhes do usuário com base no login.

#### Configuração de Segurança

A configuração de segurança está na classe `SecurityConfiguration`:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/produtos").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            )
            .addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
```

---

## Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

### **Gerenciamento de Clientes**
- Criar, atualizar e recuperar dados de clientes.
- Realizar exclusão lógica (desativação) de clientes.

### **Gestão de Estoque de Produtos**
- Gerenciar detalhes dos produtos, incluindo estoque e preço.
- Suporte para exclusão lógica de produtos inativos.

### **Processamento de Pedidos**
- Associar pedidos a clientes e produtos específicos.
- Validar disponibilidade de estoque durante a criação do pedido.

---

## Como Executar a Aplicação

1. **Pré-requisitos**
    - Java 21+
    - Maven
    - MySQL

2. **Passos**
    - Clone o repositório:
      ```bash
      git clone <repository-url>
      ```
    - Navegue até o diretório do projeto:
      ```bash
      cd sistemamercado
      ```
    - Configure as configurações do banco de dados em `application.properties`.
    - Construa e execute a aplicação:
      ```bash
      mvn spring-boot:run
      ```
    - Acesse a API em `http://localhost:8080`.

---

## Endpoints de Exemplo

- **API de Clientes**
    - `GET /clientes/`: Recupera todos os clientes ativos.
    - `POST /clientes/`: Cria um novo cliente.
    - `DELETE /clientes/dl/{id}`: Desativa um cliente.

- **API de Produtos**
    - `GET /produtos/`: Recupera todos os produtos ativos.
    - `PUT /produtos/{id}`: Atualiza os detalhes do produto.

- **API de Pedidos**
    - `POST /pedidos/`: Cria um novo pedido.
    - `GET /pedidos/{id}`: Recupera um pedido por ID.

---

