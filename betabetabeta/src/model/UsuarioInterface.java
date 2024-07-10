package model;

import java.util.Date;

// gets e sets do UsuarioInterface
public interface UsuarioInterface {
    String getNome();
    void setNome(String nome);

    String getCpf();
    void setCpf(String cpf);

    String getMatricula();
    void setMatricula(String matricula);

    void setDataNascimento(Date dataNascimento);

    String getDataNascimentoFormatada();
}
