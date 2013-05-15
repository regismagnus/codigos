
<?php

@ $userid = $_GET['userid'];
@ $data = $_GET['data'];
@ $pag = $_GET['pag'];

if(isset($userid)){

     $conect = new conexao();
     $conect->conectar();

     $query = "SELECT * FROM post WHERE id_usuario=".$userid;
     $querycount = "SELECT count(id) AS n FROM post WHERE id_usuario=".$userid;
     
     if(isset($data)){
     
     $query = $query." AND data_post='".$data."'";
     $querycount = $querycount." AND data_post='".$data."'";
     
     }
     
     $query .= " ORDER BY data_post DESC ";

     if(isset($pag)){

        $query = $query." LIMIT ".($pag*10).",10";
     
     }else{

       $query = $query." LIMIT 0,10";

     }
     
     $result = mysql_query($query);
     $resultcount = mysql_query($querycount);
     
     if($resultcount){
               $rowcount = mysql_fetch_array($resultcount);
               $tamanho = $rowcount['n'];
     }
           if($result){
             while($row=mysql_fetch_array($result)){
       
           echo "<h2>".$row['titulo']."</h2>";
           echo "<div class='data'>Data de Postagem: ".$row['data_post']."</div>";
           echo "<div class='message'>".nl2br($row["message"])."</div>";

       }
       if(mysql_num_rows($result)==0){
           echo "Nenhum post publícado.";
       }
     }else{
          echo "Nenhum post publícado.";
     }
     
     ?>
          <br class="clearfix" />
     <?php

     for($i=0; $i<($tamanho/10); $i++){
     
           if($pag<>$i){
              echo "<a href='index.php?pagina=principal&pag=".$i."&userid=".$userid."'>".($i+1)."</a>";
           }else{
              echo ($i+1);
           }
     }

     if(isset($connect)){
      $connect->close();
     }

}else{
   echo "Blog não encontrado";
}

?>
