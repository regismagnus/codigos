<?php
class conexao{

private $local = "localhost";
private $usuario = "root";//"root";
private $senha = "123";//"123";
private $dataBase = "blog";
private $conexao;

public function conectar(){

$conexao = mysql_connect($this->local,$this->usuario,$this->senha);
	if(!$conexao){
		echo "Erro ao se conectar";
		exit;
	}	

$banco = mysql_select_db($this->dataBase);
	if(!$banco){
		echo "Desculpe, estavos com problemas, em acessar nosso banco de dados, retorne mais tarde. Obrigado";
		exit;
	}

}

public function close(){
mysql_close();
}


}
