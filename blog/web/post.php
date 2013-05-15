
<?php

@ $id = $_SESSION['id'];
@ $titulo = $_POST['titulo'];
@ $message = $_POST['message'];
@ $update = $_POST['update'];
@ $idpost = $_GET['idpost'];

if(!isset($id)){
     header("Location: index.php");
}

if(isset($message) && isset($titulo)){

     $conect = new conexao();
     $conect->conectar();
     
     $titulo = addslashes(strip_tags($titulo));
     $message = addslashes(strip_tags($message, "<strong>,<img>,<a>"));

     if(isset($update)){
       $query = "UPDATE post SET message='".$message."', titulo='".$titulo."' WHERE id=".$update." AND id_usuario=".$id;
       $message =null;
       $titulo = null;
     }else{
       $query = "INSERT INTO post(id_usuario, data_post, message, titulo) VALUES(".$id.",'".date("Y-m-d")."','".$message."','".$titulo."')";
       $message =null;
       $titulo = null;
     }
     $insert = mysql_query($query);

           if($insert){
                      echo "Post publícado com sucesso";
           }else{
                      echo "Erro ao publícar post. ".mysql_error();
           }

   }

   if(isset($idpost)){

     $conect = new conexao();
     $conect->conectar();

     $query = "SELECT * FROM post WHERE id=".$idpost." AND id_usuario=".$id;

     $result = mysql_query($query);

     if($result){
        $row= mysql_fetch_array($result);

        $message = $row['message'];
        $titulo = $row['titulo'];

     }
   }
?>



<script type="text/javascript" src="script/java.js"></script>

<script type="text/javascript">

        function contar(){

         var message = document.getElementById("message");
         var label = document.getElementById("count");

         label.innerHTML = "[" + message.value.length + "/4000]";
        }


</script>

<form id="postar" method="post" action="index.php?pagina=post">
<?php
 if(isset($message) && isset($titulo) && isset($idpost)){
    ?>
     <input type="hidden" id="update" name="update" value="<?php echo $idpost; ?>" />
    <?php
 }

?>
<label for="titulo">Titulo:</label><input type="text" id="titulo" name="titulo" size="50" <?php if(isset($titulo)){ echo "value='".$titulo."'"; } ?> />
<label for="message">POST:</label>
<input type="button" value="Negrito" id="negrito" class="prata first" /><input type="button" value="Link" onclick="negrito()" id="link" class="prata" /><input type="button" value="Imagem" id="imagem" class="prata last" />
<div id="painel_link">
      <label for="url">URL:</label><input type="text" id="url" name="url" size="35" value="http://" /><br />
      <input type="button" id="link_ok" value="OK" style="padding: 5px"/>
</div>
<div id="painel_imagem">
      Alinhamento:
      <label for="sem"><input type="radio" id="sem" name="imagem_align" value="sem" checked>Sem</label>
      <label for="esquerda"><input type="radio" id="esquerda" name="imagem_align" value="esquerda">Esquerda</label>
      <label for="direita"><input type="radio" id="direita" name="imagem_align" value="direita">Direita</label>
      <br />
      <label for="url_imagem">URL:</label><input type="text" id="url_imagem" name="url_imagem" size="35" value="http://" /><br />
      <input type="button" id="imagem_ok" value="OK" style="padding: 5px"/>
</div>
 <br />
<textarea id="message" name="message" cols="80" rows="10" onkeyup="contar()" ><?php if(isset($message)){ echo $message; } ?></textarea>
<label id="count">[0/4000]</label>
<input type="submit" value="Publícar" />
<input type="reset" value="Limpar" />
</form>
