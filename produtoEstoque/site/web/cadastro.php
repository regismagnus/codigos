<?php

require("../src/util/conexao.php");
require("../src/bean/cadastroEntity.php");

$cadastro = new cadastro();

@ $user = $_GET['user'];
@ $password = $_GET['password'];
@ $cadastro->setnome($_GET['nome']);
@ $cadastro->setDataNasc($_GET['data']);
@ $cadastro->setEndereco($_GET['endereco']);
@ $cadastro->setObs($_GET['obs']);

$conect = new conexao();
$conect->conectar($user,$password);

$query = "INSERT INTO cliente (nome, dataNasc, endereco, obs) VALUES('".$cadastro->getNome()."','".$cadastro->getDataNasc()."','".$cadastro->getEndereco()."','".$cadastro->getObs()."')";


$insert = mysql_query($query);

if($insert===FALSE)
echo "Erro ao inserir dados... " .mysql_error();

?>