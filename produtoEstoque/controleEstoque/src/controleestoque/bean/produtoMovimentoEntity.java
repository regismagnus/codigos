/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controleestoque.bean;

import java.util.Date;

/**
 *
 * @author re
 */
public class produtoMovimentoEntity {

    private Integer id;
    private Integer id_Produto;
    private Date dia;
    private String observacao;
    private Integer tipo;
    private Integer quantidade;
    private Double preco_unidade;
    private String descricao;

    public produtoMovimentoEntity() {
        this.id = null;
        this.id_Produto = null;
        this.dia = null;
        this.observacao = null;
        this.tipo = null;
        this.quantidade = null;
        this.preco_unidade = null;
        this.descricao = null;
    }

    public produtoMovimentoEntity(Integer id, Integer id_Produto, Date dia, String observacao, Integer tipo, Integer quantidade, Double preco_unidade) {
        this.id = id;
        this.id_Produto = id_Produto;
        this.dia = dia;
        this.observacao = observacao;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco_unidade = preco_unidade;
    }

    public Date getDia() {
        return dia;
    }

    public Integer getId() {
        return id;
    }

    public Integer getId_Produto() {
        return id_Produto;
    }

    public String getObservacao() {
        return observacao;
    }

    public Double getPreco_unidade() {
        return preco_unidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId_Produto(Integer id_Produto) {
        this.id_Produto = id_Produto;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setPreco_unidade(Double preco_unidade) {
        this.preco_unidade = preco_unidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


}
