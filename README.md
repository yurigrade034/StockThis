📦 StockThis — Sistema de Gerenciamento de Estoque
O StockThis é um sistema desenvolvido em Java com JavaFX para controle de estoque, cadastro de produtos, atualização de quantidades e gerenciamento básico das operações em um sistema desktop. O projeto utiliza conexão com banco de dados H2, interface gráfica em FXML e um padrão próximo ao MVC.

🚀 Tecnologias Utilizadas
Java 17+
JavaFX
Maven
H2 Database (h2-2.4.240.jar)
FXML
SceneBuilder (para edição das telas)
MVC simplificado (Controllers, DAO, Model)

🖥️ Funcionalidades Principais
Login e autenticação
Dashboard com visão geral
Cadastro de produtos
Controle de estoque
Atualização de preço
Inserção de novos itens
Redefinição de senha
Interface gráfica com FXML

📁 Estrutura de Pastas (Fiel ao Projeto)
stockthis/
└── StockThis-master/
    ├── .gitignore
    ├── README.txt
    ├── h2-2.4.240.jar
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── .idea/
    │   ├── .gitignore
    │   ├── encodings.xml
    │   ├── misc.xml
    │   └── vcs.xml
    ├── .mvn/
    │   └── wrapper/
    │       └── maven-wrapper.jar
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── br/
        │   │       └── unipar/
        │   │           └── stockthis/
        │   │               ├── controllers/
        │   │               │   ├── AlterarPrecoController.java
        │   │               │   ├── CadastroController.java
        │   │               │   ├── DashboardController.java
        │   │               │   ├── EstoqueController.java
        │   │               │   ├── LoginController.java
        │   │               │   ├── NovoItemController.java
        │   │               │   ├── RedefinirSenhaController.java
        │   │               │   └── HelloController.java
        │   │               ├── dao/
        │   │               │   ├── ProdutoDAO.java
        │   │               │   └── UsuarioDAO.java
        │   │               ├── database/
        │   │               │   └── Conexao.java
        │   │               ├── model/
        │   │               │   ├── Produto.java
        │   │               │   └── Usuario.java
        │   │               ├── Main.java
        │   │               └── util/
        │   │                   └── Alerta.java
        │   └── resources/
        │       ├── Images/
        │       │   └── IconEstoque.png
        │       └── br/
        │           └── unipar/
        │               └── stockthis/
        │                   ├── AlterarPreco.fxml
        │                   ├── Cadastro.fxml
        │                   ├── Dashboard.fxml
        │                   ├── Estoque.fxml
        │                   ├── Login.fxml
        │                   ├── NovoItem.fxml
        │                   ├── Redefinir_senha.fxml
        │                   └── hello-view.fxml

⚙️ Como Executar o Projeto
1. Clonar o repositório
git clone <url-do-repositorio>
2. Importar no IntelliJ/Eclipse
Abra como projeto Maven
Aguarde baixar dependências
3. Executar
No IntelliJ, abra o arquivo:
src/main/java/br/unipar/stockthis/Main.java
E execute.

🗄️ Banco de Dados
O projeto utiliza H2 Database embutido.
Arquivo incluído:
h2-2.4.240.jar
A conexão está definida em:
src/main/java/br/unipar/stockthis/database/Conexao.java
Para abrir abra o terminal dentro da pasta do projeto.
O comando para o terminal: 
```
java -jar h2-2.4.240.jar 
```
URL = "jdbc:h2:./data/stockthis"; USER = "StockThis"; PASSWORD = "12345";

📚 Organização do Código
Controllers
Controlam as telas FXML e interações do usuário.
Model
Representação das entidades:
Produto
Usuário
DAO
Acesso ao banco e operações CRUD.
Util

Classes auxiliares (ex.: alertas e mensagens).
