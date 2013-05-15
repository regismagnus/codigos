<?php

@ $permissao = $_SESSION["permissao"];

if($permissao==2){
header("Location: index.php");
}

@ $nome = $_POST['nome'];
@ $sexo = $_POST['sexo'];
@ $email = $_POST['email'];
@ $cpf = $_POST['cpf'];
@ $rg = $_POST['rg'];
@ $data = $_POST['data'];
@ $telefone = $_POST['telefone'];
@ $celular = $_POST['celular'];
@ $cep = $_POST['cep'];
@ $endereco = $_POST['endereco'];
@ $numero = $_POST['numero'];
@ $complemento = $_POST['complemento'];
@ $informacoes = $_POST['informacoes'];
@ $pais = $_POST['pais'];
@ $estado = $_POST['estado'];
@ $cidade = $_POST['cidade'];
@ $bairro = $_POST['bairro'];
@ $username = $_POST['username'];
@ $senha = $_POST['senha'];
@ $senhaconfirma = $_POST['senhaconfirma'];

//Juridico
@ $razao = $_POST['razao'];
@ $cnpj = $_POST['cnpj'];
@ $inscricao = $_POST['inscricao'];
@ $insento = ($_POST['insento']=="on" ? "1" : "0");

$sucesso=false;

if($username){

$conect = new conexao();
$conect->conectar();

$query = "SELECT login FROM usuario WHERE login='".$username."'";

$result = mysql_query($query);

if(mysql_num_rows($result)>=1){
 echo "Nome de usuario já existe.";
}else{

if($razao){
$query = "INSERT INTO usuario(razao, nome, email, cnpj, inscricaoEstadual, insento, nascimento, telResidencial, celular, cep, endereco,
numero, complemento, referencia, pais, estado, cidade, bairro, login, senha, tipo)
VALUES ('".$razao."', '".$nome."', '".$email."', '".$cnpj."', '".$inscricao."', '".$insento."', '".$data."', '".$telefone."', '".$celular."', '".$cep."', '".$endereco."',
'".$numero."', '".$complemento."', '".$informacoes."', '".$pais."', '".$estado."', '".$cidade."', '".$bairro."', '".$username."', '".$senha."', '2')";
}else{
$query = "INSERT INTO usuario(nome, sexo, email, cpf, rg, nascimento, telResidencial, celular, cep, endereco,
numero, complemento, referencia, pais, estado, cidade, bairro, login, senha, tipo)
VALUES ('".$nome."', '".$sexo."', '".$email."', '".$cpf."', '".$rg."', '".$data."', '".$telefone."', '".$celular."', '".$cep."', '".$endereco."',
'".$numero."', '".$complemento."', '".$informacoes."', '".$pais."', '".$estado."', '".$cidade."', '".$bairro."', '".$username."', '".$senha."', '2')";

}
$insert = mysql_query($query);

if($insert){
 echo "Cadastro realizado com sucesso.";
 $sucesso = true;
}else{
 echo "Erro ao realizar o cadastro.".mysql_error();
}

}
}

   if(!$sucesso){

@ $tipo = $_POST['tipo'];
?>
<h2>Novo usuário</h2>
<table border="0" width="100%">
<tr><td colspan="0">
<form method="POST" action="index.php?pagina=cadastrar">
Se cadastrar como? <input type="radio" name="tipo" value="fisica" onchange="submit()" <?php if(!$tipo) echo "checked"?>
<?php if($tipo && $tipo=="fisica"){echo "checked";} ?> /> Pessoa física <input type="radio" name="tipo" value="juridica" onchange="submit()"
<?php if($tipo && $tipo=="juridica"){echo "checked";} ?> /> Pessoa Jurídica
</form> </td></tr>
<form id="cadastrar" method="POST" action="index.php?pagina=cadastrar">
<?php if($tipo && $tipo=="juridica"){ ?><tr><td><label for="razao">*Razão social da empresa:</label> </td><td><input type="text" id="razao" name="razao" /></td><?php } ?>
<tr>
<td><?php if($tipo && $tipo=="juridica"){ ?>*Responsável:<?php }else{ ?>*Nome completo:<?php } ?> </td><td><input type="text" name="nome" /></td>
</tr>
<?php if(!$tipo || $tipo=="fisica"){ ?><tr><td>*Sexo:</td><td><input type="radio" name="sexo" value="0" />Masculino <input type="radio" name="sexo" value="1" />Feminino </td></tr> <?php }?>
<tr>
<td><?php if($tipo && $tipo=="juridica"){ ?>*Email do responsável:<?php }else{ ?>*Seu email:<?php } ?></td><td><input type="text" name="email" /></td>
</tr>
<tr>
<td><?php if($tipo && $tipo=="juridica"){ ?>*CNPJ:</td><td><input type="text" name="cnpj" /></td>
</tr>
<tr>
<td>*Inscrição estadual:</td><td><input type="text" name="inscricao" /><input type="checkbox" name="insento" />Isento</td>
<?php }else{?>*CPF:</td><td><input type="text" name="cpf" /></td>
</tr>
<tr>
<td>*RG:</td><td><input type="text" name="rg" /></td><?php } ?>
</tr>
<tr>
<td><?php if($tipo && $tipo=="juridica"){ ?>*Data de abertura da empresa: <?php }else{ ?>*Data de nascimento:<?php }?></td>
<td><input type="text" name="data" /></td>
</tr>
<tr>
<td>Telefone:</td><td><input type="text" name="telefone" /></td>
</tr>
<tr>
<td>Telefone celular:</td><td><input type="text" name="celular" /></td>
</tr>
<tr>
<td>*CEP:</td><td><input type="text" name="cep" /></td>
</tr>
<tr>
<td>*Endereço:</td><td><input type="text" name="endereco" /></td>
</tr>
<tr>
<td>*Número:</td><td><input type="text" name="numero" /></td>
</tr>
<tr>
<td>Complemento:</td><td><input type="text" name="complento" /></td>
</tr>
<tr>
<td>Informações de referência:</td><td><textarea name="informacoes"></textarea></td>
</tr>
<tr>
<td>*País:</td><td><select name="pais"><option value="brasil">Brasil</select></td>
</tr>
<tr>
<td>*Estado:</td><td><input type="text" name="estado" /></td>
</tr>
<tr>
<td>*Cidade:</td><td><input type="text" name="cidade" /></td>
</tr>
<tr>
<td>*Bairro:</td><td><input type="text" name="bairro" /></td>
</tr>
<tr>
<td>*Login ou Email de acesso:</td><td><input type="text" name="username" /></td>
</tr>
<tr>
<td>*Senha de acesso:</td><td><input type="password" name="senha" /></td>
</tr>
<tr>
<td>*Repita senha:</td><td><input type="password" name="senhaconfirma" /></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Cadastrar" /></td>
</form>
</table>
<?php }?>
