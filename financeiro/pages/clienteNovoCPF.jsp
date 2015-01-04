	<%@include file="topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	
	<script type="text/javascript">
	<!--
		function validaCPF(frm){
			if (!IsCPF(frm.encdocm.value)){
				alert('CPF digitado é inválido!');
				return false;
			}
			return true;
		}
	//-->
	</script>
			
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">			
		
		<fieldset>
			<legend>
				&nbsp;   Cadastro de Cliente    &nbsp;
			</legend>
			<html:errors/>
			<html:form action="actionEntidade" focus="encdocm" onsubmit="return validaCPF(this)">
			<html:hidden property="m" value="verficaCPFCliente"/>
			<table style="width: 220px;" align="center">	
			<tbody>
				<tr>
					<td colspan="2"> &nbsp; </td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">					 
						Informe o C.P.F. do Cliente
					</th>
				</tr>
				<tr>
					<td>
						C.P.F.
					</td>
					<td style="text-align: center;">
						<html:text property="encdocm" maxlength="14" size="15" onkeyup="criaMascara(this, '###.###.###-##');"></html:text>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">			
						<html:submit styleClass="btn">Avançar</html:submit>						
					</td>
				</tr>
			</tbody>			
			</table>	
			</html:form>
		</fieldset>			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>