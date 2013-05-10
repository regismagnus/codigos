<?php

$caracteres = array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","w","y","z",
                  "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z",
                  "1","2","3","4","5","6","7","8","9","0","!","@","#","$","%","^","&","*","?");
                  
$ordem = $caracteres;
shuffle($ordem);

//Criptografar palavra, retorna ja criptografada
$cripto = "//Funcao para criptografar a palavra. Parametro palavra-senha. return palavra criptografada.
function criptografar(\$word){

global \$ordem, \$caracteres;
\$criptografado = \"\";
\$letter = str_split(\$word);

foreach(\$letter as \$l){
      \$criptografado.= \$caracteres[array_search(\$l, \$ordem)];
}

return \$criptografado;
}";

$descripto = "//Funcao para descriptografar a palavra. Parametro palavra-senha criptografada. return palavra descriptografada.
function descriptografar(\$word){
global \$ordem, \$caracteres;
\$descriptografado = \"\";
\$letter = str_split(\$word);

foreach(\$letter as \$l){
      \$descriptografado.=\$ordem[array_search(\$l, \$caracteres)];
}

return \$descriptografado;

}";


  echo "<pre>//Caracteres que pode ter a palavra-senha
   \$caracteres = array(";

foreach($caracteres as $l){
     echo "\"".$l."\",";
}

  echo ") <br /> <br />" ;

  echo "//Array com a reordenacao dos caracteres
  \$ordem = array(";

foreach($ordem as $l){
     echo "\"".$l."\",";
}

  echo ") <br /><br />" ;
  
  echo nl2br($cripto). "<br /><br />";
  echo nl2br($descripto)." </pre>";

?>
