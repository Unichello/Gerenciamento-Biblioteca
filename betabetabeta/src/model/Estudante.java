package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Estudante extends Usuario {
    // atributo de Estudante
    private String curso;
    // construtor que faz a inicialização dos atributos de estudante, utilizando os set e chama o construtor da classe pai Usuario
    public Estudante(String nome, String cpf, String matricula, Date dataNascimento, String curso) {
        super(nome, cpf, matricula, dataNascimento);
        this.setCurso(curso); // definindo o curso do estudante usando o set
    }
    // método get para o curso do estudante
    public String getCurso() {
        return curso;
    }
    // método set para o curso do estudante
    public void setCurso(String curso) {
        this.curso = curso;
    }
    // método toString onde ele retorna uma representação em forma de texto do estudante, incluindo o curso
    @Override
    public String toString() {
        return "Estudante{" + "curso:'" + getCurso() + '\'' + "} " + super.toString();
    }
    // método estático para cadastrar estudantes iniciais no banco de dados, caso não haja nenhum estudante cadastrado(primeira execução)
    public static void cadastrarEstudantesIniciais(BancoDAO banco, SimpleDateFormat sdf) {
        try {
            banco.adicionarUsuario(new Estudante("Matheus", "6", "mat6", sdf.parse("11/11/1992"), "BTI"));
            banco.adicionarUsuario(new Estudante("José", "7", "mat7", sdf.parse("19/03/1998"), "Geografia"));
            banco.adicionarUsuario(new Estudante("Maria", "8", "mat7", sdf.parse("05/06/1997"), "Geologia"));
            banco.adicionarUsuario(new Estudante("Joana", "9", "mat9", sdf.parse("25/02/1997"), "Matemática"));
            banco.adicionarUsuario(new Estudante("Eduardo", "10", "mat10", sdf.parse("30/08/1999"), "História"));
        } catch (ParseException e) {
            e.printStackTrace(); // tratamento de exceção em caso de erro na conversão da data
        }
    }
}
