<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html>
<head>
	<title><bean:message key="welcome.title"/></title>
	
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="content-style-type" content="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta name="language" content="pt-br">
		
	<link rel="stylesheet" href="stylesheet/style.css" type="text/css" media="screen, projection">
	
</head>
<body style="margin: 0px; overflow: hidden;">
	
	<div id="container" style="width: 500px;">
		<div id="content" >
		<fieldset>
			<legend>
				&nbsp;   Dados da Entidade    &nbsp;
			</legend>
			<input type="button" value="Imprimir" onclick="window.focus(); window.print();">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados da Entidade (<bean:write name="entidade" property="encdcte"/>)
					</th>
				</tr>
				<tr>
					<th style="width: 25%">
						Nome
					</th>
					<td style="width: 75%">
						<bean:write name="entidade" property="encnome"/>
					</td>
				</tr>
				<tr>
					<th>
						Tipo
					</th>
					<td>
						<bean:write name="entidade" property="encdcpp"/>
					</td>
				</tr>				
				<tr>
					<th>
						<bean:write name="entidade" property="enctpdc"/>
					</th>
					<td>
						<bean:write name="entidade" property="encdocm"/>
					</td>
				</tr>
				<tr>
					<th>
						Data Nascimento
					</th>
					<td>
						<bean:write name="entidade" property="endnasc"/>
					</td>
				</tr>
				<logic:notEmpty name="entidade" property="encrg">
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Dados do RG
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<b>Nº RG:</b>&nbsp;<bean:write name="entidade" property="encrg"/> - 
						&nbsp;<b>Data de Exp.:</b>&nbsp;<bean:write name="entidade" property="endexrg"/> - 
						&nbsp;<b>Org. Exp.:</b>&nbsp;<bean:write name="entidade" property="encoerg"/> - 
						&nbsp;<b>UF:</b>&nbsp;<bean:write name="entidade" property="encufrg"/>
						<br>
						<b>Doc. Origem:</b>&nbsp;<bean:write name="entidade" property="encdorg"/>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;">
						Filiação
					</th>
				</tr>
				<tr>
					<th>
						Nome da Mãe
					</th>
					<td>
						<bean:write name="entidade" property="encmae" />
					</td>
				</tr>
				<tr>
					<th>
						Nome do Pai
					</th>
					<td>
						<bean:write name="entidade" property="encpai"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Contatos 
					</th>
				</tr>
				<tr>
					<th>
						Fone Fixo
					</th>
					<td>
						<bean:write name="entidade" property="encfone"/>
					</td>
				</tr>
				<tr>
					<th>
						Celular
					</th>
					<td>
						<bean:write name="entidade" property="enccell"/>
					</td>
				</tr>
				<tr>
					<th>
						E-mail
					</th>
					<td>
						<bean:write name="entidade" property="encmail"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;background-color: #DDDDDD;"> 
						Endereço 
					</th>
				</tr>
				<tr>
					<th>
						Logradouro
					</th>
					<td>
						<bean:write name="entidade" property="encdctl"/>
						<bean:write name="entidade" property="enlendr"/>						
					</td>
				</tr>
				<tr>
					<th>
						Estado
					</th>
					<td>
						<bean:write name="entidade" property="encufcd"/>										
					</td>
				</tr>
				<tr>
					<th>
						Cidade
					</th>
					<td>
						<div id="cidade">
						<bean:write name="entidade"  property="encdccd"/>
						</div>
					</td>
				</tr>				
				<tr>
					<th>
						CEP/Bairro
					</th>
					<td>
						<bean:write name="entidade" property="enccep"/>
						<bean:write name="entidade" property="encbair"/>
					</td>
				</tr>						
			</tbody>			
			</table>
		</fieldset>
		</div>		
	</div>
</body>
</html>