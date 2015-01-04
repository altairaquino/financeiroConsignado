	<%@include file="topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>		
		
		<div id="content">		
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;  <bean:write name="contrato" property="ctnnumr"/> > LISTA DE ARQUIVOS ANEXADOS  &nbsp;
			</legend>
			<table style="width: 100%">
			<tbody>
				<tr>
					<td colspan="4">
						<input type="button" value="Voltar" onclick="window.location = 'contratoPesquisa2.do'">
					</td>
				</tr>
				<tr>
					<th colspan="4" style="text-align: center; font-weight: bold; background-color: #DDD;">
						Dados do contrato
					</th>
				</tr>
				<tr>
					<td colspan="4">
						<b>Agente de Crédito:</b> <bean:write name="contrato" property="ctcnman"/><br>
						<b>Cliente:</b> <bean:write name="contrato" property="ctcnmcl"/><br>
						<b>Valor do Contrato:</b> <bean:write name="contrato" property="ctyvalr"/><br>
						<b>Status:</b> <bean:write name="contrato" property="ctcdcsc"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						Anexar documento ao contrato.<br>
						<html:form action="actionContrato" focus="arquivo" method="post" enctype="multipart/form-data">
							<input type="hidden" name="ctncodg" value="<bean:write name="contrato" property="ctncodg"/>">
							<html:hidden property="m" value="uploadArquivo2"/>
							<html:file property="arquivo" size="40"/>
							<input type="button" value="Enviar" onclick="if (this.form.arquivo.value == '') {alert('Escolha o arquivo antes de enviar!'); } else { this.form.submit();}">
						</html:form>
						<font color="red">
							OBS: ao enviar arquivos evite colocar acentuação e arquivos muito grandes. (Limite de 1mb por arquivo)<br>
							Para facilitar a identificação dos documentos, coloque o nome do arquivo referente ao documento anexado.
						</font> 
					</td>
				</tr>
			</tbody>
			</table>
			
			<%
				BeanContrato contrato = (BeanContrato)request.getAttribute("contrato");
				String realPath = getServletConfig().getServletContext().getRealPath("/documentos/");
				String diretorio = realPath + "/"+contrato.getCtnnumr();
			
				File dir = new File(diretorio);
				
				File fList[] = dir.listFiles();
							
			%>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%">
			<tbody>
				<tr style="background-color: #DDD; font-weight: bold; ">
					<th style="width: 70%;">
						Nome do Arquivo
					</th>
					<th style="width: 10%;">
						Tamanho
					</th>
					<th style="width: 10%;">
						Data
					</th>
					<th style="width: 10%;">
						Download
					</th>
				</tr>
				<% if (dir.exists()){ %>
				
				<% if (fList.length == 0){ %>
				<tr>
					<td colspan="4" style="color: red; font-weight: bold;">
						NÃO HÁ ARQUIVOS ANEXADOS
					</td>
				</tr>
				<% } %>
				
				<%
				for (int i = 0; i < fList.length; i++ ){
				%>
				<tr style="background-color: <%= (i%2==1?"#DDD":"#FFF") %>">
					<td style="font-size: 12px; font-weight: bold;">
						<%= (fList[i].getName()) %>
					</td>
					<td>
						<%= (fList[i].length()) %> Bytes
					</td>
					<td>
						<%= (new FormataObj().formataData(new Date(fList[i].lastModified()))) %>
					</td>
					<td>
						<input type="button" value="Download" onclick="window.open('<%= ("documentos/"+contrato.getCtnnumr()+"/"+fList[i].getName()) %>')">						
					</td>
				</tr>
				<% }} %>
				
			</tbody>
			</table>
			</div>
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>

<%@page import="com.grupoexata.bancario.struts.bean.BeanContrato"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Date"%>
<%@page import="com.grupoexata.bancario.utils.FormataObj"%></html>