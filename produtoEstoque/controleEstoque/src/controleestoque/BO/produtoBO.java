/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controleestoque.BO;

import controleestoque.bean.produtoEntity;
import javax.swing.JOptionPane;

/**
 *
 * @author re
 */
public class produtoBO {

    public boolean validar(produtoEntity produtoEntity) {

        String texto = "";

        
        if(produtoEntity.getDescricaoProduto().equalsIgnoreCase("")){
             texto += "- Descrição do Produto preenchimento obrigatorio \n";
        }
        if(produtoEntity.getDescricaoProduto().length()>50){
             texto += "- Descrição do Produto muito grande. Máximo 50 caracteres. \n";
        }
        if(produtoEntity.getCodigoBarras().equalsIgnoreCase("")){
             texto += "- Código de Barras preenchimento obrigatorio \n";
        }
        if(produtoEntity.getCodigoBarras().length() > 50){
             texto += "- Código de Barras muito grande. Máximo 50 caracteres. \n";
        }
        if(produtoEntity.getUnidade().equalsIgnoreCase("")){
             texto += "- Unidade preenchimento obrigatorio \n";
        }
        if(produtoEntity.getUnidade().length() > 10){
             texto += "- Unidade muito grande. Máximo 50 caracteres. \n";
        }
        if(produtoEntity.getMarca().equalsIgnoreCase("")){
             texto += "- Marca preenchimento obrigatorio \n";
        }
        if(produtoEntity.getMarca().length() > 30){
             texto += "- Marca muito grande. Máximo 50 caracteres. \n";
        }
        if(produtoEntity.getUnidadeEstoque() == null){
              texto += "- Unidade Estoque preenchimento obrigatorio \n";
        }
        if(produtoEntity.getUnidadeEstoque() < 0){
            texto += "- Unidade valor não pode ser negativo \n";
        }

        if(texto.equalsIgnoreCase("")){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, texto, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }

}
