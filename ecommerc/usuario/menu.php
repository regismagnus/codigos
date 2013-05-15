
<?php

//require("../../src/util/conexao.php");
@ $idcat = $_GET['idcat'];

$conect = new conexao();
$conect->conectar();

$query = "SELECT * FROM categoria ORDER BY prioridade";
$result = mysql_query($query);

if($result){
    ?>
<ul>  <?php
	while($row=mysql_fetch_array($result)){ 
     ?>
     	<a href="index.php?idcat=<?php echo $row['id'] ?>&pagina=produto"><li <?php if($idcat && $idcat==$row['id']){ echo "class='active'"; } ?>><?php echo $row['nome']; ?></li></a>
     <?php

	}

}

?>
	</ul>
