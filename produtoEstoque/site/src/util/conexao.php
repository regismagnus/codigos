<?php
class conexao{

private $local = "localhost";
private $usuario;//"root";
private $senha;//"123";
private $dataBase = "mysql";
private $conexao;

public function conectar($user,$password){

$this->usuario = $user;
$this->senha = $password;

$conexao = mysql_connect($this->local,$this->usuario,$this->senha);
	if(!$conexao){
		echo "Erro ao se conectar";
		exit;
	}	

$banco = mysql_select_db($this->dataBase);
	if(!$banco){
		echo "O Banco de dados não foi encontrado";
		exit;
	}

}


}