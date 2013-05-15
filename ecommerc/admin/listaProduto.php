
<?php

@ $permissao = $_SESSION["permissao"];

if($permissao!=1){
header("Location: ../usuario/index.php");
}


require("../../src/util/conexao.php");

$conect = new conexao();
$conect->conectar();

@ $id = $_POST['id'];
@ $busca = $_POST['busca'];

if($id){
$query = "DELETE FROM siteproduto WHERE id=".$id;
$result = mysql_query($query);
if($result){
	echo "Produto excluido com sucesso";
}else{
	echo "O produto não pode ser excluido";
}
	
}
if($busca){
 $query = "SELECT * FROM siteProduto WHERE nome like '%".$busca."%'";
}else{
$query = "SELECT * FROM siteProduto";
}
$query = $query." ORDER BY nome";
$result = mysql_query($query);

if($result){
?>
<table border="0" width="100%">
<tr><td width="50">Código</td>
    <td width="550">Produto</td>
    <td width="80">Marca</td>
    <td width="100">Quant. Estoque</td>
</tr>
 <?php
$i = 0;
	while($row=mysql_fetch_array($result)){ 

?>


<tr bgcolor=<?php if(($i%2)>0){echo "#FFFFFF";}else{echo "#F98765";} ?> ><td><?php echo $row['id'] ?> </td>
    <td><?php echo $row['nome'] ?> </td>
    <td><?php echo $row['marca'] ?> </td>
    <td><?php echo $row['estoque'] ?></td>
    <form method="post" action="index.php?pagina=cadastro">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Alterar" /></td>
    </form>
    <form method="post" action="index.php?pagina=lista">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Excluir" /></td>
    </form>	
</tr>

	<?php
    $i++;
     }
}
$conect->close();
 ?>
   </table>
