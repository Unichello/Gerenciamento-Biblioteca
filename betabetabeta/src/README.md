#### LINGUAGEM DE PROGRAMAÇÃO II - UNIVERSIDADE FEDERAL DO RIO GRANDE DO NORTE

+ Docente: Janiheryson Felipe De Oliveira Martins
+ Discente: Pedro Henrique Sousa Barichello

**Projeto 2 -  Sistema de Gerenciamento de Biblioteca**

 #### Instruções de uso e informações

    Descrição
    
    Sistema de biblioteca simples desenvolvido em Java 
    que permite gerenciar usuários(professores, estudantes e bibliotecário), 
    livros e empréstimos. 
    O sistema possui um menu de operações acessível apenas
    por um bibliotecário com credenciais default.

    Requisitos
    • Java JDK instalado(A versão desse programa é a JDK 21 version 17.0.9)
    • Ambiente de desenvolvimento Java (IntelliJ IDEA, Eclipse, etc)

    Passos para Executar
    • Abra o projeto no seu ambiente de desenvolvimento Java.
    • Compile e execute a classe 'view.Main'.

    Como acessar o programa:
    Ao iniciar o programa, será solicitado o login e a senha do bibliotecário.
    O login e a senha do programa na sua primeira execução é fixa
    sendo o login: adm; e a senha: 123; Após a primeira execução, é
    possível alterar o cadastro do bibliotecario livremente.
    Login: adm;
    Senha: 123;

    Funcionalidades
    
    O sistema oferece as seguintes funcionalidades:

    1. Adicionar Livro
    2. Excluir Livro
    3. Realizar Empréstimo
    4. Devolver Livro
    5. Pesquisar Livro
    6. Verificar Empréstimos de Usuário
    7. Adicionar Usuário (Professor ou Estudante)
    8. Remover Usuário (Professor ou Estudante)
    9. Listar Todos os Usuários
    10. Listar Todos os Livros
    0. Sair

    Menu de Operações
    Após a autenticação bem-sucedida, o menu de operações será exibido. Você pode escolher entre as seguintes opções:

    Adicionar Livro: Permite adicionar um novo livro ao sistema. Você precisará fornecer o título, autor, assunto, ano de lançamento e quantidade em estoque do livro.
    Excluir Livro: Permite excluir um livro existente do sistema. Você precisará fornecer o título do livro a ser excluído.
    Realizar Empréstimo: Permite realizar o empréstimo de um livro para um usuário. Você precisará fornecer a matrícula do usuário e o título do livro.
    Devolver Livro: Permite devolver um livro emprestado. Você precisará fornecer a matrícula do usuário e o título do livro.
    Pesquisar Livro: Permite pesquisar um livro no sistema pelo título.
    Verificar Empréstimos de Usuário: Permite verificar os empréstimos de um usuário específico. Você precisará fornecer a matrícula do usuário.
    Adicionar Usuário (Professor ou Estudante): Permite adicionar um novo usuário ao sistema. Você precisará fornecer o nome, CPF, matrícula, data de nascimento e curso/departamento do usuário.
    Remover Usuário (Professor ou Estudante): Permite remover um usuário existente do sistema. Você precisará fornecer a matrícula do usuário a ser removido.
    Listar Todos os Usuários: Exibe uma lista de todos os usuários cadastrados no sistema.
    Listar Todos os Livros: Exibe uma lista de todos os livros cadastrados no sistema.
    Sair: Encerra o programa.

    Classes e Pacotes
    O código está organizado nos seguintes pacotes:

    controller: Contém a classe Operacoes, responsável por realizar as operações de empréstimo e devolução de livros.
    model: Contém as classes de modelo (Usuario, UsuarioInterface, Professor, Estudante, Livro, Emprestimo, Bibliotecario, BancoDAO), que representam os dados do sistema.
    util: Contém a classe ArquivoUtil, responsável por salvar e carregar os dados do sistema a partir de um arquivo.
    view: Contém a classe Main, que é o ponto de entrada do programa e gerencia o menu de operações.

    Explicação da funcionalidade de cada classe:
    
    BancoDAO
    A classe BancoDAO é responsável por gerenciar a lista de usuários, livros e empréstimos.
    Ela utiliza o padrão Singleton para garantir que apenas uma instância do banco de dados exista.
    Esta classe contém métodos para adicionar e remover usuários, livros e empréstimos, bem como métodos para salvar e carregar dados de/para um arquivo.

    Operacoes
    A classe Operacoes contém os métodos para realizar operações de empréstimo e devolução de livros.
    Ela utiliza a instância única do BancoDAO para acessar e manipular os dados.
    Esta classe define a lógica de negócio para as operações de empréstimo e devolução, incluindo verificações de disponibilidade de livros e limites de empréstimos por usuário.

    Usuario
    A classe Usuario é uma classe abstrata que representa um usuário genérico do sistema.
    Ela possui atributos comuns a todos os usuários, como nome, CPF, matrícula e data de nascimento, e métodos getters e setters para esses atributos.
    A classe também implementa a interface UsuarioInterface.

    UsuarioInterface
    A interface UsuarioInterface define os métodos que devem ser implementados pelas classes que representam usuários no sistema.
    Ela inclui métodos para obter e definir o nome, CPF, matrícula e data de nascimento dos usuários.

    Professor
    A classe Professor é uma subclasse de Usuario que representa um professor.
    Ela adiciona um atributo departamento e seus métodos getters e setters correspondentes.
    A classe também contém um método estático para cadastrar professores iniciais.

    Estudante
    A classe Estudante é uma subclasse de Usuario que representa um estudante.
    Ela adiciona um atributo curso e seus métodos getters e setters correspondentes.
    A classe também contém um método estático para cadastrar estudantes iniciais.

    Livro
    A classe Livro representa um livro no sistema.
    Ela possui atributos como título, autor, assunto, ano de lançamento, quantidade em estoque e estado do livro (disponível ou emprestado).
    A classe inclui métodos getters e setters para esses atributos, além de um método estático para cadastrar livros iniciais.

    Emprestimo
    A classe Emprestimo representa um empréstimo de um livro por um usuário.
    Ela possui atributos para o usuário, livro, data de empréstimo e data de devolução prevista.
    A classe inclui métodos getters e setters para esses atributos.

    Bibliotecario
    A classe Bibliotecario é uma subclasse de Usuario que representa um bibliotecário.
    Ela adiciona atributos para login e senha, e métodos getters e setters correspondentes.
    A classe também contém um método estático para verificar ou criar um bibliotecário padrão no sistema.

    ArquivoUtil
    A classe ArquivoUtil fornece métodos para salvar e carregar os dados do sistema a partir de um arquivo chamado banco.dat.
    Ela utiliza serialização para persistir os dados do BancoDAO.

    Main
    A classe Main contém o método main que inicializa o sistema, autentica o bibliotecário e exibe o menu de operações.
    Cada opção do menu chama os métodos apropriados na classe Operacoes ou diretamente no BancoDAO.