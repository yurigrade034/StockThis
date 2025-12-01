# 📦 StockThis — Sistema de Gerenciamento de Estoque

O *StockThis* é um sistema desenvolvido em Java com JavaFX para controle de estoque, cadastro de produtos, atualização de quantidades e gerenciamento básico das operações em um sistema desktop. O projeto utiliza conexão com banco de dados H2, interface gráfica em FXML e um padrão próximo ao MVC.

---

## 🚀 Tecnologias Utilizadas

- Java JDK 24.0.2
- JavaFX
- Maven
- H2 Database (h2-2.4.240.jar)
- FXML
- SceneBuilder (para edição das telas)
- MVC simplificado (Controllers, DAO, Model)

---

## 🖥 Funcionalidades Principais

- Login e autenticação
- Dashboard com visão geral
- Cadastro de produtos
- Controle de estoque
- Atualização de preço
- Inserção de novos itens
- Redefinição de senha
- Interface gráfica com FXML

---

## ✅ Testes Unitários (2 testes)
- Adição de itens ao estoque
- Verificador de valores exatos no banco de dados

---

## 📁 Estrutura de Pastas (Fiel ao Projeto)

text

StockThis/
├── .idea/
│   ├── .gitignore
│   ├── compiler.xml
│   ├── encodings.xml
│   ├── jarRepositories.xml
│   ├── misc.xml
│   ├── vcs.xml
│   └── workspace.xml
│
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
│
├── data/
│   └── stockthis.mv.db
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/unipar/stockthis/
│   │   │       ├── BANCO_DADOS/
│   │   │       │   ├── Database.java
│   │   │       │   ├── ProdutoDAO.java
│   │   │       │   └── UsuarioDAO.java
│   │   │       │
│   │   │       ├── CADASTRO/
│   │   │       │   ├── CadastroController.java
│   │   │       │   ├── LoginController.java
│   │   │       │   ├── RedefinirSenhaController.java
│   │   │       │   └── Usuario.java
│   │   │       │
│   │   │       ├── ESTOQUE/
│   │   │       │   ├── AlterarPrecoController.java
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── EstoqueController.java
│   │   │       │   ├── HelloController.java
│   │   │       │   ├── NovoItemController.java
│   │   │       │   └── Produto.java
│   │   │       │
│   │   │       ├── HelloApplication.java
│   │   │       ├── Launcher.java
│   │   │       │
│   │   │       └── module-info.java
│   │   │
│   │   └── resources/
│   │       └── br/unipar/stockthis/
│   │           ├── AlterarPreco.fxml
│   │           ├── Cadastro.fxml
│   │           ├── Dashboard.fxml
│   │           ├── Estoque.fxml
│   │           ├── hello-view.fxml
│   │           ├── Login.fxml
│   │           ├── Novoltem.fxml
│   │           ├── Redefinir_senha.fxml
│   │           │
│   │           └── Images/
│   │               └── IconEstoque.png
│   │
│   └── test/
│       └── java/
│           └── br/unipar/stockthis/BANCO_DADOS/
│               └── ProdutoDAOTest.java
│
├── .gitignore
├── h2-2.4.240.jar
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md



⚙ Como Executar o Projeto
Clonar o repositório
git clone <https://github.com/yurigrade034/StockThis.git>
Importar no IntelliJ/Eclipse
Aguarde baixar as dependências
Executar
No IntelliJ, abra o arquivo:
src/main/java/br/unipar/stockthis/Main.java
E execute a aplicação.

🗄 Banco de Dados
O projeto utiliza H2 Database embutido.
Arquivo incluído:
h2-2.4.240.jar

Para abrir o console do H2, abra o terminal dentro da pasta do projeto e execute:

java -jar h2-2.4.240.jar


No H2, utilize os seguintes dados de conexão:

URL: jdbc:h2:./data/stockthis
USER: StockThis
PASSWORD: 12345

📚 Organização do Código
Controllers
Controlam as telas FXML e interações do usuário.
Model
Representação das entidades:
Produto
Usuario
DAO
Camada de acesso ao banco de dados, responsável pelas operações.
ProdutoDAO
UsuarioDAO
Util


Autores: Yuri Grade 60005585, Moacir Samoel Anhaia 60005619, Thiago Cornelius 60012173
