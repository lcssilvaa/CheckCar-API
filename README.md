# 🚗 CheckCar API

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)
![JUnit 5](https://img.shields.io/badge/JUnit-5-red?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-Mocking-yellow?logo=java)
![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-green?logo=swagger)

Sistema de checklist veicular desenvolvido para controle de inspeções, acompanhamento de respostas por item e geração de relatórios. Ideal para oficinas, frotas e operações que exigem verificação sistemática de veículos.

---

## 📦 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Swagger (SpringDoc OpenAPI)**
- **Maven**
- **JUnit 5** — framework de testes
- **Mockito** — criação de mocks
- **Spring Boot Test** — testes de integração
- **MockMvc** — simulação de requisições HTTP

---

## 🚀 Como executar o projeto

### 🔧 Pré-requisitos

- Java 21
- MySQL rodando localmente
- IDE com suporte a Maven (IntelliJ, Eclipse ou VS Code)

---

## ▶️ Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/checkcar-api.git
   
2. **Configure o banco de dados no application.properties:**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/checkcar
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   springdoc.api-docs.enabled=true
   springdoc.swagger-ui.enabled=true```

3. **Execute o projeto:**

   ```bash
   mvn spring-boot:run

4. **Acesse a documentação Swagger:**

   ```
   http://localhost:8080/swagger-ui/index.html
   
---

## 🧪 Testes
O projeto conta com cobertura de testes unitários e de integração.

### ✔️ Unitários (Mockito + MockMvc)
Isolam controllers e services usando @WebMvcTest e @MockBean.

### ✔️ MockMvc
Simula chamadas HTTP e valida JSON de resposta.

### ✔️ JUnit 5
Organização e execução de testes.

### 🧰 Exemplo de teste unitário (MockMvc + Mockito)
   ```
   @WebMvcTest(ItemChecklistController.class)
   class ItemChecklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemChecklistService itemChecklistService;

    @Test
    void deveListarItens() throws Exception {
        ItemChecklist item = new ItemChecklist();
        item.setId(1);
        item.setNome("Óleo");
        item.setTipoVeiculo(TipoVeiculo.CARRO);

        when(itemChecklistService.listarTodos()).thenReturn(List.of(item));

        mockMvc.perform(get("/api/itens-checklist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Óleo"));
       }
   }
   ```

---

## 📘 Endpoints principais

### ✅ Checklist
- `POST /checklists` — Cadastra checklist

- `GET /checklists` — Lista todos

- `GET /checklists/{id}` — Busca por ID

- `DELETE /checklists/{id}` — Remove

### 🧾 ItemChecklist

- `POST /itens-checklist`

- `GET /itens-checklist`

- `GET /itens-checklist/{id}`

- `DELETE /itens-checklist/{id}`

- `GET /itens-checklist/tipo/{tipo}`

### ❓ PerguntaChecklist
- `POST /perguntas`

- `GET /perguntas?tipoVeiculo=CARRO`

- `GET /perguntas/todas`

### 📝 RespostaChecklist
- `POST /respostas-checklist`

- `POST /respostas-checklist/lote`

- `GET /respostas-checklist`

- `GET /respostas-checklist/{id}`

- `DELETE /respostas-checklist/{id}`

---

## 📚 Documentação Swagger
Acesse a interface interativa 👇

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧠 Funcionalidades futuras
- Validação de checklist completo
- Relatório resumido por checklist
- Autenticação via JWT
- Integração com front-end (React)
- Deploy em nuvem (Render, Railway)

---

## 👨‍💻 Autor
- Lucas 
- Projeto desenvolvido para fins acadêmicos e profissionais.
#### 📍 Contagem — MG, Brasil