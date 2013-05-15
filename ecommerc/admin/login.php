<?php
  
  @ $usuario = $_SESSION['usuario'];
  @ $permissao = $_SESSION['permissao'];
  @ $identificacao = $_SESSION['id'];
  
  if($usuario){
  if($permissao!=1){
  header("Location: ../usuario/index.php");
  }
   echo "Você esta logado como: ".$_SESSION['usuario']." <a href=../usuario/index.php?off=true >Sair</a>";
  }else{
     header("Location: ../usuario/index.php");
  }

?>
