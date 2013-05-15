
<?php

@ $permissao = $_SESSION["permissao"];

if($permissao!=1){
header("Location: ../usuario/index.php");
}
 
 @ $id = $_POST['id'];
 @ $nome = $_POST['nome'];
 @ $marca = $_POST['marca'];
 @ $descricao = $_POST['descricao'];
 @ $caracteristica = $_POST['caracteristica'];
 @ $estoque = $_POST['estoque'];
 @ $categoria = $_POST['categoria'];
 @ $preco = $_POST['preco'];
 
 $uploaddir = '../../public/imagens/';

$tipo = $_FILES['foto']['type'];
$tamanho = $_FILES['foto']['size'];
 $arquivo ="";
 $arquivo = date("d_m_y").date("H_i"). $_FILES['foto']['name'];

require("../../src/util/conexao.php");

$conect = new conexao();
$conect->conectar();

if($id){

if(!$tipo){
@ $query = "UPDATE siteproduto SET nome='".$nome."' , preco='".$preco."', marca='".$marca."', descricao='".$descricao."',
	 caracteristica='".$caracteristica."', estoque='".$estoque."',
	 id_subcategoria='".$categoria."' WHERE id='".$id."'";
}else{
@ $query = "UPDATE siteproduto SET nome='".$nome."' , preco='".$preco."', marca='".$marca."', descricao='".$descricao."',
	 caracteristica='".$caracteristica."', foto='".$arquivo."', estoque='".$estoque."',
	 id_subcategoria='".$categoria."' WHERE id='".$id."'";
}
}else{

if(!$tipo){
 $arquivo = "sem.jpg";
}
 $query = "INSERT INTO siteproduto (nome, preco, marca, descricao, caracteristica, foto, estoque, id_subcategoria) VALUES
	('".$nome."','".$preco."' ,'".$marca."', '".$descricao."', '".$caracteristica."',
	 '".$arquivo."', '".$estoque."', '".$categoria."')";
}

$insert = mysql_query($query);

if($insert===FALSE)
echo "Erro ao inserir os dados... " .mysql_error();
else{
echo "Produto salvo com sucesso.";

if($tamanho<=1961366 && $tipo=="image/jpeg"){

$uploadfile = $uploaddir.$arquivo;

if (move_uploaded_file($_FILES['foto']['tmp_name'], $uploadfile)){
echo "OK ";}
else {echo "Arquivo não enviado";}
}else{
if($_FILES['foto']=="")
echo "Arquivo não pode ser enviado, tamanho ou tipo errado.";
}

}
$conect->close();
?>
