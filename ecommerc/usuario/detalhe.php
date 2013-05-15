
<?php

@ $id = $_GET['id'];

//require("../../src/util/conexao.php");

$conect = new conexao();
$conect->conectar();

$query = "SELECT * FROM siteproduto WHERE id=".$id;
$result = mysql_query($query);

$row=mysql_fetch_array($result);
?>

<h1><?php echo $row['nome'] ?></h1>
Marca: <?php echo $row['marca'] ?> <br />
Cód.: <?php echo $row['id'] ?>  <br />
<img src="../../public/imagens/<?php echo $row['foto'] ?>" class="alignleft" width="300" height="300" border="0" />
<?php
if($row['estoque']>=1){ ?>
<form method="GET" action="index.php">
<input type="hidden" name="id" value="<?php echo $row['id'] ?>" />
<input type="hidden" name="pagina" value="carrinho" />
<input type="hidden" name="acao" value="add" />
<div id="comprar">
<input type="submit" value="Add ao carrinho" class="button" />
</form>
<br />
<div class="preco"><?php echo "R$ ".number_format($row['preco'],2,",",".") ?></div>
</div>
<?php }else{
   echo "<div id='comprar'>Produto em falta</div>";
}
?>
<br class="clearleft" /><br />
<h2>Descrição</h2>
<?php echo nl2br($row['descricao']) ?> <br />
<br />
<h2>Caracteristica</h2>
<?php echo nl2br($row['caracteristica']) ?> <br />

<?php

?>
