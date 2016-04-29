
package br.com.panificadora.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.panificadora.model.Produto;
import br.com.panificadora.factory.ConnectionFactory;


public class ProdutoDAO {
     public void salvar(Produto d)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO produto (nome,qtd,preco,cod_fornecedor) VALUES(?,?,?,?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, d.getNome());
        stmt.setInt(2, d.getQtd());
        stmt.setFloat(3, d.getPreco());
        stmt.setInt(4, d.getCod_fornecedor());
        stmt.execute();
        stmt.close();
   }
        
    
public void excluir(Produto d) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, d.getId_produto());
        stmt.execute();
        stmt.close();
    }
 public void editar(Produto d)throws ClassNotFoundException,SQLException{
        String sql= "UPDATE produto SET nome = ?, qtd = ?, preco = ?,cod_fornecedor=? WHERE id_produto = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, d.getNome());
        stmt.setInt(2, d.getQtd());
        stmt.setFloat(3, d.getPreco());
        stmt.setInt(4, d.getCod_fornecedor());
        stmt.setInt(5, d.getId_produto());
        stmt.execute();
        stmt.close();
}
 public ArrayList<Produto> lista()throws SQLException, ClassNotFoundException{
        String sql = "select produto.id_produto, produto.nome, produto.qtd, produto.preco,"
                + " fornecedor.razao_social from produto "
                + "inner join fornecedor on fornecedor.id_fornecedor = produto.cod_fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Produto> lista = new ArrayList<Produto>();
        
        while(rs.next()){
            Produto e = new Produto();
            e.setId_produto(rs.getInt("id_produto"));
            e.setNome(rs.getString("nome"));
            e.setQtd(rs.getInt("qtd"));
            e.setPreco(rs.getFloat("preco"));
            e.setRazao_social(rs.getString("razao_social"));
            lista.add(e);
        }
        return lista;
    }
}
