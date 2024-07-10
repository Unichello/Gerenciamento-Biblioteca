package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    // atributos de Livro
    private String id;
    private String titulo;
    private String autor;
    private String assunto;
    private int anoLancamento;
    private int qtdEstoque;
    private EstadoLivro estado;

    // Enum que representa o estado do livro entre Disponivel e Emprestado
    public enum EstadoLivro {
        DISPONIVEL,
        EMPRESTADO
    }
    // construtor que faz a inicialização dos atributos de livro e define o estado com base na quantidade em estoque e empréstimos, utilizando os set
    public Livro(String titulo, String autor, String assunto, int anoLancamento, int qtdEstoque) {
        this.id = UUID.randomUUID().toString();
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAssunto(assunto);
        this.setAnoLancamento(anoLancamento);
        this.setQtdEstoque(qtdEstoque);
        this.setEstado(qtdEstoque > 0 ? EstadoLivro.DISPONIVEL : EstadoLivro.EMPRESTADO); // é iniciaizado o estado com base na quantidade de estoque
    }
    // métodos get e set para os atributos de livros
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    // definindo a quantidade em estoque e atualizando o estado do livro com base na quantidade dele
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
        this.setEstado(qtdEstoque > 0 ? EstadoLivro.DISPONIVEL : EstadoLivro.EMPRESTADO); // Atualiza o estado com base na quantidade de estoque
    }

    public EstadoLivro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLivro estado) {
        this.estado = estado;
    }
    // método equals que verifica se dois objetos Livro são iguais com base no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }
    // método hashCode que retorna o código hash do objeto com base no ID
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // método toString onde ele retorna uma representação em forma de texto do livro
    @Override
    public String toString() {
        return "Livro{" + "id:'" + getId() + '\'' + ", titulo: '" + getTitulo() + '\'' + ", autor: '" + getAutor() + '\'' + ", assunto: '" + getAssunto() + '\'' + ", anoLancamento: " + getAnoLancamento() + ", qtdEstoque: " + getQtdEstoque() + ", estado: " + getEstado() + '}';
    }
    // método estático para cadastrar livros iniciais no banco de dados, caso não haja nenhum livro cadastrado(primeira execução)
    public static void cadastrarLivrosIniciais(BancoDAO banco) {
        banco.adicionarLivro(new Livro("Matematica Basica", "Jorge Fagundes", "Matemática", 2011, 4));
        banco.adicionarLivro(new Livro("Quimica Fundamental", "Pedro João", "Química", 2005, 1));
        banco.adicionarLivro(new Livro("Programacao Basica", "Janyherison Felipe", "Programação", 2024, 2));
        banco.adicionarLivro(new Livro("Estrutura de dados", "Jorge Estefano", "Informática/Programação", 2003, 3));
        banco.adicionarLivro(new Livro("Educacao Fundamental", "Paulo Freire", "Educação", 1975, 8));
    }
}
