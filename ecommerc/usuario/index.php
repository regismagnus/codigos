<?php
   session_start();
  @ $pagina = $_GET['pagina'];
  require("../../src/util/conexao.php");
  require("painelCarrinho.php");

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Regis Magnus
http://www.regismagnus.com.br
2013
-->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<title>Accumen by Free CSS Templates</title>
		<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />
		<link href="http://fonts.googleapis.com/css?family=Kreon" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="style.css" />
 <?php

		 $xajax->processRequests();
   $xajax->printJavascript("xajax/");
?>
<script language="javascript" type="text/javascript">

   xajax_listar()

</script>
		<script language="javascript" src="jquery.js" type="text/javascript"></script>

		<script>
                $(document).ready(function(){
                             var carrinho = false;

                             $("#painel").css({right: '-150px'});

                    $("#painel_in_carrinho").click(function(){
                      if(carrinho){
                         $("#painel").animate({ right: '-150px'}, '1000' ,function(){
                        carrinho = false;
                         });
                      }else{
                         $("#painel").animate({ right: '0px'}, '1000' ,function(){
                          carrinho = true;
                         });
                      }
                    });
                    
                    $("#painel").hover(function(){
                          // Nada foi acrescentado, somente quando sai de hover
                    },function(){
                       $("#painel").animate({ right: '-150px'}, '1000', function() {
                          carrinho=false;
                       });
                    });
                    
                });
		</script>
		<script>
                    function abreLink(){
                                window.location=('index.php?pagina=carrinho');
                    }
       </script>
	</head>
	<body>
        <div id="painel">
              <div id="painel_in">
                <div id="lista"></div>
              </div>
              <div id="painel_in_carrinho"></div>
        </div>
    	<div id="line-top">
                  <div id="line-top-inside">
                                     All with 50% off
                  </div>
        </div>
		<div id="wrapper">
			<div id="header">
				<div id="logo">
					<h1><a href="index.php">FOODS</a></h1>
				</div>
				<div id="banner-top">
                     <form>
                            <input type="button" value="Home" class="button" />
                            <input type="button" value="Who are we?" class="button" />
                            <input type="button" value="About" class="button" />
                            <input type="button" value="Contact" class="button" />
                     </form>
				</div>
				<div id="banner-bottom">
                        <?php require("login.php"); ?>
				</div>
			</div>
			<div id="menu">
                      <?php require("menu.php"); ?>
					<br class="clearfix" />
			</div>
			<div id="page-top">
                 <div id="page-top-inside-left">
                      Search
                 </div>
                 <div id="page-top-inside-center">
                       <form method="GET" action="index.php" >
                              <input type="text" style="width: 400px" name="busca" id="busca" />
                              <input type="submit" value="Search" class="button" />
                       </form>
                 </div>
                 <div id="page-top-inside-right">
                       <form action="index.php?pagina=carrinho" method="get">
                             <input type="button" value="Carrinho de Compras" class="button" onclick="abreLink();" />
                       </form>
                 </div>
			</div>
			<div id="page-top-second">

			</div>
			<div id="page">
				<div id="sidebar">
                    <div class="box">
                          <h3>Produtos</h3>
                           <?php require("categoria.php"); ?>
                    </div>
                    <div class="box">
						<h3>Goi cuon</h3>
						<p>
                            Ego Vena series sudo ac Nitidus. Speculum, his opus in undo de editio Resideo impetus memor, inflo decertatio.
						</p>
						<ul class="list">
							<li class="first"><a href="#">Dux prius edo</a></li>
							<li><a href="#">Huic quod Sis canalis</a></li>
							<li class="last"><a href="#">Insidiae, si pax Cupido, ut</a></li>
						</ul>
					</div>
					<div class="box">
						<h3>Lechon</h3>
						<ul class="list">
							<li class="first"><a href="#">Tergo, ac Cui per quo processus</a></li>
							<li><a href="#">Disputo sui Infucatus leo</a></li>
							<li><a href="#">Prodoceo par Verber, nec</a></li>
							<li><a href="#">Uberrime alo Scelestus</a></li>
							<li class="last"><a href="#">Escensio Mundus</a></li>
						</ul>
					</div>
				</div>
				<div id="content">
					<div class="box">
                <?php
                 if($pagina){
                            if($pagina=="detalhe"){
                             require("detalhe.php");
                            }elseif($pagina=="produto"){
                             require("produto.php");
                            }elseif($pagina=="cadastrar"){
                              require("cadastrar.php");
                            }elseif($pagina=="carrinho"){
                              require("carrinho.php");
                            }elseif($pagina=="finalizar"){
                              require("finalizar.php");
                            }
                }else{
                 require("produto.php");
                }
                ?>
					<br class="clearfix" />
				</div>
				<br class="clearfix" />
			</div>
			<div id="page-bottom">
				<div id="page-bottom-sidebar">
					<h3>Parma ham</h3>
					<ul class="list">
						<li class="first"><a href="#">Algor infrunitus</a></li>
						<li><a href="#">Rogo eo non Namucense</a></li>
						<li class="last"><a href="#">Supero ivi vituperabilis</a></li>
					</ul>
				</div>
				<div id="page-bottom-content">
					<h3>Fettucini alfredo</h3>
					<p>
                        Ora per res Cum Suffoco in depono Conduco Ico amicabiliter fraudatio obsum. Humo to qui quin ne pes castellatim pertingo, leo te Subduco sol quaestuosus compaciscor is nex quotienscumque propinquo aptus, tam aetas, Arbor eia Subnuba, religiosus puteus, luo et quotienscumque angulus edo Monasteriense Turba hic Defaeco ymo vix Balatus convenienter sanctifico metus in per magnificabiliter ars divini.
					</p>
				</div>
				<br class="clearfix" />
			</div>
		</div>
		</div>
    	<div id="footer">
			&copy; 2013 Untitled | Design by <a href="http://www.regismagnus.com.br/">RTM</a> | Images by <a href="http://fotogrph.com/">Fotogrph</a>
		</div>
	</body>
</html>
