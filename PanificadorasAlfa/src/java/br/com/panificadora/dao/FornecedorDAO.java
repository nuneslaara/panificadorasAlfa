
package br.com.panificadora.dao;

import br.com.panificadora.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.panificadora.model.Fornecedor;

public class FornecedorDAO {
    public void salvar(Fornecedor d)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO fornecedor ( razao_social) VALUES(?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, d.getRazao_social());
        stmt.execute();
        stmt.close();
        
    }
    public void excluir(Fornecedor d)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, d.getId_fornecedor());
        stmt.execute();
        stmt.close();
    }
    public void editar(Fornecedor d)throws ClassNotFoundException,SQLException{
        String sql= "UPDATE fornecedor SET Razao_social = ? WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString (1, d.getRazao_social());
        stmt.setInt(2, d.getId_fornecedor());    
        stmt.execute();
        stmt.close();
}
    public Fornecedor pesquisar(Fornecedor d)throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, d.getId_fornecedor());
        
        ResultSet rs = stmt.executeQuery();
        Fornecedor retorno = null;
           if(rs.next()){
            retorno = new Fornecedor();
            retorno.setId_fornecedor(rs.getInt("Id_fornecedor"));
            retorno.setRazao_social(rs.getString("Razao_social"));
        }
        return retorno;

}
     public ArrayList<Fornecedor> lista()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
        
        while(rs.next()){
            Fornecedor e = new Fornecedor();
            e.setId_fornecedor(rs.getInt("id_fornecedor"));
            e.setRazao_social(rs.getString("razao_social"));            
            lista.add(e);
        }
        return lista;
    }
}
