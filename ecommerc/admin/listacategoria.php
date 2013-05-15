
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

$query = "UPDATE categoria SET prioridade=".$prioridade." WHERE id=".$id;
$result = mysql_query($query);
if($result){
 echo "Prioridade atualizada";
}else{
 echo "Erro ao atualizar prioridade";
}


}else{
$query = "DELETE FROM categoria WHERE id=".$id;
$result = mysql_query($query);
if($result){
	echo "Categoria excluido com sucesso";
}else{
	echo "A categoria não pode ser excluido";
}
      }
}
if($busca){
 $query = "SELECT * FROM categoria WHERE nome like '%".$busca."%'";
}else{
$query = "SELECT * FROM categoria";
}
$query = $query." ORDER BY prioridade";
$result = mysql_query($query);

if($result){
?>
<table border="0" width="100%">
<tr><td>Código</td>
    <td>Nome Categoria</td>
    <td>Prioridade</td>
</tr>
 <?php
$i = 0;
	while($row=mysql_fetch_array($result)){

?>


<tr bgcolor=<?php if(($i%2)>0){echo "#FFFFFF";}else{echo "#F98765";} ?> ><td><?php echo $row['id'] ?> </td>
    <td><?php echo $row['nome'] ?> </td>
    <td>    <form method="post" action="index.php?pagina=listacategoria">
    <?php echo "<input type='text' name='prioridade' value='".$row['prioridade']."' />"  ?> </td>
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Alterar" /></td>
    </form>
    <form method="post" action="index.php?pagina=listacategoria">
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
