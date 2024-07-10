package controller;

import model.*;
import util.ArquivoUtil;
import model.BancoDAO;

import java.util.Date;
import java.util.List;

public class Operacoes {
    private BancoDAO banco = BancoDAO.getInstance();

    public void realizarEmprestimo(Usuario usuario, Livro livro) {
        if (livro.getQtdEstoque() <= 0) {
            throw new IllegalArgumentException("Livro indisponível para empréstimo.");
        }

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

        if (emprestimosUsuario.size() >= limiteEmprestimos) {
            throw new IllegalArgumentException("Usuário já atingiu o limite de empréstimos.");
        }

        livro.setQtdEstoque(livro.getQtdEstoque() - 1);
        if(livro.getQtdEstoque() < 1){
        livro.setEstado(Livro.EstadoLivro.EMPRESTADO);
        } else
            livro.setEstado(Livro.EstadoLivro.DISPONIVEL);
        Date dataEmprestimo = new Date();
        Date dataDevolucaoPrevista = new Date(dataEmprestimo.getTime() + diasEmprestimo);

        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucaoPrevista);
        banco.adicionarEmprestimo(emprestimo);
        ArquivoUtil.salvarDados(banco);
        System.out.println("Empréstimo realizado: " + emprestimo);
    }

    public void devolverLivro(Usuario usuario, Livro livro) {
        Emprestimo emprestimo = banco.getEmprestimos().stream()
                .filter(e -> e.getUsuario().equals(usuario) && e.getLivro().equals(livro))
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado.");
        } else {
            banco.removerEmprestimo(emprestimo);
            if (livro.getQtdEstoque() > 0) {
                livro.setEstado(Livro.EstadoLivro.DISPONIVEL);
            }
            ArquivoUtil.salvarDados(banco);
            System.out.println("Livro devolvido: " + livro);
        }
    }

    // método para verificar os empréstimos de um usuário
    public List<Emprestimo> verificarEmprestimosUsuario(Usuario usuario) {
        // filtra a lista de empréstimos para encontrar os empréstimos do usuário
        return banco.getEmprestimos().stream()
                .filter(e -> e.getUsuario().equals(usuario))
                .toList();
    }
}
