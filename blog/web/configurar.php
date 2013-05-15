
<?php

     $conect = new conexao();
     $conect->conectar();
     
     $query = "SELECT * FROM usuario WHERE id=".$_SESSION['id'];

     $result = mysql_query($query);
     
     if($result){
     
        $row=mysql_fetch_array($result);
        
        $nome = $row['nome'];
        $email = $row['email'];
        $senha = $row['senha'];
     
     }
?>
