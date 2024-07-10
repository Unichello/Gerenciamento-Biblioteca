package model;

import java.util.Date;

public class Bibliotecario extends Usuario {
    // atributos de login e senha
    private String login;
    private String senha;
    // construtor que faz a inicialização dos atributos herdados de Usuario e define o login e a senha default do bibliotecario
    public Bibliotecario(String nome, String cpf, String matricula, Date dataNascimento) {
        super(nome, cpf, matricula, dataNascimento);
        this.setLogin("adm");
        this.setSenha("123");
    }
    // método get para o login do bibliotecario
    public String getLogin() {
        return login;
    }
    // método set para o login do bibliotecario
    public void setLogin(String login) {
        this.login = login;
    }
    // método get para a senha do bibliotecario
    public String getSenha() {
        return senha;
    }
    // método set para a senha do bibliotecario
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // método toString onde ele retorna uma representação em forma de texto do bibliotecario, usando os métodos get, onde inclui login e senha do bibliotecario
    @Override
    public String toString() {
        return "Bibliotecario{" + "login='" + getLogin() + '\'' + ", senha='" + getSenha() + '\'' + "} " + super.toString();
    }

    // método estático que verifica se há um bibliotecário no banco de dados e cria um se não houver(primeira execução)
    public static void verificarOuCriarBibliotecario(BancoDAO banco) {
        // verificando se há ou não um usuario do tipo bibliotecario no banco de dados
        if (banco.getUsuarios().stream().noneMatch(u -> u instanceof Bibliotecario)) {
            // criando um novo bibliotecario baseado nas informações default, caso não existe um
            Bibliotecario bibliotecario = new Bibliotecario("administrador", "0", "adm0", new Date());
            banco.adicionarUsuario(bibliotecario); // adiciona o bibliotecario no banco de dados
        }
    }
}
