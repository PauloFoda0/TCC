/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Vitor
 */
public class UsuarioDao {

    private Connection conexao;

    public UsuarioDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void InsereUsuario(Usuario user) {
        String sql = "INSERT into usuario(login, senha, foto) values (?, ?, ?)";
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getSenha());
            statement.setString(3, user.getFoto());
            statement.execute();
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Usuario> getLista() {
        List<Usuario> usuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setFoto(rs.getString("foto"));
                user.setId(Long.getLong("id"));
                usuario.add(user);
            }   
            stmt.close();
            rs.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException();       
        }
        return usuario;
    }
}
