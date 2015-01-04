	<%@include file="topo.jsp" %>	
				
</head>
<body>

	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">
		<% BeanEntidade usuario = (BeanEntidade)session.getAttribute("usuario"); 
			request.setAttribute("ls_agencia", ModelEntidade.getInstance().getAgenciasDoSupervisor(Integer.parseInt(usuario.getEnncodg())));
		%>
		<html:errors/>
		<fieldset>
			<legend style="red">
				&nbsp;   Relatórios Sinergia Por Agência &nbsp;
			</legend>
			<logic:empty name="ls_agencia">
				<h3 style="color: #D00;">Usuário sem acesso a esta opção.</h3>
			</logic:empty>		
			
			<logic:notEmpty name="ls_agencia">
			<html:form action="actionContrato">
			<html:hidden property="m" value="relatoriosSinergiaSup"/>
			<center>
			<table style="width: 600px;">
			<tbody>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="background-color: #DDD; text-align: center;" colspan="2">
						Relatórios Sinergia por Agência
					</th>
				</tr>
				<tr>
					<td style="width: 25%;">
						Data Inicial
					</td>
					<td style="width: 75%;">
						<input type="text" id="data1" name="data1" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly">						
						<input type="image" id="b1" src="jscalendar/img.gif">
					</td>
				</tr>
				<tr>
					<td>
						Data Final
					</td>
					<td>
						<input type="text" name="data2" id="data2" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')" readonly="readonly">						
						<input type="image" id="b2" src="jscalendar/img.gif">						
					</td>
				</tr>
				<tr>
					<td>
						Agência Sinergia
					</td>
					<td>
						<html:select property="ctncgen" style="width: 350px;">
							<html:optionsCollection name="ls_agencia" value="enncodg" label="encnome"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<td>
						Tipo
					</td>
					<td>
						<select name="tipo" style="width: 400px;">
							<option value="1">Relatório de Comissões por Funcionário/Agência</option>
							<option value="2">Relatório de Produção Digitada</option>
							<option value="3">Relatório de Extornos de Contratos</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;" colspan="2">
						&nbsp;
					</td>
				</tr>				
				<tr>
					<td style="text-align: right;" colspan="2">
						<html:submit styleClass="btn_hot">Visualizar Relatório</html:submit>						
					</td>
				</tr>
			
			</tbody>
			</table>
			</center>	
			</html:form>
			</logic:notEmpty>
			
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	<logic:notEmpty name="ls_agencia">
	<script type="text/javascript">
	    Calendar.setup({
	        inputField     :    "data1",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b1",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    Calendar.setup({
	        inputField     :    "data2",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	</script>
	</logic:notEmpty>
</body>

<%@page import="com.grupoexata.bancario.struts.bean.BeanEntidade"%>
<%@page import="com.grupoexata.bancario.dao.ModelEntidade"%></html>