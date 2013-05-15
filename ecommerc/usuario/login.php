<?php

     @ $usuario = $_POST['usuario'];
     @ $senha = $_POST['senha'];
     @ $off = $_GET['off'];

   if($off){
    session_destroy();
    header("Location: index.php");
   }
   
if($usuario && $senha){

     $conect = new conexao();
     $conect->conectar();

     $query = "SELECT * FROM usuario WHERE login='".$usuario."' AND senha=".$senha;
     $result = mysql_query($query);

     if($result){
       $row=mysql_fetch_array($result);
       $_SESSION['usuario']= $row['login'];
       $_SESSION['permissao']= $row['tipo'];
       $_SESSION['id']= $row['id'];
   }
  // $conect->close();

}
  
  @ $usuario = $_SESSION['usuario'];
  @ $permissao = $_SESSION['permissao'];
  @ $identificacao = $_SESSION['id'];
  
  if($usuario){
  if($permissao==1){
  header("Location: ../admin/index.php");
  }
   echo "Bem vindo, ".$_SESSION['usuario']." <a href=index.php?off=true >Sair</a>";
  }else{
  
  ?>

 <form method="post" action="<?php echo $_SERVER ['REQUEST_URI'] ?>">
<ul>
 <li class="first">
         Nome: <input type="text" name="usuario" /> Senha: <input type="password" name="senha" />
        <input type="submit" value="Entrar" />
  </li>
  <li>
      <a href="index.php?pagina=cadastrar">Cadastrar</a>
  </li>
</ul>
  </form>

  <?php
  
  }

?>
