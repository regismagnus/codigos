/*
 * Classe que faiz comunicao com o banco de dados para a tabela Carro
 */

package controleestoque.crud;

import controleestoque.bean.produtoMovimentoEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import controleestoque.bean.produtoEntity;
import controleestoque.util.conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author re
 */
public class produtoDAO {
//Variaveis padroes para comunicacao com o banco
    private conexao banco;
    private Connection conexao;
    private Statement instrucaoSQL;
    private PreparedStatement prepare;
    private ResultSet resultado;

    private produtoEntity produto;

    public produtoDAO() {

      banco = new conexao();
      produto = new produtoEntity();

    }

//Faz listagem dos produto por busca, retornando uma lista encontrada
    public List<produtoEntity> listar(String busca){

        List<produtoEntity> listAll = new ArrayList<produtoEntity>();

        String query = "SELECT * FROM produto";

        conexao = banco.conectarBd();

        query+=" where id="+busca;

        try {

            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){
                produto = new produtoEntity();
                
            produto.setId(resultado.getInt("id"));
            produto.setDescricaoProduto(resultado.getString("descricaoProduto"));
            produto.setCodigoBarras(resultado.getString("codigoBarras"));
            produto.setUnidade(resultado.getString("unidade"));
            produto.setCategoria(resultado.getString("categoria"));
            produto.setMarca(resultado.getString("marca"));
            produto.setModelo(resultado.getString("modelo"));
            produto.setUnidadeEstoque(resultado.getInt("unidadeEstoque"));
            produto.setAplicacao(resultado.getString("aplicacao"));
            produto.setDetalhe(resultado.getString("detalhe"));
            produto.setUltimoCusto(resultado.getDouble("ultimoCusto"));
            produto.setValorVenda(resultado.getDouble( "valorVenda" ));


                listAll.add(produto);
                produto = null;
                
            }
        
        conexao.close();
        resultado.close();
        instrucaoSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listAll;
    }
//Lista todos os carros
public List<produtoEntity> listar(){

        List<produtoEntity> listAll = new ArrayList<produtoEntity>();

        String query = "SELECT * FROM produto";

        conexao = banco.conectarBd();

        try {
            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){

                produto = new produtoEntity();

            produto.setId(resultado.getInt("id"));
            produto.setDescricaoProduto(resultado.getString("descricaoProduto"));
            produto.setCodigoBarras(resultado.getString("codigoBarras"));
            produto.setUnidade(resultado.getString("unidade"));
            produto.setCategoria(resultado.getString("categoria"));
            produto.setMarca(resultado.getString("marca"));
            produto.setModelo(resultado.getString("modelo"));
            produto.setUnidadeEstoque(resultado.getInt("unidadeEstoque"));
            produto.setAplicacao(resultado.getString("aplicacao"));
            produto.setDetalhe(resultado.getString("detalhe"));
            produto.setUltimoCusto(resultado.getDouble("ultimoCusto"));
            produto.setValorVenda(resultado.getDouble( "valorVenda" ));

                listAll.add(produto);

                produto = null;
            }
        
        conexao.close();


        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return listAll;
    }

//remove o cadastro por Id do produto recebe String Placa para mandar menssagem avisando qual placa foi removida
public boolean remover(int id){

    produtoMovimentoDAO produtoMovimento = new produtoMovimentoDAO();

    produtoMovimento.remover(id);
    
    String query = "DELETE FROM produto WHERE id= ?";
    
    conexao = banco.conectarBd();
        try {
            
            prepare = conexao.prepareStatement(query);

            prepare.setInt(1, id);
            prepare.executeUpdate();

            
            JOptionPane.showMessageDialog(null, "Produto codigo " + id + " removido");

            prepare.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    return true;
}

//Recebe a entidade produto para fazer alteraçoes
public boolean atualizar(produtoEntity c){

    String query = "UPDATE produto set descricaoProduto=?, codigoBarras=?," +
                   "unidade=?, categoria=?, marca=?, modelo=?, unidadeEstoque=?," +
                   "aplicacao=?, detalhe=?, ultimoCusto=?, valorVenda=? where id=?";

    conexao = banco.conectarBd();
        try {
            prepare = conexao.prepareStatement(query);

            prepare.setString(1, c.getDescricaoProduto());
            prepare.setString(2, c.getCodigoBarras());
            prepare.setString(3, c.getUnidade());
            prepare.setString(4, c.getCategoria());
            prepare.setString(5, c.getMarca());
            prepare.setString(6, c.getModelo());
            prepare.setInt(7, c.getUnidadeEstoque());
            prepare.setString(8, c.getAplicacao());
            prepare.setString(9, c.getDetalhe());
            prepare.setDouble(10, c.getUltimoCusto());
            prepare.setDouble(11, c.getValorVenda());
            prepare.setInt(12, c.getId());

            prepare.executeUpdate();
            prepare.close();
            conexao.close();

             JOptionPane.showMessageDialog(null, "Produto Código " + c.getId() + " atualizado");
        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    return true;
}

public boolean atualizarEstoque(String id, String valor){

    String query = "UPDATE produto set unidadeEstoque=unidadeEstoque";

    query+=valor + " where id=" +id;

    conexao = banco.conectarBd();
        try {
            prepare = conexao.prepareStatement(query);


            prepare.executeUpdate();
            prepare.close();
            conexao.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    return true;
}

//Cria novo cadastro de produto
public boolean novo(produtoEntity c){

    String query = "INSERT INTO produto(descricaoProduto, codigoBarras, unidade," +
                   "categoria, marca, modelo, unidadeEstoque, aplicacao, detalhe," +
                   "ultimoCusto, valorVenda) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    conexao = banco.conectarBd();
        try {
            prepare = conexao.prepareStatement(query);

            prepare.setString(1, c.getDescricaoProduto());
            prepare.setString(2, c.getCodigoBarras());
            prepare.setString(3, c.getUnidade());
            prepare.setString(4, c.getCategoria());
            prepare.setString(5, c.getMarca());
            prepare.setString(6, c.getModelo());
            prepare.setInt(7, c.getUnidadeEstoque());
            prepare.setString(8, c.getAplicacao());
            prepare.setString(9, c.getDetalhe());
            prepare.setDouble(10, c.getUltimoCusto());
            prepare.setDouble(11, c.getValorVenda());

            prepare.executeUpdate();
            prepare.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado.");

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    return true;
}

    public List<produtoEntity> listarDescricao(String busca) {
         List<produtoEntity> listAll = new ArrayList<produtoEntity>();

        String query = "SELECT * FROM produto";

        conexao = banco.conectarBd();

        query+=" where descricaoProduto like '%"+busca+"%' ORDER BY descricaoProduto";

        try {
            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){
                produto = new produtoEntity();

            produto.setId(resultado.getInt("id"));
            produto.setDescricaoProduto(resultado.getString("descricaoProduto"));
            produto.setCodigoBarras(resultado.getString("codigoBarras"));
            produto.setUnidade(resultado.getString("unidade"));
            produto.setCategoria(resultado.getString("categoria"));
            produto.setMarca(resultado.getString("marca"));
            produto.setModelo(resultado.getString("modelo"));
            produto.setUnidadeEstoque(resultado.getInt("unidadeEstoque"));
            produto.setAplicacao(resultado.getString("aplicacao"));
            produto.setDetalhe(resultado.getString("detalhe"));
            produto.setUltimoCusto(resultado.getDouble("ultimoCusto"));
            produto.setValorVenda(resultado.getDouble( "valorVenda" ));


                listAll.add(produto);
                produto = null;
            }

        conexao.close();


        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listAll;
    }

    public boolean verificarQuantidade(String id, Integer quantidade) {

        String query = "SELECT UnidadeEstoque FROM produto";

        conexao = banco.conectarBd();

        query+=" where id=" + id ;

        try {

            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

                produto = new produtoEntity();
            
            resultado.next();

            produto.setUnidadeEstoque(resultado.getInt("unidadeEstoque"));

        conexao.close();
        resultado.close();
        instrucaoSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(produto.getUnidadeEstoque()< quantidade){
            return true;
        }

        return false;
    }

}
