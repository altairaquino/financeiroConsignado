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
				&nbsp;   Dados do Contrato    &nbsp;
			</legend>
			<table style="width: 500px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados do Contrato
					</th>
				</tr>	
				<tr>
					<th>
						Corretor/Parceiro
					</th>
					<td style="color: red; font-weight: bold;">
						<bean:write name="contrato" property="ctcnman"/>										
					</td>
				</tr>	
				<tr>
					<th>
						Cliente
					</th>
					<td style="color: blue; font-weight: bold;">
						<bean:write name="contrato" property="ctcnmcl"/>
					</td>
				</tr>
				<tr>
					<th>
						Nº do Contrato
					</th>
					<td style="font-weight: bold;">
						<bean:write name="contrato" property="ctnnumr"/>						
					</td>
				</tr>
				<tr>
					<th> 
						Forma de Pagamento
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcfp"/>
					</td>
				</tr>
				<tr>
					<th> 
						Status
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcsc"/>
					</td>
				</tr>
				<logic:equal name="contrato" property="ctncgfp" value="2">
				<tr>
					<th> 
						Banco da O.P.
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcbc"/>&nbsp;<b>Agência da O.P.:</b>&nbsp;
						<bean:write name="contrato" property="ctcagen"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th> 
						Produto
					</th>
					<td>
						<bean:write name="contrato" property="ctcdcpd"/>
					</td>
				</tr>				
				<tr>
					<th>
						Tabela Produto
					</th>
					<td>
						<bean:write name="contrato" property="ctcdctp"/>					
					</td>
				</tr>
				<tr>
					<th>
						Valor do Contrato
					</th>
					<td>
						R$ <bean:write name="contrato" property="ctyvalr"/>				
					</td>
				</tr>
				<tr>
					<th>
						Data da Averbação
					</th>
					<td>
						<bean:write name="contrato" property="ctdverb"/>						
					</td>
				</tr>
				<tr>
					<th>
						Data do Pagamento
					</th>
					<td>
						<bean:write name="contrato" property="ctdpgto"/>						
					</td>
				</tr>				
				<tr>
					<th>
						Cadastrado por:
					</th>
					<th>
						<bean:write name="contrato" property="ctccadt"/>							
					</th>
				</tr>
				<tr>
					<th>
						Data e Hora
					</th>
					<th>
						<bean:write name="contrato" property="ctdcadt"/> - <bean:write name="contrato" property="cttcadt"/>							
					</th>
				</tr>
							
			</tbody>			
			</table>
			
		</fieldset>
		</div>		
	</div>
</body>
</html>