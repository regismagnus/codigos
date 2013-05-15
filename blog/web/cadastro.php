
<?php

@ $nome = $_POST['nome'];
@ $email = $_POST['email'];
@ $senha = $_POST['senha'];
@ $confirmasenha = $_POST['confirmaSenha'];

$sucesso = false;

if(isset($nome)){

$conect = new conexao();
$conect->conectar();

$nome = addslashes(strip_tags($nome));
$email = addslashes(strip_tags($email));
$senha = addslashes(strip_tags($senha));
$confirmasenha = addslashes(strip_tags($confirmasenha));

$query = "SELECT email FROM usuario WHERE email='".$email."'";

$result = mysql_query($query);

if(mysql_num_rows($result)>=1){
 echo "O email já está sendo utilizado.";
}else{

$query = "INSERT INTO usuario(nome, email, senha, permissao) VALUES('".$nome."','".$email."','".$senha."',1)";
$insert = mysql_query($query);

if($insert){
   echo "Cadastro realizado com sucesso.";
   $sucesso = true;
}else{
   echo "Erro ao se cadastrar: ".mysql_error();
}

}
}

if(!$sucesso){
?>

<script type="text/javascript">

          $(document).ready(function(){

             $("#cadastro_ca").validate({
             
                rules:{
                 nome:{
                      required: true, maxlength: 30
                 },
                 email:{
                      required: true, email: true, maxlength: 50
                 },
                 senha:{
                      required: true, maxlength: 30, minlength: 6
                 },
                 confirmasenha:{
                      required: true, equalTo: senha
                 }
                },
                messages:{
                 nome:{
                      required: "Campo obrigatorio",
                      maxlenght: "Tamanho deve ser menor que 30"
                 },
                 email:{
                      required: "Campo obrigatorio",
                      maxlenght: "Tamanho deve ser menor que 50",
                      email: "Email invalido"
                 },
                 senha:{
                      required: "Campo obrigatorio",
                      maxlenght: "Tamanho deve ser menor que 30",
                      minlenght: "Tamanho deve ser maior que 6"
                 },
                 confirmasenha:{
                     required: "Campo obrigatorio",
                     equalTo: "Senhas diferentes"
                 }
                }
             });

          });

</script>

<form id="cadastro_ca" method="post" action="index.php?pagina=cadastro">

<label for="nome">Nome:</label><input type="text" id="nome_ca" name="nome" class="cadastro" />
<label for="email">Email:</label><input type="text" id="email" name="email" class="cadastro" />
<label for="senha">Senha:</label><input type="password" id="senha" name="senha" class="cadastro" />
<label for="confirmaSenha">Repita a senha:</label><input type="password" id="confirmasenha" name="confirmasenha" class="cadastro" />
<input type="submit" value="Cadastrar" class="cadastro" />

</form>
<?php
  }
  
  if(isset($connect)){
     $connect->close();
  }

?>
