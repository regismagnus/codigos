<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Regis Magnus
http://www.regismagnus.com.br
-->

<?php
   session_start();
  @ $pagina = $_GET['pagina'];
  require_once("../src/util/conexao.php");
?>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
		<meta name="description" content="Blog RTM" />
		<meta name="keywords" content="blog" />
		<title>Blog - RTM</title>
		<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css" />
		<link href="http://fonts.googleapis.com/css?family=Kreon" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="../layout/style.css" />

        <script src="jquery.js" type="text/javascript"></script>
         <script src="jquery.validate.js" type="text/javascript"></script>

	</head>
	<body>
		<div id="wrapper">
             <div id="superiorline">
                   <?php
                     require("topline.php");
                   ?>
             </div>
			<div id="header">
                            <?php
                              require("cabecalho.php");
                            ?>
			</div>
			<div id="page">
				<div id="sidebar">
                            <?php
                              require("sidebar.php");
                            ?>
				</div>
				<div id="content">

                        <?php
                          if(isset($pagina)){
                          
                          if($pagina=="cadastro"){
                             require("cadastro.php");
                          }elseif($pagina=="principal"){
                             require("principal.php");
                          }elseif($pagina=="post"){
                             require("post.php");
                          }elseif($pagina=="gerenciar"){
                             require("gerenciar.php");
                          }
                          
                          }else{
                             echo "Para mais informações visite: <a href='http://regismagnus.wordpress.com/'>http://regismagnus.wordpress.com</a>";
                          }
                        ?>

					<br class="clearfix" />
				</div>
				<br class="clearfix" />
			</div>
			<div id="page-bottom">
				<div id="page-bottom-sidebar">
					<h3>Implementações</h3>
					<ul class="list">
						<li class="first">Registro</li>
						<li>Posts</li>
						<li class="last">Visualização de posts</li>
					</ul>
				</div>
				<div id="page-bottom-content">
					<h3>Blog RTM</h3>
					<p>
                             Blog RTM versão Alpha. "Este blog está em desenvolvimento, mais funções seram inseridas em versões futuras. Obrigado por utilizar ele." (Régis Magnus)
					</p>
				</div>
				<br class="clearfix" />
			</div>
		</div>
		<div id="footer">
			&copy; 2013 | Design by <a href="http://www.regismagnus.com.br/">RTM</a> | Images by <a href="http://fotogrph.com/">Fotogrph</a>
		</div>
	</body>
</html>
