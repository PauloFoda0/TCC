package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    //Cria Conexao

    public Connection getConnection() {
        try {
            String usuario = "postgres"; //Nome do Usuario do BD
            String senha = "paulo"; //Senha do BD
            String nomeBanco = "produtos"; //Nome do Banco de Dados
            String EnderecoServer = "localhost"; //Localhost do Server

            return DriverManager.getConnection("jdbc:postgresql://" + EnderecoServer + "/" + nomeBanco, usuario, senha);
        } catch (SQLException e) {
            System.out.println("ERRO, não abre conexao");
            throw new RuntimeException(e);
        }
    }
}
