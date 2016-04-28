
package br.com.panificadora.bean;

import br.com.panificadora.model.Produto;
import br.com.panificadora.dao.ProdutoDAO;
import br.com.panificadora.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Lara
 */
@ManagedBean (name = "MBProduto")
@ViewScoped
public class ProdutoBean {
     private ArrayList<Produto> itens;
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto=produto;
    }
    

    public ArrayList<Produto> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Produto> itens) {
        this.itens = itens;
    }
    @PostConstruct //metodo chamado antes da tag desenhada
    public void prepararPesquisa() {        
            ProdutoDAO dao = new ProdutoDAO();       
                try {
                    itens = dao.lista();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    public void prepararProduto(){
        produto= new Produto();
    }
    
    public void novoProduto(){
        ProdutoDAO dao = new ProdutoDAO();            
            try {
                dao.salvar(produto);
                itens= dao.lista();
                JSFUtil.addMsgSucesso("Produto salvo com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addMsgErro(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
}
    
     public void excluiProduto(){
         ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.excluir(produto);
            itens = dao.lista();
            JSFUtil.addMsgSucesso("Produto excluído com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JSFUtil.addMsgErro(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }  
}
     
     public void editaProduto(){
      ProdutoDAO dao = new ProdutoDAO();
        try {
            dao.editar(produto);
            itens = dao.lista();
            JSFUtil.addMsgSucesso("Produto alterado com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
           JSFUtil.addMsgErro(ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.addMsgErro(e.getMessage());
        }
        }
}
