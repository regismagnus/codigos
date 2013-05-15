/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controleestoque.bean;

/**
 *
 * @author regis
 */
public class produtoEntity {

private Integer id;
private String descricaoProduto;
private String codigoBarras;
private String unidade;
private String categoria;
private String marca;
private String modelo;
private Integer unidadeEstoque;
private String aplicacao;
private String detalhe;
private Double ultimoCusto;
private Double valorVenda;

    public produtoEntity() {

        descricaoProduto = null;
        codigoBarras = null;
        unidade = null;
        categoria = null;
        marca = null;
        modelo = null;
        unidadeEstoque = null;
        aplicacao = null;
        detalhe = null;
        ultimoCusto = null;
        valorVenda = null;

    }

    public String getAplicacao() {
        return aplicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public Integer getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Double getUltimoCusto() {
        return ultimoCusto;
    }

    public String getUnidade() {
        return unidade;
    }

    public Integer getUnidadeEstoque() {
        return unidadeEstoque;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setUltimoCusto(Double ultimoCusto) {
        this.ultimoCusto = ultimoCusto;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setUnidadeEstoque(Integer unidadeEstoque) {
        this.unidadeEstoque = unidadeEstoque;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final produtoEntity other = (produtoEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
      
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }






}
