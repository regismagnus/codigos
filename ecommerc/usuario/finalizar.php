<?php
     @ $usuario = $_POST['usuario'];
     @ $senha = $_POST['senha'];
     
     $conect = new conexao();
      $conect->conectar();
      
     if($usuario && $senha){
     $query = "SELECT * FROM usuario WHERE login='".$usuario."' AND senha=".$senha;
     $result = mysql_query($query);

     if($result){
       $row=mysql_fetch_array($result);
       $_SESSION['usuario']= $row['login'];
       $_SESSION['permissao']= $row['tipo'];
       $_SESSION['id']= $row['id'];
   }
   }

@ $iduser = $_SESSION['id'];

@ $final = $_POST['final'];


if(count($_SESSION['carrinho']) == 0){
       echo 'Não há produto no carrinho.';
}else{

if($iduser){

      $query = "SELECT * FROM usuario WHERE id=".$iduser;
      $result = mysql_query($query);
      
      if($result){
      $row=mysql_fetch_array($result);
      $endereco = $row['endereco'];
      $numero = $row['numero'];
      $complemento = $row['complemento'];
      $cidade = $row['cidade'];
      $estado = $row['estado'];
      $cep = $row['cep'];
      $referencia = $row['referencia'];
     @ $adicional = $_POST['adicional'];
      
      echo " <table border=0 width='100%'>
           <tr><td class='linhatitulo'colspan='2'><h2>Dados do Comprador</h2></td></tr>
           <tr><td><h3>".$row['nome']."</h3>";
      echo $endereco.",".$numero." ".$complemento."</td></tr>";
      ?>
           <tr><td class="linhatitulo" colspan="2"><h2>Dados Adicionais</h2></td></tr>
          <tr><td style="text-align: right; padding-left: 10px; padding-top: 5px;" valign="top" ><form method="POST" action="index.php?pagina=finalizar">
           Observação:</td><td ><textarea name="adicional" rows="3" cols="20"></textarea>
           </form></td></tr>
               <tr><td class="linhatitulo" colspan="2"><h2>Dados da Compra</h2></td></tr>
               <tr><td colspan="2">
           <table>
    <thead>
          <tr>
            <th width="244">Produto</th>
            <th width="79">Quantidade</th>
            <th width="89">Pre&ccedil;o</th>
            <th width="100">SubTotal</th>
          </tr>
    </thead>

    <tbody>
               <?php
                     if(count($_SESSION['carrinho']) == 0){
                        echo '<tr><td colspan="5">Não há produto no carrinho</td></tr>';
                     }else{
                           $total = 0;
                        foreach($_SESSION['carrinho'] as $id => $qtd){
                              $sql   = "SELECT *  FROM siteproduto WHERE id= '$id'";
                              $qr    = mysql_query($sql) or die(mysql_error());
                              $ln    = mysql_fetch_assoc($qr);

                              $nome  = $ln['nome'];
                              $preco = number_format($ln['preco'], 2, ',', '.');
                              $sub   = number_format($ln['preco'] * $qtd, 2, ',', '.');

                              $total += $ln['preco'] * $qtd;

                           echo '<tr>
                                 <td><img src=../../public/'.$ln['foto'].' width=50 height=50 />'.$nome.'</td>
                                 <td>'.$qtd.' </td>
                                 <td>R$ '.$preco.'</td>
                                 <td>R$ '.$sub.'</td>
                              </tr>';
                        }
                           $total = number_format($total, 2, ',', '.');
                           echo '
                               <tr>
                                    <td colspan="5" align="right"><br /> Total R$ '.$total.'</td>

                              </tr>';
                     }
               ?>
               </table>
               </td></tr>
               </table>
      <?php

      }else{
       echo "Erro ao importar dados do usuario.";
      }

  if($final){
      foreach($_SESSION['carrinho'] as $id => $qtd){

      $query = "INSERT INTO pedido(id_usuario, id_produto, endereco, numero, complemento, cidade, estado, cep, referencia, adicional, quantia, situacao)
               VALUES (".$iduser.",".$id.",'".$endereco."','".$numero."','".$complemento."','".$cidade."','".$estado."','".$cep."','".$referencia."','".$adicional."',".$qtd.",'Aguardando')";
               
      $result = mysql_query($query);
      
      }
      echo "Pedido finalizado.";
      
      unset($_SESSION['carrinho']);

 }
}else{

echo "Faça o login primeiro.";
 ?>
<form method="post" action="index.php?pagina=finalizar">
         Nome: <input type="text" name="usuario" /><br /> Senha: <input type="password" name="senha" />
         <input type="submit" value="Entrar" />
  </form>
   <?php
}
}
?>
<a href="index.php">Continuar comprando</a>

