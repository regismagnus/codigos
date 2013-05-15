<?php

     @ $usuario = $_POST['usuario'];
     @ $senha = $_POST['senha'];
     @ $off = $_GET['off'];

   if(isset($off)){
    session_destroy();
    header("Location: index.php");
   }

if($usuario && $senha){

     $conect = new conexao();
     $conect->conectar();

     $query = "SELECT * FROM usuario WHERE email='".$usuario."' AND BINARY senha='".$senha."'";
     $result = mysql_query($query);

     if($result){
       $row=mysql_fetch_array($result);
       $_SESSION['usuario']= $row['email'];
       $_SESSION['nome'] = $row['nome'];
       $_SESSION['permissao']= $row['permissao'];
       $_SESSION['id']= $row['id'];
       
       header("Location: index.php?pagina=post");
   }

}

  @ $usuario = $_SESSION['usuario'];
  @ $nome = $_SESSION['nome'];
  @ $permissao = $_SESSION['permissao'];
  @ $identificacao = $_SESSION['id'];


  if(isset($usuario)){
   echo "<a href='index.php?pagina=post'>Postar</a> | <a href='index.php?pagina=gerenciar'>Gerenciar Posts</a> | Bem vindo, ".$_SESSION['nome']." <a href=index.php?off=true >Sair</a>";
  }else{

  ?>
   <script type="text/javascript">
   
         $(document).ready(function(){
         
                $("#login").hide();
                
                $("#login_botao").click(function(){
                
                  $("#login").slideToggle("slow");
                
                });
                
                $("#painel_login").hover(function(){
                
                },function(){
                
                   $("#login").hide();
                
                });
                
                $("#cadastrar").click(function(){
                
                   window.location="index.php?pagina=cadastro";
                
                });
         
         });
   
   </script>
  <div id="painel_registro">
       <input type="button" id="cadastrar" class="prata login" value="Cadastrar"/>
  </div>
  <div id="painel_login">
     <div id="login">
          <form method="post" action="index.php">
                 <label for="email_login">Email:</label><input type="text" id="email_login" name="usuario" />
                 <label for="senha_login">Senha:</label><input type="password" id="senha_login" name="senha" />
                 <input type="submit" value="Entrar" />
          </form>
     </div>
     <div id="login_botao">
           <input type="button" id="botao" value="Login" class="prata login" />
     </div>
  </div>

  <?php

  }

?>
