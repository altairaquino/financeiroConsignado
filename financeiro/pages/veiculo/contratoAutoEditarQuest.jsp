	<%@include file="../topo.jsp" %>
	<script type="text/javascript" src="scripts/jquery.js"></script>	
			
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">			
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Questionário de Satisfação do Cliente    &nbsp;
			</legend>
			<html:form action="actionContratoAuto" onsubmit="return window.confirm('Confirmar a inclusão dos dados fornecidos?')">
			<html:hidden property="m" value="updateQuest"/>
			<html:hidden property="ctancodg"/>
			<table style="width: 550px;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Questionário de Satisfação do Cliente
					</th>
				</tr>
				<tr>
					<th colspan="2">
						1ª) Cliente recebeu o carnê? (Caso não tenha recebido o carnê em 15 dias o sistema volta a lembrar.)
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:radio property="ctalcarn" value="T">Sim</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="ctalcarn" value="F">Nao</html:radio>&nbsp;&nbsp;	
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						2ª) Cliente satisfeito com o atendimento da loja?
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:radio property="ctalaten" value="T">Sim</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="ctalaten" value="F">Nao</html:radio>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						3ª) Cliente satisfeito com o veículo?
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:radio property="ctalveic" value="T">Sim</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="ctalveic" value="F">Nao</html:radio>					
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						4ª) Cliente tem algum amigo ou parente para indicar ao Banco, sabendo que isso pode gerar uma comissão em cima da operação de financiamento?
					</th>
				</tr>
				<tr>
					<td colspan="2">
						<html:radio property="ctalindi" value="T">Sim</html:radio>&nbsp;&nbsp;&nbsp;
						<html:radio property="ctalindi" value="F">Nao</html:radio>				
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>					
				<tr>
					<th style="vertical-align: top; background-color: #DDD;" colspan="2">
						Dados da indicação para que possa ser feito o contato se houver.										
					</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<html:textarea property="ctacobin" cols="65" rows="2"></html:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<html:submit styleClass="btn_hot">Registrar Dados</html:submit>
					</td>
				</tr>				
				
			</tbody>
			</table>
			</html:form>
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
	
</body>
</html>