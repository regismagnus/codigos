
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

@ $operacao = $_GET['operacao'];
if($operacao){

switch($operacao){
 case 1:
 $query = "UPDATE pedido SET situacao='Pago' WHERE id=".$id;
 break;
 case 2:
 $query = "UPDATE pedido SET situacao='Enviado' WHERE id=".$id;
 break;
 case 3:
 $query = "UPDATE pedido SET situacao='Entregue' WHERE id=".$id;
 break;
 case 4:
 $query = "UPDATE pedido SET situacao='Cancelado' WHERE id=".$id;
 break;
}

$insert = mysql_query($query);

if($insert){
 echo "Status mudado com sucesso.";
}else{
 echo "Erro ao mudar Status.";
}

}


}
if($busca){
 $query = "SELECT id FROM usuario WHERE login like '%".$busca."%'";
 $result = mysql_query($query);
 
 if($result){
$num_rows = mysql_num_rows($result);

 $query = "SELECT * FROM pedido WHERE ";

 for($i=0; ($i+1)<$num_rows; $i++){
$row=mysql_fetch_array($result);
$query = $query." id_usuario=".$row['id']." OR ";
 }
 $row=mysql_fetch_array($result);
 $query = $query."id_usuario=".$row['id'];
  }
}else{
$query = "SELECT * FROM pedido";
}
$result = mysql_query($query);

?>
<table border="0" width="100%">
<tr><td>Código</td>
    <td>ID Cliente</td>
    <td>ID Produto</td>
    <td>Endereço</td>
    <td>Complemento</td>
    <td>Cidade</td>
    <td>Estado</td>
    <td>Cep</td>
    <td>Quantia</td>
    <td>Status</td>
</tr>
 <?php
 if($result){
$i = 0;
 while($row=mysql_fetch_array($result)){

?>


<tr bgcolor=<?php if(($i%2)>0){echo "#FFFFFF";}else{echo "#F98765";} ?> ><td><?php echo $row['id'] ?> </td>
    <td><?php echo $row['id_usuario'] ?></td>
    <td><?php echo $row['id_produto'] ?></td>
    <td><?php echo $row['endereco'] ?></td>
    <td><?php echo $row['complemento'] ?></td>
    <td><?php echo $row['cidade'] ?></td>
    <td><?php echo $row['estado'] ?></td>
    <td><?php echo $row['cep'] ?></td>
    <td><?php echo $row['quantia'] ?></td>
    <td><?php echo $row['situacao'] ?></td>
    <form method="post" action="index.php?pagina=pedido&operacao=1">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Pago" /></td>
    </form>
    <form method="post" action="index.php?pagina=pedido&operacao=2">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Enviado" /></td>
    </form>
    <form method="post" action="index.php?pagina=pedido&operacao=3">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Entregue" /></td>
    </form>
    <form method="post" action="index.php?pagina=pedido&operacao=4">
	<input type="hidden" name="id" value=<?php echo $row['id'] ?> />
	<td><input type="submit" value="Cancelado" /></td>
    </form>
</tr>

	<?php
    $i++;
     }
}
$conect->close();
 ?>
   </table>
