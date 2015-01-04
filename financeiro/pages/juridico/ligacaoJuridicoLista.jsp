	<%@include file="../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Relação de Ligações > <bean:write name="contato" property="encnome"/>  &nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="4" style="background-color: #DDD; text-align: center;"> 
						Registro de Últimas Ligações Efetuadas
					</th>
				</tr>
				<tr>
					<th>
						Data
					</th>
					<th>
						Responsável
					</th>
					<th>
						Observações/Ocorrências
					</th>										
					<th>
						E-mail
					</th>										
				</tr>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_ligacaojuridico">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<td style="vertical-align: top; width: 12%;">
						<bean:write name="b" property="lgjddata"/>
					</td>
					<td style="vertical-align: top; width: 30%;">
						<bean:write name="b" property="lgjcnmus"/>
					</td>
					<td style="width: 48%;">
						<bean:write name="b" property="lgjmobsv"/>
					</td>					
					<td style="width: 10%;">
						<logic:equal name="b" property="lgjlmail" value="T">Sim</logic:equal>						
						<logic:equal name="b" property="lgjlmail" value="F">Não</logic:equal>						
					</td>					
				</tr>
				</logic:iterate>
				<tr>
					<th colspan="4">
						<input type="button" class="btn" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesContatoJuridico&enncodg=<bean:write name="contato" property="enncodg"/>'">
					</th>
				</tr>				
			</tbody>			
			</table>	
			</div>
		</fieldset>
		
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>