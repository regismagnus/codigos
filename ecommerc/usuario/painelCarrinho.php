<?php

  require_once("xajax/xajax.inc.php");

        $xajax = new xajax();

        $xajax->registerFunction("remover");
        $xajax->registerFunction("listar");


      if(!isset($_SESSION['carrinho'])){
         $_SESSION['carrinho'] = array();
      }

         //REMOVER CARRINHO

         function remover($id){
            //$id = intval($_GET['id']);
            if(isset($_SESSION['carrinho'][$id])){
               unset($_SESSION['carrinho'][$id]);
            }
            
            if($_GET['pagina']=="carrinho"){

            }

              return listar();
        }

        function listar(){

         	$objResponse = new xajaxResponse();

             if(count($_SESSION['carrinho']) == 0){
                        $resposta = 'Não há produto no carrinho';
                     }else{
                     $conect = new conexao();
                     $conect->conectar();
                                              $total = 0;
                                              $resposta = "";
                                              $count = 0;
                        foreach($_SESSION['carrinho'] as $id => $qtd){
                              $sql   = "SELECT *  FROM siteproduto WHERE id= '$id'";
                              $qr    = mysql_query($sql) or die(mysql_error());
                              $ln    = mysql_fetch_assoc($qr);

                              $nome  = substr($ln['nome'], 0, 18);
                              $total += $ln['preco'] * $qtd;

                           $resposta = $resposta.' <div class="clearall"></div><div class="nome">'.$nome.'</div>
                                 <div class="remover">'.($_GET['pagina']=="carrinho" ? '</div>' : '<a href="#" onclick="xajax_remover('.$id.') ">|X|</a></div>');
                           $count++;

                           if($count >= 18){
                              break;
                           }
                           }
                           $resposta = '<div class="total">Total R$ '.$total.'</div><h4>Ultimos pedidos:</h4>'.$resposta;
                     }

                     	$objResponse->addAssign("lista", "innerHTML", $resposta);

                     	return $objResponse;
        }
$xajax->processRequests(); ?>

