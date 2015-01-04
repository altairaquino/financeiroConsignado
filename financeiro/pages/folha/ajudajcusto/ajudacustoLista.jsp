<%@include file="/pages/topo.jsp" %>
<%@ page import="com.grupoexata.folha.dao.ModelEmpregado" %>
<%@page import="com.grupoexata.folha.dao.ModelTipoajcust"%>
<script type="text/javascript" src="scripts/jquery.js"></script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<logic:present name="msg">
		<font style="color: red; font-weight: bold;">
		<bean:write name="msg"/>
		</font>
		</logic:present>
		<%
		Map<String,BeanAjudajcusto> map = (HashMap<String,BeanAjudajcusto>)request.getAttribute("mapajudacusto");
		%>
		<fieldset>
			<legend>
				&nbsp; Relação de empregados&nbsp;
			</legend>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<html:form action="/actionAjudacusto">
			<input type="hidden" name="m" value="cadastro">
			<table style="width: 100%">
			<tbody>
				<tr>
					<th colspan="3" align="right"> <input class="btn_hot" type="submit" value="Enviar"></th>
				</tr>
				<tr>
					<th colspan="3" style="text-align: center; background-color: #DDDDDD;">  Relação de empregados </th>
				</tr>
                <tr>
					<th>
						Nome
					</th>
				<logic:iterate id="a001" name="listatipoajcust">
					<th>
						<bean:write name="a001" property="taccdesc"/>
					</th>
				</logic:iterate>
				</tr>
				<logic:empty name="listaempregado">
					<th>
						 Não há empregados Cadastrados.
					</th>
				</logic:empty>
				<logic:iterate id="b001" name="listaempregado"  indexId="d">
				<tr style="background-color: <%=(d++%2==0?"#f0f0f0":"transparent") %>">
					<td>
						<logic:empty name="b001" property="empncodg">
						<a href="actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="b001" property="enncodg"/>">
						</logic:empty>
						<bean:write name="b001" property="encnome"/>
						<logic:empty name="b001" property="empncodg">
						</a>
						</logic:empty>
					</td>
					<logic:iterate id="a001" name="listatipoajcust">
					<td align="center">
						<logic:empty name="b001" property="empncodg">
						<input name="xxx" value="XXX" disabled="disabled" maxlength="10" size="10" style="text-align: center; width: 120px;">
						</logic:empty>
						<logic:notEmpty name="b001" property="empncodg">
						<%
						BeanTipoajcust tipoajcust =	(BeanTipoajcust)a001;
						BeanEntidadeEmpregado empregado = (BeanEntidadeEmpregado)b001;
						String nome = "ajc_" + empregado.getEmpncodg() + "_" + tipoajcust.getTacncodg();
						BeanAjudajcusto ajudajcusto = map.get(nome);
						String valor = "";
						if(ajudajcusto != null){
							valor = ajudajcusto.getAjcyvalor();
						}
						%>
						<input type="text" name="<%=nome%>" value="<%=valor%>" maxlength="10" size="10" style="text-align: right;" onkeydown="Formata(this,10,event,2)">
						<% if(!valor.equals("")) {%>
						<input type="hidden" name="a<%=nome%>" value="<%=ajudajcusto.getAjcncodg()%>">
						<input type="hidden" name="v<%=nome%>" value="<%=valor%>">
						<%} %>
						</logic:notEmpty>
					</td>
				</logic:iterate>
					</tr>
				</logic:iterate>
				 <tr>
					<th colspan="3" align="top"><hr></th>
				</tr>
				<tr>
					<td colspan="3">Observações:<br><b>XXX</b> - Sem dados funcionais</td>
				</tr>
				<tr>
					<th colspan="3" align="right"> <input class="btn_hot" type="submit" value="Enviar"></th>
				</tr>
			</tbody>
			</table>
			</html:form>
			</div>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
<%@page import="com.grupoexata.folha.bean.BeanAjudajcusto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.grupoexata.folha.bean.BeanTipoajcust"%>
<%@page import="com.grupoexata.folha.bean.BeanEntidadeEmpregado"%>
</html>