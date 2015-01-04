<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> Consignado Web </TITLE>
	<link rel="shortcut icon" href="dinheiro.ico" type="image/x-icon">
	<link rel="STYLESHEET" type="text/css" href="stylesheet/style.css">
<style type="text/css">
	#divcentral {
		margin-top: -50px;
		margin-left: -50px;
		left: 45%;
		top: 30%;
		position: fixed;
		text-align: center;
	}
</style>
</HEAD>
<BODY>
	<div id="divcentral">
		<img src="imagens/logo_exata.gif" border="0"><br>
		<form method="post" action="actionLogin.do" name="formLogin" id="formLogin" onsubmit="if (navigator.appName == 'Netscape'){return true;}else{alert('Por medidas de seguran?a e para um melhor funcionamento do sistema, \nutilize o navegador Mozila Firefox.'); return false;}">
		<input name="m" value="autenticaUsuario" type="hidden">
		<table border="0" cellpadding="2" cellspacing="0" style="width: 240px; height: 120px; background-color: #F0FFFF; border: #5F9EA0 2px solid;">
			<tbody>
				<tr>
					<th style="text-align: center; background-color: #191970; color: #FFF;" colspan="2">
						Consignado Web
					</th>
				</tr>
				<tr>
					<td style="font-weight: bold; text-align: right;">
						Usuário: &nbsp;
					</td>
					<td>
						<input name="login" style="width: 100px;" size="10" maxlength="15" class="input2" type="text">
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold; text-align: right;">
						Senha:&nbsp;
					</td>
					<td>
						<input name="senha" style="width: 100px;" size="10" class="input2" maxlength="15" type="password">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						<input type="submit" value="Entrar">					
						<!-- 
						<input type="button" value="Entrar" onclick="if (this.form.login.value == '' || this.form.senha.value == ''){alert ('Informe Login e Senha.'); return false; }else{return true;}">
						<input type="image" src="imgsite/btn_topo_ok.gif" >
						 -->
					</td>
				</tr>
			</tbody>
			</table>
		</form>
	</div>
</BODY>
</HTML>
