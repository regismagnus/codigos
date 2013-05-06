<?php
class conexao{

private $local = "localhost";
private $usuario = "root";//"root";
private $senha = "123";//"123";
private $dataBase = "comerce";
private $conexao;

public function conectar(){
//Efetuando conexao
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
//Fechar conexão
public function close(){
mysql_close();
}


}
