<?php

 @ $id = $_GET['id'];
 @ $idcat = $_GET['idcat'];
 @ $pag = $_GET['pag'];
 @ $busca = $_GET['busca'];

$conect = new conexao();
$conect->conectar();

if($id){
$query = "SELECT * FROM siteproduto WHERE id_subcategoria=".$id;
$queryCount = "SELECT count(id) AS q FROM siteproduto WHERE id_subcategoria=".$id;
}elseif($idcat){
$query = "SELECT * FROM siteproduto
         WHERE id_subcategoria
         IN (
             SELECT id FROM subcategoria WHERE id_categoria = ".$idcat.")";
$queryCount = "SELECT count(id) AS q FROM siteproduto
         WHERE id_subcategoria
         IN (
             SELECT id FROM subcategoria WHERE id_categoria = ".$idcat.")";
}elseif($busca){
   $query = "SELECT * FROM siteproduto WHERE nome LIKE '%".$busca."%'";
   $queryCount = "SELECT count(id) AS q FROM siteproduto WHERE nome LIKE '%".$busca."%'";
}else{
 $query = "SELECT * FROM siteproduto";
 $queryCount = "SELECT count(id) AS  q FROM siteproduto";
}
if($pag){
         $pag = $pag*20;
         $query = $query." LIMIT ".$pag.", 20";
}else{
         $query = $query." LIMIT 0, 20";
}
$result = mysql_query($query);
$resultCount = mysql_query($queryCount);

 if($result){
while($row=mysql_fetch_array($result)){
?>
                     <div class="productleft">
                             <a href="index.php?id=<?php echo $row['id'] ?>&pagina=detalhe"><img src="../../public/imagens/<?php echo $row['foto'] ?>" height="100" width="100" border="0" /> <br />
                             <?php echo $row['nome']." </a><br />R$ ".number_format($row['preco'],2,",",".");
                              $row=mysql_fetch_array($result);
                              if($row){
                              ?>
                     </div>
                     <div class="productcenter">
                             <a href="index.php?id=<?php echo $row['id'] ?>&pagina=detalhe"><img src="../../public/imagens/<?php echo $row['foto'] ?>" height="100" width="100" border="0" /> <br />
                             <?php echo $row['nome']." </a><br />R$ ".number_format($row['preco'],2,",",".");
                             }
                             $row=mysql_fetch_array($result);
                             if($row){
                             ?>
					</div>
					<div class="productright">
                             <a href="index.php?id=<?php echo $row['id'] ?>&pagina=detalhe"><img src="../../public/imagens/<?php echo $row['foto'] ?>" height="100" width="100" border="0" /> <br />
                             <?php echo $row['nome']." </a><br />R$ ".number_format($row['preco'],2,",","."); }?>
					</div>
<?php }
?>
     <br class="clearfix" />
<?php
$row=mysql_fetch_array($resultCount);
}
$count= $row['q']/20;
@ $atual = $_GET['pag'];
for($i=0;$i<$count;$i++){
if((isset($atual) &&  $atual==$i) || (!isset($atual) && $i==0)){
  echo "<div class='pagesActive'>".($i+1)."</div>";
}else{
 echo "<a href=index.php?pagina=produto&id=".$id."&pag=".$i.($idcat ? "&idcat=".$idcat : "").(isset($busca) ? "&busca=".$busca : "")."><div class='pages'>".($i+1)."</div></a> ";
 }
}
  $conect->close();
?>
