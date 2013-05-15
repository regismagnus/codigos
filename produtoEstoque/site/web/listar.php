<?php

require("../src/util/conexao.php");
//require("../src/bean/produtoEntity.php");

@ $user = $_GET['user'];
@ $password = $_GET['password'];
@ $busca = $_GET["busca"];
@ $tipo = $_GET["tipo"];
@ $pag = $_GET["pag"] * 20;

$conect = new conexao();
$conect->conectar($user,$password);

$query = "SELECT * FROM produto";
$query2 = "SELECT COUNT(id) AS n FROM produto";

if($busca){
	if($tipo){
		if($tipo == 1){
			$query = "SELECT * FROM produto WHERE descricaoProduto like '%$busca%' ORDER BY descricaoProduto LIMIT $pag ,20";
			$query2 = "SELECT COUNT(id) AS n FROM produto WHERE descricaoProduto like '%$busca%'";
		}else{
			$query ="SELECT * FROM produto WHERE id=$busca";
			$query2 = "SELECT COUNT(id) AS n FROM produto WHERE id=$busca";
		}
	}
}else{
	$query = "$query LIMIT $pag ,20";

}
$result = mysql_query($query);
$result2 = mysql_query($query2);

?>
<?xml version="1.0" encoding="UTF-8"?>

<produtos>
<?php 
if($result){
$row2=mysql_fetch_array($result2);

while($row=mysql_fetch_array($result)){ ?>
<produto>
	<id><?php echo $row['id'] ?></id>
	<descricao><?php echo $row['descricaoProduto'] ?></descricao>
	<codigoBarras><?php echo $row['codigoBarras'] ?></codigoBarras>
	<unidade><?php echo $row['unidade'] ?></unidade>
	<categoria><?php echo $row['categoria'] ?></categoria>
	<marca><?php echo $row['marca'] ?></marca>
	<modelo><?php echo $row['modelo'] ?></modelo>
	<unidadeEstoque><?php echo $row['unidadeEstoque'] ?></unidadeEstoque>
	<aplicacao><?php echo $row['aplicacao'] ?></aplicacao>
	<detalhe><?php echo $row['detalhe'] ?></detalhe>
	<ultimoCusto><?php echo $row['ultimoCusto'] ?></ultimoCusto>
	<valorVenda><?php echo $row['valorVenda'] ?></valorVenda>
	<n><?php echo $row2['n'] ?></n>
</produto>

<?php } }?>

</produtos>