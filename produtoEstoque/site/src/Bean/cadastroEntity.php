<?php

class cadastro{

private $nome;
private $dataNasc;
private $endereco;
private $obs;

public function getNome(){
return $this->nome;
}

public function getDataNasc(){
return $this->dataNasc;
}

public function getEndereco(){
return $this->endereco;
}

public function getObs(){
return $this->obs;
}

public function setNome($nome){
$this->nome = $nome;
}

public function setDataNasc($data){
$this->dataNasc = $data;
}

public function setEndereco($endereco){
$this->endereco = $endereco;
}

public function setObs($obs){
$this->obs = $obs;
}


}