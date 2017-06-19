/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
 * @author Paulo Vitor
 */
public class PedidoDao {

    private Connection conexao;

    public PedidoDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void DeletaPedido(Pedido p) {
        String sql = "DELETE FROM pedido WHERE id=?";
        try {
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.execute();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Pedido> getList() {
        List<Pedido> pedido = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setCliente(rs.getString("cliente"));
                ped.setProduto(rs.getString("produto"));
                ped.setQuantidade(rs.getInt("quantidade"));
                ped.setPreco(rs.getDouble("preco"));
                ped.setEndereco(rs.getString("endereco"));
                ped.setId(rs.getLong("id"));
                pedido.add(ped);
            }
            stmt.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }   
}
