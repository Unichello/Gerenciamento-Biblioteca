package view;

import controller.Operacoes;
import model.*;
import util.ArquivoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BancoDAO banco = ArquivoUtil.carregarDados();
        Operacoes operacoes = new Operacoes();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Verificando e/ou criando o bibliotecário default
        Bibliotecario.verificarOuCriarBibliotecario(banco);

        // Verificando se há professores ou estudantes no banco, não existindo, cadastra os default
        if (banco.getUsuarios().stream().noneMatch(u -> u instanceof Professor || u instanceof Estudante)) {
            Professor.cadastrarProfessoresIniciais(banco, sdf);
            Estudante.cadastrarEstudantesIniciais(banco, sdf);
        }
        // verificando se existem livros cadastrados, caso não exista, cadastra os livros default
        if (banco.getLivros().isEmpty()) {
            Livro.cadastrarLivrosIniciais(banco);
        }

        // trecho de autenticação do bibliotecário
        Bibliotecario authBibliotecario = autenticarBibliotecario(banco, scanner);

        // menu do bibliotecario com as opções em switch-case
        if (authBibliotecario != null) {
            int opcao;
            do {
                System.out.println("\nMenu de Operações:");
                System.out.println("1. Adicionar Livro");
                System.out.println("2. Excluir Livro");
                System.out.println("3. Realizar Empréstimo");
                System.out.println("4. Devolver Livro");
                System.out.println("5. Pesquisar Livro");
                System.out.println("6. Verificar Empréstimos de Usuário");
                System.out.println("7. Adicionar Usuário (Professor ou Estudante)");
                System.out.println("8. Remover Usuário (Professor ou Estudante)");
                System.out.println("9. Listar Todos os Usuários");
                System.out.println("10. Listar Todos os Livros");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // consumindo uma nova linha

                switch (opcao) {
                    case 1:
                        // adicionando um livro à biblioteca
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Assunto: ");
                        String assunto = scanner.nextLine();
                        System.out.print("Ano de Lançamento: ");
                        int ano = scanner.nextInt();
                        System.out.print("Quantidade em Estoque: ");
                        int qtdEstoque = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        Livro livro = new Livro(titulo, autor, assunto, ano, qtdEstoque);
                        try {
                            Optional<Livro> livroExistente = banco.getLivros().stream()
                                    .filter(l -> l.getTitulo().equalsIgnoreCase(livro.getTitulo()) &&
                                            l.getAutor().equalsIgnoreCase(livro.getAutor()) &&
                                            l.getAssunto().equalsIgnoreCase(livro.getAssunto()) &&
                                            l.getAnoLancamento() == livro.getAnoLancamento())
                                    .findFirst();
                            if (livroExistente.isPresent()) {
                                throw new IllegalArgumentException("Livro já existe no sistema.");
                            } else {
                                banco.adicionarLivro(livro);
                                System.out.println("Livro adicionado com sucesso.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        // excluindo um livro da biblioteca
                        System.out.print("Título do livro a ser excluído: ");
                        titulo = scanner.nextLine();
                        Livro livroParaExcluir = banco.getLivros().stream()
                                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                                .findFirst()
                                .orElse(null);
                        if (livroParaExcluir != null) {
                            try {
                                banco.removerLivro(livroParaExcluir);
                                System.out.println("Livro excluído com sucesso.");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                        break;
                    case 3:
                        // realizando Empréstimo ao usuário, seja ele professor, estudante ou bibliotecario
                        System.out.print("Matrícula do usuário: ");
                        String matriculaUsuario = scanner.nextLine();
                        Usuario usuario = banco.getUsuarios().stream()
                                .filter(u -> u.getMatricula().equals(matriculaUsuario))
                                .findFirst()
                                .orElse(null);
                        if (usuario != null) {
                            System.out.print("Título do livro: ");
                            titulo = scanner.nextLine();
                            Livro livroParaEmprestimo = banco.getLivros().stream()
                                    .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                                    .findFirst()
                                    .orElse(null);
                            if (livroParaEmprestimo != null) {
                                try {
                                    banco.realizarEmprestimo(usuario, livroParaEmprestimo);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Livro não encontrado.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 4:
                        // fazendo a devolução do livro que ocorreu o empréstimo
                        System.out.print("Matrícula do usuário: ");
                        matriculaUsuario = scanner.nextLine();
                        usuario = banco.getUsuarios().stream()
                                .filter(u -> u.getMatricula().equals(matriculaUsuario))
                                .findFirst()
                                .orElse(null);
                        if (usuario != null) {
                            System.out.print("Título do livro: ");
                            titulo = scanner.nextLine();
                            Livro livroParaDevolucao = banco.getLivros().stream()
                                    .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                                    .findFirst()
                                    .orElse(null);
                            if (livroParaDevolucao != null) {
                                try {
                                    banco.devolverLivro(usuario, livroParaDevolucao);
                                    System.out.println("Livro devolvido com sucesso.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Livro não encontrado.");
                            }
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;
                    case 5:
                        // pesquisando um livro no banco de dados da biblioteca
                        System.out.print("Título do livro: ");
                        titulo = scanner.nextLine();
                        Livro livroPesquisado = banco.getLivros().stream()
                                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                                .findFirst()
                                .orElse(null);
                        if (livroPesquisado != null) {
                            System.out.println("Livro encontrado: " + livroPesquisado);
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                        break;
                    case 6:
                        // verificando se há empréstimos de um determinado usuário
                        System.out.print("Matrícula do usuário: ");
                        matriculaUsuario = scanner.nextLine();
                        usuario = banco.getUsuarios().stream()
                                .filter(u -> u.getMatricula().equals(matriculaUsuario))
                                .findFirst()
                                .orElse(null);
                        if (usuario != null) {
                            banco.verificarEmprestimosUsuario(usuario).forEach(System.out::println);
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;
                    case 7:
                        // adicionando novo usuário ao banco de dados da biblioteca
                        System.out.print("Tipo de usuário (1 - Professor, 2 - Estudante): ");
                        int tipoUsuario = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        System.out.print("Nome: ");
                        String nomeUsuario = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpfUsuario = scanner.nextLine();
                        System.out.print("Matrícula: ");
                        String matriculaUsuarioNovo = scanner.nextLine();
                        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                        String dataNascimentoStr = scanner.nextLine();
                        Date dataNascimento;
                        try {
                            dataNascimento = sdf.parse(dataNascimentoStr);
                        } catch (ParseException e) {
                            System.out.println("Formato de data inválido. Usuário não adicionado.");
                            break;
                        }
                        if (tipoUsuario == 1) {
                            // adicionando novo professor ao banco de dados da biblioteca
                            System.out.print("Departamento: ");
                            String departamento = scanner.nextLine();
                            Professor professor = new Professor(nomeUsuario, cpfUsuario, matriculaUsuarioNovo, dataNascimento, departamento);
                            banco.adicionarUsuario(professor);
                            System.out.println("Professor adicionado com sucesso.");
                        } else if (tipoUsuario == 2) {
                            // adicionando novo estudante ao banco de dados da biblioteca
                            System.out.print("Curso: ");
                            String curso = scanner.nextLine();
                            Estudante estudante = new Estudante(nomeUsuario, cpfUsuario, matriculaUsuarioNovo, dataNascimento, curso);
                            banco.adicionarUsuario(estudante);
                            System.out.println("Estudante adicionado com sucesso.");
                        } else {
                            System.out.println("Tipo de usuário inválido.");
                        }
                        break;
                    case 8:
                        // removendo um usuário do banco de dados da biblioteca
                        System.out.print("Matrícula do usuário a ser removido: ");
                        String matriculaParaRemover = scanner.nextLine();
                        Usuario usuarioParaRemover = banco.getUsuarios().stream()
                                .filter(u -> u.getMatricula().equals(matriculaParaRemover))
                                .findFirst()
                                .orElse(null);
                        if (usuarioParaRemover != null) {
                            banco.removerUsuario(usuarioParaRemover);
                            System.out.println("Usuário removido com sucesso.");
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;
                    case 9:
                        // listando todos os usuários presentes no banco de dados da biblioteca
                        System.out.println("Lista de Usuários:");
                        banco.getUsuarios().forEach(System.out::println);
                        break;
                    case 10:
                        // listando todos os livros presentes no banco de dados da biblioteca
                        listarTodosOsLivros(banco);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 0);
        } else {
            System.out.println("Falha na autenticação. Encerrando o programa.");
        }

        // Salvando as informações no banco mo final da execução do programa
        ArquivoUtil.salvarDados(banco);
    }

    // função de autenticação do bibliotecario para permitir o acesso ao programa
    private static Bibliotecario autenticarBibliotecario(BancoDAO banco, Scanner scanner) {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        return banco.getUsuarios().stream()
                .filter(u -> u instanceof Bibliotecario)
                .map(u -> (Bibliotecario) u)
                .filter(b -> b.getLogin().equals(login) && b.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    // função de listar todos os livros presentes no banco de dados da biblioteca
    private static void listarTodosOsLivros(BancoDAO banco) {
        System.out.println("Lista de Livros:");
        banco.getLivros().forEach(System.out::println);

    }
}
