package br.com.panificadora.bean;

import br.com.panificadora.model.Fornecedor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.panificadora.dao.FornecedorDAO;
import br.com.panificadora.util.JSFUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "MBFornecedor")
//escopo:session(criado quando o tomcat é instanciado e finalizado quando o tomcat)//
//escopo:request(a cada clique é instanciado)//
//escopo:view(só existe enquanto estiver manipulando a tela dele)//
@ViewScoped
public class FornecedorBean {

    private ArrayList<Fornecedor> itens;
    private Fornecedor fornecedor;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    

    public ArrayList<Fornecedor> getItens() {
        return itens;
    }
 
    public void setItens(ArrayList<Fornecedor> itens) {
        this.itens = itens;
    }
     @PostConstruct //metodo chamado antes da tag desenhada
    public void prepararPesquisa() {
        
            FornecedorDAO dao = new FornecedorDAO();
        try {
            itens = dao.lista();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    public void prepararFornecedor(){
       fornecedor = new Fornecedor();
    }
   
    public void novoFornecedor(){
       
        try{ 
            FornecedorDAO dao = new FornecedorDAO();
            dao.salvar(fornecedor);
                itens= dao.lista();
                JSFUtil.addMsgSucesso("Fornecedor salvo com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addMsgErro(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    
}
     public void excluiFornecedor(){
        FornecedorDAO dao = new FornecedorDAO();
        try{ 
            dao.excluir(fornecedor);
                itens= dao.lista();
                JSFUtil.addMsgSucesso("Fornecedor excluído com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addMsgErro(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    
}
     public void editaFornecedor(){
      FornecedorDAO dao = new FornecedorDAO();
        try{
            dao.editar(fornecedor);
                itens = dao.lista();
                JSFUtil.addMsgSucesso("Fornecedor alterado com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addMsgErro(ex.getMessage());
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
       
    


   
}

