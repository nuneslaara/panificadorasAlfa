
package DAO;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DadosFornecedor;

public class fornecedorDao {
    public void salvar(DadosFornecedor d)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO fornecedor ( razao_social) VALUES(?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, d.getRazao_social());
        stmt.execute();
        stmt.close();
        
    }
    public void excluir(DadosFornecedor d)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, d.getId_fornecedor());
        stmt.execute();
        stmt.close();
    }
    public void editar(DadosFornecedor d)throws ClassNotFoundException,SQLException{
        String sql= "UPDATE fornecedor SET Razao_social = ? WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString (1, d.getRazao_social());
        stmt.setInt(2, d.getId_fornecedor());    
        stmt.execute();
        stmt.close();
}
    public DadosFornecedor pesquisar(DadosFornecedor d)throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, d.getId_fornecedor());
        
        ResultSet rs = stmt.executeQuery();
        DadosFornecedor retorno = null;
           if(rs.next()){
            retorno = new DadosFornecedor();
            retorno.setId_fornecedor(rs.getInt("Id_fornecedor"));
            retorno.setRazao_social(rs.getString("Razao_social"));
        }
        return retorno;

}
     public ArrayList<DadosFornecedor> lista()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<DadosFornecedor> lista = new ArrayList<DadosFornecedor>();
        
        while(rs.next()){
            DadosFornecedor e = new DadosFornecedor();
            e.setId_fornecedor(rs.getInt("id_fornecedor"));
            e.setRazao_social(rs.getString("razao_social"));            
            lista.add(e);
        }
        return lista;
    }
}
