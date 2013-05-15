
<?php


@ $userid = $_GET['userid'];
@ $busca = $_POST['busca'];

     $conect = new conexao();
     $conect->conectar();

if(isset($userid)){
     $query = "SELECT DISTINCT data_post FROM post WHERE id_usuario=".$userid." ORDER BY data_post DESC";

     $result = mysql_query($query);
     
     if($result){
        while($row=mysql_fetch_array($result)){
            echo "<a href='index.php?pagina=principal&userid=".$userid."&data=".$row['data_post']."'>".$row['data_post']."</a><br />";
        }
     }

}

?>

<form id="busca" method="POST" action="<?php $_SERVER['REQUEST_URI'] ?>" >
       <input type="text" id="busca" name="busca" /><input type="submit" value="Buscar" />
</form>

<?php
     $query = "SELECT nome, id FROM usuario";

     if(isset($busca)){
             $query = $query." WHERE nome LIKE '%".$busca."%'";
     }

     $result = mysql_query($query);
     
     echo "<h3>Blogs:</h3>";
     if($result){
             while($row=mysql_fetch_array($result)){
             ?>
             
               <a href="index.php?pagina=principal&userid=<?php echo $row['id']; ?>"><?php echo $row['nome']; ?></a> <br />
             
             <?php
             }
     }
     
     if(isset($connect)){
         $connect->close();
     }

?>
