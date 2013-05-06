
<?php 

@ $permissao = $_SESSION["permissao"];

if($permissao!=1){
header("Location: ../usuario/index.php");
}

@ $id = $_POST['id'];

require("../../src/util/conexao.php");

if($id){

$conect = new conexao();
$conect->conectar();

$query = "SELECT * FROM siteProduto WHERE id=".$id;

$result = mysql_query($query);

if($result){

$row=mysql_fetch_array($result);

$nome = $row['nome'];
$marca = $row['marca'];
$descricao = $row['descricao'];
$caracteristica = $row['caracteristica'];
$estoque = $row['estoque'];
$categoria = $row['id_subcategoria'];
$preco = $row['preco'];

}
 }
?>
<style type="text/css">
*

label.error {color: red; margin: 0 0 0 0; }
</style>

<script src="jquery.js" type="text/javascript"></script>
 <script src="jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">

        $(document).ready( function() {
        $("#cadastro").validate({

        rules:{
               nome:{
               required: true, maxlength: 150
},
marca:{
required: true, maxlength: 30
},
preco:{
required: true, number: true
},
descricao:{
maxlength: 2000
},
caracteristica:{
maxlength: 2000
},
estoque:{
required: true, number: true
}
},
messages:{
nome:{
required: "Digite o nome",
maxlength: "O nome pode ter no máximo, 150 caracteres"
},
marca:{
required: "Digite a marca",
maxlength: "A marca pode ter no máximo, 30 caracteres"
},
preco:{
required: "Digite o preço",
number: "Digite somente numeros"
},
descricao:{
maxlength: "A descricao pode ter no máximo, 2000 caracteres"
},
caracteristica:{
maxlength: "A caracteristica pode ter no máximo, 2000 caractreres"
},
estoque:{
required: "Digite o estoque",
number: "Somente digite números"
}
}
});
});
</script>

<script type="text/javascript">

//contador de palavras de decrição

function contarDescricao(){
         var descricao = document.getElementById("descricao");
         var label = document.getElementById("countDescricao");

         label.innerHTML = "[" + descricao.value.length + "/2000]";
}

function contarCaracteristica(){
         var caracteristica = document.getElementById("caracteristica");
         var label = document.getElementById("countCaracteristica");
         
         label.innerHTML = "[" + caracteristica.value.length + "/2000]";
}
</script>

<h1>Registro de Produtos</h1>

<form id="cadastro" method="POST" enctype="multipart/form-data" action="index.php?pagina=salvar">
<?php if($id){ ?><input type="hidden" name="id" value=<?php echo $id ?> /> <?php } ?>

<table width="100%">
<tr><td align="right"><label for="nome">Nome:</label></td><td align="left"><input id="nome" name="nome" type="text" value="<?php if($id) echo $nome; ?>" />  </td></tr>
<tr><td align="right">Marca:</td><td align="left"> <input type="text" id="marca" name="marca" value="<?php if($id) echo $marca; ?>"/>  </td></tr>
<tr><td align="right">Preço:</td><td align="left"> <input type="text" id="preco" name="preco" value="<?php if($id) echo $preco; ?>"/>  </td></tr>
<tr><td align="right">Foto:</td><td align="left"> <input type="file" id="arquivo" name="foto" />  </td></tr>
<tr><td align="right">Categoria:</td><td align="left"><select id="categoria" name="categoria" >

<?php

$conect = new conexao();
$conect->conectar();

$query = "SELECT * FROM categoria";
$result = mysql_query($query);

if($result){

	while($row=mysql_fetch_array($result)){ ?>

    <?php $query2 = "SELECT * FROM subcategoria WHERE id_categoria=".$row['id'];
    
    $result2 = mysql_query($query2); ?>
       <optgroup label="<?php echo $row['nome']; ?>">
       <?php if($result2){
       
             while($row2=mysql_fetch_array($result2)){
              ?>
                       <option value=<?php echo $row2['id']; if(isset($categoria) && $categoria==$row2['id']){echo " selected";} ?> > <?php echo $row2['nome'] ?> </option>
            <?php }
       
       } ?>
       </optgroup>



	<?php }
} ?>

</select> <br />

<tr><td align="right">
<label id="countDescricao">[0/2000]</label>
<br />Descrição:</td><td align="left"> <textarea id="descricao" name="descricao" onkeyup="contarDescricao()" cols="50" rows="10" ><?php if($id) echo $descricao; ?></textarea>  </td></tr>
<tr><td align="right">
<label id="countCaracteristica">[0/2000]</label>
<br />Caracteristica:</td><td align="left"> <textarea id="caracteristica" name="caracteristica" onkeyup="contarCaracteristica()" cols="50" rows="10"><?php if($id) echo $caracteristica; ?></textarea>  </td></tr>
<tr><td align="right">Quant. Estoque:</td><td align="left"> <input type="type" id="estoque" name="estoque" value="<?php if($id) echo $estoque; ?>"/> <br /> </td></tr>
<?php
$conect->close();
?>
</table>
<input type="submit" value="Salvar" /> <input type="reset" value="Limpar" />


</form>

</body>
</html>
