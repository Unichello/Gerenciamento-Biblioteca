package util;

import model.BancoDAO;

import java.io.*;

public class ArquivoUtil {
    // salvando os dados do banco em um arquivo
    public static void salvarDados(BancoDAO banco) {
        // utilizando try catch garantindo que ObjectOutputStream seja fechado de forma correta
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("banco.dat"))) {
            oos.writeObject(banco); // escreve o objeto banco no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // imprime a StackTrace em caso de exceção
        }
    }
    // carregando os dados do banco a partir de um arquivo
    public static BancoDAO carregarDados() {
        File arquivo = new File("banco.dat");
        // verificando se o arquivo não existe
        if (!arquivo.exists()) {
            BancoDAO banco = BancoDAO.getInstance(); // criando uma nova instância do banco
            salvarDados(banco); // salvando a nova instância no arquivo
            return banco; // retornando a nova instância
        }
        // try catch garantindo que ObjectOutputStream seja fechado de forma correta
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (BancoDAO) ois.readObject(); // lendo e retornando o objeto banco do arquivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // imprime o StackTrace em caso de exceção
            return BancoDAO.getInstance(); // retorna uma instância nova do banco em caso de alguma exceção
        }
    }
}
