
<?php
  @ $permissao = $_SESSION["permissao"];

if($permissao!=1){
header("Location: ../usuario/index.php");
}

@ $nome = $_POST['nome'];
@ $prioridade = $_POST['prioridade'];
@ $id = $_POST['categoria'];

require("../../src/util/conexao.php");

$conect = new conexao();
$conect->conectar();

if($nome){

$query = "INSERT INTO subcategoria(id_categoria, nome, prioridade) VALUES(".$id.",'".$nome."',".($prioridade ? $prioridade : 1).")";

$insert = mysql_query($query);

if($insert){
 echo "Cadastro realizado com sucesso.";
}else{
echo "Erro no cadastro.".mysql_error();
}
}else{
?>

<style type="text/css">
*

label.error {color: red; margin: 0 0 0 0; }
</style>

<script src="jquery.js" type="text/javascript"></script>
 <script src="jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">
         $(document).ready(function(){
           //verificar formulario
               $("#subcategoria").validate({

               rules:{
                  nome:{
                    required: true, maxlength: 30
                   },
                   prioridade:{
                    number: true
                  }
               },
               messages:{
                  nome:{
                    required: "Preencha o nome",
                    maxlength: "O nome não pode ter mais do que, 30 caracteres"
                  },
                  prioridade:{
                    number: "Somente digite numeros, neste campo"
                  }
               }
               });

         });
</script>



<form id="subcategoria" method="POST" action="index.php?pagina=subcategoria">
<table>
<tr>
<td><label for="nome">Nome subcategoria:</label></td><td><input type="text" id="nome" name="nome" /></td>
</tr>
<tr>
<td><label for="categoria">Categoria:</label></td><td>
<select id="categoria" name="categoria">
     <?php

$query = "SELECT * FROM categoria";
$result = mysql_query($query);

if($result){

	while($row=mysql_fetch_array($result)){ ?>

		<option value=<?php echo $row['id']; ?> > <?php echo $row['nome'] ?> </option>

	<?php }
} ?>
</td>
</tr>
<tr>
<td><label for="prioridade">Prioridade:</label></td><td><input type="text" id="prioridade" name="prioridade" /></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Salvar" /></td>
</tr>
</table>
</form>
<?php } ?>
