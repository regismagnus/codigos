/*
 * Classe que faiz comunicao com o banco de dados para a tabela Carro
 */

package controleestoque.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import controleestoque.bean.produtoMovimentoEntity;
import controleestoque.util.conexao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author re
 */
public class produtoMovimentoDAO {
//Variaveis padroes para comunicacao com o banco
    private conexao banco;
    private Connection conexao;
    private Statement instrucaoSQL;
    private PreparedStatement prepare;
    private ResultSet resultado;

    private produtoMovimentoEntity produtoMovimento;
    produtoDAO produtoDAO;

    public produtoMovimentoDAO() {

      banco = new conexao();
      produtoMovimento = new produtoMovimentoEntity();
      produtoDAO = new produtoDAO();
    }

//Faz listagem dos produto por busca, retornando uma lista encontrada
    public List<produtoMovimentoEntity> listar(String busca){

        List<produtoMovimentoEntity> listAll = new ArrayList<produtoMovimentoEntity>();

        String query = "SELECT * FROM produtoMovimento";

        conexao = banco.conectarBd();

        query+=" where id="+busca;

        try {

            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){
                produtoMovimento = new produtoMovimentoEntity();

            produtoMovimento.setId(resultado.getInt("id"));
            produtoMovimento.setId_Produto(resultado.getInt("id_Produto"));
            produtoMovimento.setDia(resultado.getDate("dia"));
            produtoMovimento.setObservacao(resultado.getString("observacao"));
            produtoMovimento.setTipo(resultado.getInt("tipo"));
            produtoMovimento.setQuantidade(resultado.getInt("quantidade"));
            produtoMovimento.setPreco_unidade(resultado.getDouble("preco_Unidade"));

                listAll.add(produtoMovimento);
                produtoMovimento = null;

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
public List<produtoMovimentoEntity> listar(){

        List<produtoMovimentoEntity> listAll = new ArrayList<produtoMovimentoEntity>();

        String query = "SELECT * FROM produtoMovimento";

        conexao = banco.conectarBd();

        try {
            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){

                produtoMovimento = new produtoMovimentoEntity();

            produtoMovimento.setId(resultado.getInt("id"));
            produtoMovimento.setId_Produto(resultado.getInt("id_Produto"));
            produtoMovimento.setDia(resultado.getDate("dia"));
            produtoMovimento.setObservacao(resultado.getString("observacao"));
            produtoMovimento.setTipo(resultado.getInt("tipo"));
            produtoMovimento.setQuantidade(resultado.getInt("quantidade"));
            produtoMovimento.setPreco_unidade(resultado.getDouble("preco_Unidade"));

                listAll.add(produtoMovimento);

                produtoMovimento = null;
            }

        conexao.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return listAll;
    }

//Cria novo cadastro de produto
public boolean novo(produtoMovimentoEntity c){

    String query = "INSERT INTO produtoMovimento(id_Produto, dia, observacao, tipo," +
                   " quantidade, preco_Unidade) " +
                   "VALUES (?,?, ?, ?, ?," +
                   "(SELECT valorVenda FROM produto WHERE id=? ))";


    conexao = banco.conectarBd();
        try {
            prepare = conexao.prepareStatement(query);

            prepare.setInt(1, c.getId_Produto());
            prepare.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(c.getDia()));
            prepare.setString(3, c.getObservacao());
            prepare.setInt(4, c.getTipo());
            prepare.setInt(5, c.getQuantidade());
            prepare.setInt(6, c.getId_Produto());
            prepare.executeUpdate();
            prepare.close();
            conexao.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    return true;
}

public boolean remover(int id){

    String query = "DELETE FROM produtoMovimento WHERE id_Produto= ?";

    conexao = banco.conectarBd();
        try {

            prepare = conexao.prepareStatement(query);

            prepare.setInt(1, id);
            prepare.executeUpdate();

            prepare.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    return true;
}

public List<produtoMovimentoEntity> relatorio(Integer tipo, Integer idProduto, String inicio, String fim){

        List<produtoMovimentoEntity> listAll = new ArrayList<produtoMovimentoEntity>();

        String query = "SELECT *, (SELECT descricaoProduto FROM produto WHERE id=id_produto) AS descricao FROM produtoMovimento";

        conexao = banco.conectarBd();

        query+=" WHERE dia>='" + inicio + "' AND dia<='" + fim +
                "' AND tipo=" + tipo;

        if(idProduto!=null){
            query+= " AND id_Produto=" + idProduto + " ORDER BY dia";
        }else{
            query+= " ORDER BY id_produto";
        }
        try {

            instrucaoSQL = conexao.prepareStatement(query);
            resultado = instrucaoSQL.executeQuery(query);

            while(resultado.next()){
                produtoMovimento = new produtoMovimentoEntity();

            produtoMovimento.setId(resultado.getInt("id"));
            produtoMovimento.setId_Produto(resultado.getInt("id_Produto"));
            produtoMovimento.setDia(resultado.getDate("dia"));
            produtoMovimento.setObservacao(resultado.getString("observacao"));
            produtoMovimento.setTipo(resultado.getInt("tipo"));
            produtoMovimento.setQuantidade(resultado.getInt("quantidade"));
            produtoMovimento.setPreco_unidade(resultado.getDouble("preco_Unidade"));
            produtoMovimento.setDescricao(resultado.getString("descricao"));

                listAll.add(produtoMovimento);
                produtoMovimento = null;

            }

        conexao.close();
        resultado.close();
        instrucaoSQL.close();

        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listAll;

}

}
