<%@include file="../../topo.jsp" %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="../../menu.jsp" %>
		<%@include file="../../header.jsp" %>		
		
		<div id="content">		
		<html:errors/>
		<logic:present name="msg">
		<font style="color: red;font-weight: bold;">
			<bean:write name="msg"/>
		</font>
		</logic:present>
		<fieldset>
			<legend>
				&nbsp;IMPORTAÇÃO DE ARQUIVOS GCOM DO NETCERTO  &nbsp;
			</legend>
			<table>
			<tbody>
				<tr>
					<td colspan="4">
					
						Enviar Arquivo de Importação do NETCERTO. (GCOM)<br>
						<html:form action="actionLayout" focus="arquivo" method="post" enctype="multipart/form-data">
							<html:hidden property="m" value="uploadArquivo"/>
							<html:file property="arquivo" size="40"/>
							<input type="button" value="Enviar" onclick="if (this.form.arquivo.value == '') {alert('Escolha o arquivo antes de enviar!'); } else { this.form.submit();}">
						</html:form>
						<font color="red">
							OBS: O arquivo a ser enviado deve ser o do formato TXT do GCOM.
						</font> 
					</td>
				</tr>
			</tbody>
			</table>
			<%--
			<%
				String diretorio = getServletConfig().getServletContext().getRealPath("/retornos/");
				ManipulaArquivo dir = new ManipulaArquivo();
				List<File> listfile = new ArrayList<File>();
				request.setAttribute("arquivos",	dir.getFiles(diretorio));							
			%>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table>
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
				<logic:empty name="arquivos">
				<tr>
					<td colspan="4" style="color: red; font-weight: bold;">
						NÃO HÁ ARQUIVOS ANEXADOS
					</td>
				</tr>
				</logic:empty>
				<logic:iterate id="b0001" indexId="i" name="arquivos">
				<tr style="background-color: <%= (i%2==1?"#DDD":"#FFF") %>">
					<td style="font-size: 12px; font-weight: bold;">
						<%= (((File)b0001).getName()) %>
					</td>
					<td>
						<%= (((File)b0001).length()) %> Bytes
					</td>
					<td>
						<%= (new FormataObj().formataData(new Date(((File)b0001).lastModified()))) %>
					</td>
					<td>
						<input type="button" value="Download" onclick="window.open('<%= ("retornos/"+((File)b0001).getName()) %>')">						
					</td>
				</tr>
				</logic:iterate>
				
			</tbody>
			</table>
			</div>
				--%>
		</fieldset>
			
		</div>
		
		<%@include file="../../footer.jsp" %>
	</div>
</body>

<%@page import="com.grupoexata.bancario.struts.bean.BeanContrato"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Date"%>
<%@page import="com.grupoexata.bancario.utils.ManipulaArquivo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.grupoexata.bancario.utils.FormataObj"%></html>