
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
@ $prioridade = $_POST['prioridade'];

if($id){
if($prioridade){

$query = "UPDATE subcategoria SET prioridade=".$prioridade." WHERE id=".$id;
$result = mysql_query($query);
if($result){
 echo "Prioridade atualizada";
}else{
 echo "Erro ao atualizar prioridade";
}


}else{
$query = "DELETE FROM subcategoria WHERE id=".$id;
$result = mysql_query($query);
if($result){
	echo "Subcategoria excluido com sucesso";
}else{
	echo "A subcategoria não pode ser excluido";
}
      }
}
if($busca){
 $query = "SELECT *,(SELECT nome FROM categoria WHERE id=id_categoria) AS categoria FROM subcategoria WHERE nome like '%".$busca."%'";
}else{
$query = "SELECT *,(SELECT nome FROM categoria WHERE id=id_categoria) AS categoria FROM subcategoria";
}
$query = $query." ORDER BY id_categoria";
$result = mysql_query($query);

if($result){
?>
<table border="0" width="100%">
<tr><td>Código</td>
    <td>Nome Subategoria</td>
    <td>Categoria</td>
    <td>Prioridade</td>
</tr>
 <?php
$i = 0;
	while($row=mysql_fetch_array($result)){

?>


<tr bgcolor=<?php if(($i%2)>0){echo "#FFFFFF";}else{echo "#F98765";} ?> ><td><?php echo $row['id'] ?> </td>
    <td><b><?php echo $row['nome'] ?> </b></td>
    <td><?php echo $row['categoria'] ?></td>
    <td>    <form method="post" action="index.php?pagina=listasubcategoria">
    <?php echo "<input type='text' name='prioridade' value='".$row['prioridade']."' />"  ?> </td>
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Alterar" /></td>
    </form>
    <form method="post" action="index.php?pagina=listasubcategoria">
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
