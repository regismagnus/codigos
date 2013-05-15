<?php

require("../src/util/conexao.php");
//require("../src/bean/produtoEntity.php");

@ $user = $_GET['user'];
@ $password = $_GET['password'];
@ $busca = $_GET["busca"];
@ $pag = $_GET["pag"] * 20;

$conect = new conexao();
$conect->conectar($user,$password);

$query = "SELECT * FROM cliente";
$query2 = "SELECT COUNT(id) AS n FROM cliente";

if($busca){
	$query = "SELECT * FROM cliente WHERE nome like '%$busca%' ORDER BY nome LIMIT $pag ,20";
	$query2 = "SELECT COUNT(id) AS n FROM cliente WHERE nome like '%$busca%'";
}else{
	$query = "$query LIMIT $pag ,20";

}
$result = mysql_query($query);
$result2 = mysql_query($query2);

?>
<?xml version="1.0" encoding="UTF-8"?>

<clientes>
<?php 
if($result){
$row2=mysql_fetch_array($result2);

while($row=mysql_fetch_array($result)){ ?>
<cliente>
	<id><?php echo $row['id'] ?></id>
	<nome><?php echo $row['nome'] ?></nome>
	<n><?php echo $row2['n'] ?></n>
</cliente>

<?php } }?>

</clientes>