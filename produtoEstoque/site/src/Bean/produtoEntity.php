<?php

class produtoEntity{

private $id;
private $descricaoProduto;
private $codigoBarras;
private $unidade;
private $categoria;
private $marca;
private $modelo;
private $unidadeEstoque;
private $aplicacao;
private $detalhe;
private $ultimoCusto;
private $valorVenda;

public function getId(){
return $id;
}

public function getDescricaoProduto(){
return $descricaoProduto;
}

public function getCodigoBarras(){
return $codigoBarras;
}

public function getUnidade(){
return $unidade;
}

public function getCategoria(){
return $categoria;
}

public function getMarca(){
return $marca;
}

public function getModelo(){
return $modelo;
}

public function getUnidadeEstoque(){
return $unidadeEstoque;
}

public function getAplicacao(){
return $aplicacao;
}

public function getDetalhe(){
return $detalhe;
}

public function getUltimoCusto(){
return $ultimoCusto;
}

public function getValorVenda(){
return $valorVenda;
}

//Sets

public function setId($id){
return $this->id = $id;
}

public function setDescricaoProduto($descricaoProduto){
return $this->descricaoProduto = $descricaoProduto;
}

public function setCodigoBarras($codigoBarras){
return $this->codigoBarras = $codigoBarras;
}

public function setUnidade($unidade){
return $this->unidade = $unidade;
}

public function setCategoria($categoria){
return $this->categoria = $categoria;
}

public function setMarca($marca){
return $this->marca = $marca;
}

public function setModelo($modelo){
return $this->modelo = $modelo;
}

public function setUnidadeEstoque($unidadeEstoque){
return $this->unidadeEstoque = $unidadeEstoque;
}

public function setAplicacao($aplicacao){
return $this->aplicacao = $aplicacao;
}

public function setDetalhe($detalhe){
return $this->detalhe = $detalhe;
}

public function setUltimoCusto($ultimoCusto){
return $this->ultimoCusto = $ultimoCusto;
}

public function setValorVenda($valorVenda){
return $this->valorVenda = $valorVenda;
}

}

?>