
<?php

//require("../../src/util/conexao.php");
@ $idcat = $_GET['idcat'];
$conect = new conexao();
$conect->conectar();

if($idcat){
    $query2 = "SELECT * FROM subcategoria WHERE id_categoria=".$idcat." ORDER BY prioridade";
    $result2 = mysql_query($query2);

    if($result2){

    while($row2=mysql_fetch_array($result2)){
	?>

	<a href="index.php?id=<?php echo $row2['id'] ?>&pagina=produto&idcat=<?php echo $idcat; ?>"> <?php echo $row2['nome'] ?> </a> <br />
<?php
	}
	}
           }
?>
