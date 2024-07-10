package model;

import java.io.Serializable;
import java.util.Date;

public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;

    // construtor que faz inicialização dos atributos do empréstimo, utilizando os set
    public Emprestimo(Usuario usuario, Livro livro, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        this.setUsuario(usuario);
        this.setLivro(livro);
        this.setDataEmprestimo(dataEmprestimo);
        this.setDataDevolucaoPrevista(dataDevolucaoPrevista);
    }
    // método get para usuario do emprestimo
    public Usuario getUsuario() {
        return usuario;
    }
    // método set para usuario do emprestimo
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // método set para livro do emprestimo
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    // método set para a data do emprestimo
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    // método set para a devolução prevista do emprestimo
    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    // método get para o livro de emprestimo
    public Livro getLivro() {
        return livro;
    }
    // método get para a data do emprestimo
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    // método get para a data de devolução prevista do emprestimo
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    // método toString onde ele retorna uma representação em forma de texto do emprestimo, incluindo o usuario, livro, data do emprestimo, data da devolução prevista
    @Override
    public String toString() {
        return "Emprestimo{" + "usuario=" + getUsuario() + ", livro=" + getLivro() + ", dataEmprestimo=" + getDataEmprestimo() + ", dataDevolucaoPrevista=" + getDataDevolucaoPrevista() + '}';
    }
}
