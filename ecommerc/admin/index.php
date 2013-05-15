<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Regis Magnus
http://www.regismagnus.com.br
2013
-->
<?php

   session_start();
  @ $pagina = $_GET['pagina'];
?>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<title>Accumen by Free CSS Templates</title>
		<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />
		<link href="http://fonts.googleapis.com/css?family=Kreon" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
    	<div id="line-top">
                  <div id="line-top-inside">

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
                      <?php require("menutop.php"); ?>
					<br class="clearfix" />
			</div>
			<div id="page-top">
                 <div id="page-top-inside-left">
                      Search
                 </div>
                 <div id="page-top-inside-center">
                       <form method="POST" action="<?php echo $_SERVER ['REQUEST_URI']; ?>">
                              <select style="width: 150px"  disabled >
                                      <option <?php if(isset($pagina) && $pagina=="lista"){echo " selected ";} ?>>Listar Produtos</option>
                                      <option <?php if(isset($pagina) && $pagina=="listacategoria"){ echo " selected "; } ?> >Listar Categorias</option>
                                      <option <?php if(isset($pagina) && $pagina=="listasubcategoria"){echo " selected ";} ?>>Listar Subcategoria</option>
                                      <option <?php if(isset($pagina) && $pagina=="pedido"){echo " selected ";} ?>>Pedidos</option>
                       </select>
                              <input type="text" style="width: 300px" name="busca" id="busca" />
                              <input type="button" value="Search" class="button" onclick="submit()"/>
                       </form>
                 </div>
                 <div id="page-top-inside-right">
                 
                 </div>
			</div>
			<div id="page-top-second">
                              Areá Administrativa: <?php if(isset($pagina)){ echo ucwords($pagina); } else { echo "Lista de Produtos"; }  ?>
			</div>
			<div id="page">
				<div id="sidebar">
                    <div class="box">
                          <h3>Operações</h3>
                           <?php require("menu.php"); ?>
                    </div>
				</div>
				<div id="content">
					<div class="box">
                <?php
                 if($pagina){
                            if($pagina=="cadastro"){
                             require("cadastroproduto.php");
                            }elseif($pagina=="lista"){
                             require("listaproduto.php");
                            }elseif($pagina=="pedido"){
                             require("pedido.php");
                            }elseif($pagina=="salvar"){
                             require("salvarproduto.php");
                            }elseif($pagina=="categoria"){
                             require("categoria.php");
                            }elseif($pagina=="subcategoria"){
                             require("subcategoria.php");
                            }elseif($pagina=="listacategoria"){
                             require("listacategoria.php");
                            }elseif($pagina=="listasubcategoria"){
                             require("listasubcategoria.php");
                            }
                }else{
                 require("listaproduto.php");
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
