Sistema de API de mercado, com dois controllers, ProdutoController e ClienteController
---

## **ProdutoController**

**Base URL:** `http://localhost:8080/produtos`

### 1. **GET /** - Listar todos os produtos ativos
- **Descrição:** Retorna todos os produtos com o campo `ativo = true`.
- **Método HTTP:** GET
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/`
- **Resposta de Sucesso (200 OK):**
  ```json
  [
      {
          "id": 1,
          "nomeProduto": "Arroz Integral",
          "marca": "Marca Terra",
          "dataFabricacao": "2023-02-15",
          "dataValidade": "2024-02-15",
          "genero": "Alimento",
          "lote": "102A",
          "ativo": true
      },
      {
          "id": 2,
          "nomeProduto": "Leite Desnatado",
          "marca": "Lácteos Vida",
          "dataFabricacao": "2023-06-01",
          "dataValidade": "2024-06-01",
          "genero": "Bebida",
          "lote": "325B",
          "ativo": true
      }
  ]
  ```

### 2. **GET /{id}** - Obter um produto por ID
- **Descrição:** Retorna os detalhes de um produto específico.
- **Método HTTP:** GET
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  {
      "id": 1,
      "nomeProduto": "Arroz Integral",
      "marca": "Marca Terra",
      "dataFabricacao": "2023-02-15",
      "dataValidade": "2024-02-15",
      "genero": "Alimento",
      "lote": "102A",
      "ativo": true
  }
  ```
- **Resposta de Erro (404 NOT FOUND):**
  ```json
  "Produto não encontrado"
  ```

### 3. **POST /** - Adicionar um novo produto
- **Descrição:** Adiciona um novo produto à base de dados.
- **Método HTTP:** POST
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/`
  - **Body (JSON):**
    ```json
    {
        "nomeProduto": "Óleo de Soja",
        "marca": "Saúde Natural",
        "dataFabricacao": "2023-03-10",
        "dataValidade": "2024-03-10",
        "genero": "Alimento",
        "lote": "451C"
    }
    ```
- **Resposta de Sucesso (201 CREATED):**
  ```json
  {
      "id": 3,
      "nomeProduto": "Óleo de Soja",
      "marca": "Saúde Natural",
      "dataFabricacao": "2023-03-10",
      "dataValidade": "2024-03-10",
      "genero": "Alimento",
      "lote": "451C",
      "ativo": true
  }
  ```

### 4. **PUT /{id}** - Atualizar um produto
- **Descrição:** Atualiza as informações de um produto específico.
- **Método HTTP:** PUT
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/1`
  - **Body (JSON):**
    ```json
    {
        "nomeProduto": "Arroz Integral Orgânico",
        "marca": "Marca Terra",
        "dataFabricacao": "2023-02-15",
        "dataValidade": "2024-02-15",
        "genero": "Alimento",
        "lote": "102A"
    }
    ```
- **Resposta de Sucesso (200 OK):**
  ```json
  {
      "id": 1,
      "nomeProduto": "Arroz Integral Orgânico",
      "marca": "Marca Terra",
      "dataFabricacao": "2023-02-15",
      "dataValidade": "2024-02-15",
      "genero": "Alimento",
      "lote": "102A",
      "ativo": true
  }
  ```

### 5. **DELETE /{id}** - Deletar fisicamente um produto
- **Descrição:** Remove fisicamente um produto do banco de dados.
- **Método HTTP:** DELETE
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  "Produto deletado com sucesso"
  ```

### 6. **DELETE /dl/{id}** - Desativação lógica de um produto
- **Descrição:** Desativa um produto definindo `ativo` como `false`, sem removê-lo fisicamente.
- **Método HTTP:** DELETE
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/produtos/dl/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  "Produto desativado logicamente"
  ```

---

## **ClienteController**

**Base URL:** `http://localhost:8080/clientes`

### 1. **GET /** - Listar todos os clientes ativos
- **Descrição:** Retorna todos os clientes com o campo `ativo = true`.
- **Método HTTP:** GET
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/clientes/`
- **Resposta de Sucesso (200 OK):**
  ```json
  [
      {
          "id": 1,
          "nome": "João da Silva",
          "cpf": "123.456.789-00",
          "genero": "Masculino",
          "dataNascimento": "1980-10-12",
          "ativo": true
      },
      {
          "id": 2,
          "nome": "Maria Oliveira",
          "cpf": "987.654.321-00",
          "genero": "Feminino",
          "dataNascimento": "1992-05-25",
          "ativo": true
      }
  ]
  ```

### 2. **GET /{id}** - Obter um cliente por ID
- **Descrição:** Retorna os detalhes de um cliente específico.
- **Método HTTP:** GET
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/clientes/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  {
      "id": 1,
      "nome": "João da Silva",
      "cpf": "123.456.789-00",
      "genero": "Masculino",
      "dataNascimento": "1980-10-12",
      "ativo": true
  }
  ```
- **Resposta de Erro (404 NOT FOUND):**
  ```json
  "Cliente não encontrado"
  ```

### 3. **POST /** - Adicionar um novo cliente
- **Descrição:** Adiciona um novo cliente à base de dados.
- **Método HTTP:** POST
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/clientes/`
  - **Body (JSON):**
    ```json
    {
        "nome": "Carlos Alberto",
        "cpf": "321.654.987-11",
        "genero": "Masculino",
        "dataNascimento": "1975-08-15"
    }
    ```
- **Resposta de Sucesso (201 CREATED):**
  ```json
  {
      "id": 3,
      "nome": "Carlos Alberto",
      "cpf": "321.654.987-11",
      "genero": "Masculino",
      "dataNascimento": "1975-08-15",
      "ativo": true
  }
  ```

### 4. **PUT /{id}** - Atualizar um cliente
- **Descrição:** Atualiza as informações de um cliente específico.
- **Método HTTP:** PUT
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/clientes/1`
  - **Body (JSON):**
    ```json
    {
        "nome": "João Carlos da Silva",
        "cpf": "123.456.789-00",
        "genero": "Masculino",
        "dataNascimento": "1980-10-12",
        "ativo": true
    }
    ```
- **Resposta de Sucesso (200 OK):**
  ```json
  {
      "id": 1,
      "nome": "João Carlos da Silva",
      "cpf": "123.456.789-00",
      "genero": "Masculino",
      "dataNascimento": "1980-10-

12",
"ativo": true
}
  ```

### 5. **DELETE /{id}** - Deletar fisicamente um cliente
- **Descrição:** Remove fisicamente um cliente do banco de dados.
- **Método HTTP:** DELETE
- **Exemplo de Requisição no Postman:**
    - **URL:** `http://localhost:8080/clientes/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  "Cliente deletado com sucesso"
  ```

### 6. **DELETE /dl/{id}** - Desativação lógica de um cliente
- **Descrição:** Desativa um cliente definindo `ativo` como `false`, sem removê-lo fisicamente.
- **Método HTTP:** DELETE
- **Exemplo de Requisição no Postman:**
  - **URL:** `http://localhost:8080/clientes/dl/1`
- **Resposta de Sucesso (200 OK):**
  ```json
  "Cliente desativado logicamente"
  ```