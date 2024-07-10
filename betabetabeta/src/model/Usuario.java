package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Usuario implements UsuarioInterface, Serializable {
    private String nome;
    private String cpf;
    private String matricula;
    private Date dataNascimento;
    // construtor onde é inicializado os atributos de usuário usando os métodos set
    public Usuario(String nome, String cpf, String matricula, Date dataNascimento) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setDataNascimento(dataNascimento);
    }
    // método get para o nome
    @Override
    public String getNome() {
        return nome;
    }
    // método set para o nome
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
    // método get para o Cpf
    @Override
    public String getCpf() {
        return cpf;
    }
    // método get para o Cpf
    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // método get para a matrícula
    @Override
    public String getMatricula() {
        return matricula;
    }
    // método set para a matrícula
    @Override
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    // método set para a data de nascimento
    @Override
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    // método para formatação da data de nascimento, para que não apareça as horas
    @Override
    public String getDataNascimentoFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataNascimento);
    }
    // método toString onde ele retorna uma representação em forma de texto do usuário, usando os métodos get
    @Override
    public String toString() {
        return "Usuario{" + "nome: '" + getNome() + '\'' + ", cpf: '" + getCpf() + '\'' + ", matricula: '" + getMatricula() + '\'' + ", dataNascimento: " + getDataNascimentoFormatada() + '}';
    }
}
