package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Professor extends Usuario {
    // atributo da classe Professor
    private String departamento;
    // construtor que faz a inicialização dos atributos de Professor, usando os métodos set
    public Professor(String nome, String cpf, String matricula, Date dataNascimento, String departamento) {
        super(nome, cpf, matricula, dataNascimento);
        this.setDepartamento(departamento);
    }
    // método get para departamento
    public String getDepartamento() {
        return departamento;
    }
    // método set para departamento
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    // método toString onde ele retorna uma representação em forma de texto do Professor, incluindo o departamento
    @Override
    public String toString() {
        return "Professor{" + "departamento:'" + getDepartamento() + '\'' + "} " + super.toString();
    }
    // método estático que cadastra os professores iniciais no banco de dados, caso não existe nenhum professor cadastrado(primeira execução)
    public static void cadastrarProfessoresIniciais(BancoDAO banco, SimpleDateFormat sdf) {
        try {
            banco.adicionarUsuario(new Professor("Roberto", "1", "mat1", sdf.parse("14/05/1975"), "História"));
            banco.adicionarUsuario(new Professor("Jorge", "2", "mat2", sdf.parse("22/02/1981"), "Geografia"));
            banco.adicionarUsuario(new Professor("Matheus", "3", "mat3", sdf.parse("28/08/1987"), "Libras"));
            banco.adicionarUsuario(new Professor("Laura", "4", "mat4", sdf.parse("10/04/1965"), "Matemática"));
            banco.adicionarUsuario(new Professor("Felipe", "5", "mat5", sdf.parse("30/11/1989"), "IMD"));
        } catch (ParseException e) {
            // tratamento para a exceção de formação da data
            e.printStackTrace();
        }
    }
}
