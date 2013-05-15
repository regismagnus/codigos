
<?php

 @ $id = $_SESSION['id'];
 @ $acao = $_GET['acao'];
 @ $idpost = $_GET['idpost'];


if(isset($id)){

$conect = new conexao();
$conect->conectar();

if(isset($acao) && isset($idpost)){

   if($acao=="excluir"){

      $query = "DELETE FROM post WHERE id=".$idpost." AND id_usuario=".$id;
      
      $delete = mysql_query($query);
      
      if($delete && mysql_affected_rows()>=1 ){
         echo "Post excluido, com sucesso.";
      }else{
         echo "Erro ao excluir post. ".mysql_error();
      }

   }

}

$query = "SELECT * FROM post WHERE id_usuario=".$id." ORDER BY data_post";

$result = mysql_query($query);

if($result){
                ?>
<table width="100%">
<tr>
    <td>Titulo</td>
    <td>Data</td>
</tr>
                <?php
            while($row=mysql_fetch_array($result)){
                 ?>
                 <tr>
                      <td><?php echo $row['titulo']; ?></td>
                      <td><?php echo $row['data_post']; ?></td>
                      <td><a href="index.php?pagina=post&idpost=<?php echo $row['id']; ?>">Alterar</a></td>
                      <td><a href="index.php?pagina=gerenciar&acao=excluir&idpost=<?php echo $row['id']; ?>">Excluir</a></td>
                 </tr>
                 
                 <?php
            }

}else{
  if(mysql_error()){
     echo "Erro no banco de dados. ".mysql_error();
  }else{
     echo "Sem post";
  }
}


if(isset($connect)){
     $connect.close();
}

}

?>
</table>
