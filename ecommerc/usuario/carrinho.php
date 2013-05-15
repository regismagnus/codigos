<?php
$conect = new conexao();
$conect->conectar();
      if(!isset($_SESSION['carrinho'])){
         $_SESSION['carrinho'] = array();
      }

      //adiciona produto

      if(isset($_GET['acao'])){

         //ADICIONAR CARRINHO
         if($_GET['acao'] == 'add'){
            $id = intval($_GET['id']);
               $sql   = "SELECT *  FROM siteproduto WHERE id= '$id'";
               $qr    = mysql_query($sql) or die(mysql_error());
               $ln    = mysql_fetch_array($qr);
               $estoque = $ln['estoque'];
            if(!isset($_SESSION['carrinho'][$id])){
               $_SESSION['carrinho'][$id] = 1;
            }else{
              if($estoque>=($_SESSION['carrinho'][$id] +1)){
               $_SESSION['carrinho'][$id] += 1;
               }else{
                      echo "Quantidade em estoque baixa, para o pedido";
               }

            }
         }

         //REMOVER CARRINHO
         if($_GET['acao'] == 'del'){
            $id = intval($_GET['id']);
            if(isset($_SESSION['carrinho'][$id])){
               unset($_SESSION['carrinho'][$id]);
            }
         }

         //ALTERAR QUANTIDADE
         if($_GET['acao'] == 'up'){
            if(is_array($_POST['prod'])){
               foreach($_POST['prod'] as $id => $qtd){
                  $id  = intval($id);
                  $qtd = intval($qtd);
                  
               $sql   = "SELECT *  FROM siteproduto WHERE id= '$id'";
               $qr    = mysql_query($sql) or die(mysql_error());
               $ln    = mysql_fetch_array($qr);
               $estoque = $ln['estoque'];
                  
                  if($estoque>= $qtd){
                  if(!empty($qtd) || $qtd <> 0){
                     $_SESSION['carrinho'][$id] = $qtd;
                  }else{
                     unset($_SESSION['carrinho'][$id]);
                  }
                  }else{
                     echo "Quantidade em estoque baixa, para o pedido";
                  }
               }
            }
         }

      }


?>


<table>
    <caption>Carrinho de Compras</caption>
    <thead>
          <tr>
            <th width="244">Produto</th>
            <th width="79">Quantidade</th>
            <th width="89">Pre&ccedil;o</th>
            <th width="100">SubTotal</th>
            <th width="64">Remover</th>
          </tr>
    </thead>
            <form action="index.php?pagina=carrinho&acao=up" method="post">


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
                                 <td>'.$nome.'</td>
                                 <td><input type="text" size="3" name="prod['.$id.']" value="'.$qtd.'" /></td>
                                 <td>R$ '.$preco.'</td>
                                 <td>R$ '.$sub.'</td>
                                 <td><a href="?pagina=carrinho&acao=del&id='.$id.'">Remove</a></td>
                              </tr>';
                        }
                           $total = number_format($total, 2, ',', '.');
                           echo '
                                   <tr>
                                               <td colspan="5"><input type="submit" value="Atualizar Carrinho" /></td>
                                   <tr>
                               <tr>
                                    <td colspan="5" align="right"><br /> Total R$ '.$total.'</td>

                              </tr>';
                     }
               ?>
               <td colspan="3"><a href="index.php">Continuar Comprando</a></td>
               <td colspan="2"><a href="index.php?pagina=finalizar">Finalizar Comprando</a></td>
               </form>
               </table>
