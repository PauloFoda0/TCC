
package DAO;

import JDBC.ConnectionFactory;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Vitor
 */
public class ProdutoDao {

    private Connection conexao;

    public ProdutoDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void InsereProduto(Produto prod) {
        String sql = "INSERT INTO produto(tipo, litros, preco, foto) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setString(1, prod.getTipo());
            stmt.setString(2, prod.getLitros());
            stmt.setDouble(3, prod.getPreco());
            stmt.setString(4, prod.getFoto());
            
            stmt.execute();
            conexao.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    public void DeletaProduto(Produto p){
        String sql ="DELETE FROM produto WHERE id=?";       
        try {
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            
            stmt.execute();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Produto> getList(){
        List<Produto> produto = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto prod = new Produto();
                prod.setTipo(rs.getString("tipo"));
                prod.setLitros(rs.getString("litros"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setFoto(rs.getString("foto"));
                prod.setId(rs.getLong("id"));
                produto.add(prod);
            }   
            stmt.close();
            rs.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException();       
        }
        return produto;
    }
}
