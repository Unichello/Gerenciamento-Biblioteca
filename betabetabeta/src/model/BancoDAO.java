package model;

import util.ArquivoUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// classe que representa o banco de dados do programa, ela implementa o Serializable, onde permite que ocorra a serialização dos dados
public class BancoDAO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static BancoDAO instance;
    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    // construtor privado em que ocorre a inicialização das listas
    private BancoDAO() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    // método para obtenção da instância única do banco de dados (Padrão Singleton)
    public static BancoDAO getInstance() {
        if (instance == null) {
            instance = new BancoDAO();
        }
        return instance;
    }

    // métodos gets para as listas
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // método para adicionar um usário
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        ArquivoUtil.salvarDados(this); // salvando dados após um usuario ser adicionado
        System.out.println("Usuário adicionado: " + usuario);
    }
    // método para remover um usuário
    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        ArquivoUtil.salvarDados(this); // salvando dados após um usuario ser removido
        System.out.println("Usuário removido: " + usuario);
    }
    // método para adiconar um livro
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        ArquivoUtil.salvarDados(this); // salvando dados após adicionar livro
        System.out.println("Livro adicionado: " + livro);
    }
    // método para remover um livro
    public void removerLivro(Livro livro) {
        livros.remove(livro);
        ArquivoUtil.salvarDados(this); // salvando dados após remover livro
        System.out.println("Livro removido: " + livro);
    }
    // método para adicionar um empréstimo
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        ArquivoUtil.salvarDados(this); // salvando dados após adicionar empréstimo
        System.out.println("Empréstimo adicionado: " + emprestimo);
    }
    // método para remover um empréstimo
    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
        ArquivoUtil.salvarDados(this); // Salvar dados após remover empréstimo
        System.out.println("Empréstimo removido: " + emprestimo);
    }

    // método para verificar os empréstimos existentes de um usuário
    public List<Emprestimo> verificarEmprestimosUsuario(Usuario usuario) {
        return getEmprestimos().stream()
                .filter(e -> e.getUsuario().equals(usuario))
                .toList();
    }

    // método para realizar um empréstimo
    public void realizarEmprestimo(Usuario usuario, Livro livro) {
        if (livro.getQtdEstoque() <= 0) {
            throw new IllegalArgumentException("Livro indisponível para empréstimo.");
        }
        // definindo o tempo limite e a duração do empréstimo com base no tipo de usuário
        List<Emprestimo> emprestimosUsuario = verificarEmprestimosUsuario(usuario);
        int limiteEmprestimos;
        long diasEmprestimo;

        if (usuario instanceof Estudante) {
            limiteEmprestimos = 3;
            diasEmprestimo = 15L * 24 * 60 * 60 * 1000; // 15 dias em milissegundos
        } else {
            limiteEmprestimos = 5;
            diasEmprestimo = 30L * 24 * 60 * 60 * 1000; // 30 dias em milissegundos
        }

        // verificando se o usuário atingiu o limite de empréstimos
        if (emprestimosUsuario.size() >= limiteEmprestimos) {
            throw new IllegalArgumentException("Usuário já atingiu o limite de empréstimos.");
        }

        livro.setQtdEstoque(livro.getQtdEstoque() - 1);
        // atualizando o estado do livro com base na quantidade de estoque
        if (livro.getQtdEstoque() == 0) {
            livro.setEstado(Livro.EstadoLivro.EMPRESTADO);
        } else {
            livro.setEstado(Livro.EstadoLivro.DISPONIVEL);
        }
        Date dataEmprestimo = new Date();
        Date dataDevolucaoPrevista = new Date(dataEmprestimo.getTime() + diasEmprestimo);

        // criando um novo empréstimo e o adicionando à lista
        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucaoPrevista);
        adicionarEmprestimo(emprestimo);
        ArquivoUtil.salvarDados(this);
    }

    // método para devolver um livro
    public void devolverLivro(Usuario usuario, Livro livro) {
        // encontrando o empréstimo correspondente
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getUsuario().equals(usuario) && e.getLivro().equals(livro))
                .findFirst()
                .orElse(null);

        // lançando uma exceção se o empréstimo não for encontrado no banco de dados
        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado.");
        } else {
            // remove o empréstimo e atualiza o estado do livro
            removerEmprestimo(emprestimo);
            livro.setQtdEstoque(livro.getQtdEstoque() + 1);
            if (livro.getQtdEstoque() > 0) {
                livro.setEstado(Livro.EstadoLivro.DISPONIVEL);
            }
            ArquivoUtil.salvarDados(this);
            System.out.println("Livro devolvido: " + livro);
        }
    }
}

