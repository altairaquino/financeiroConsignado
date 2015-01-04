	<%@include file="topo.jsp" %>
	<% request.setAttribute("ls_tipofuncagencia", com.grupoexata.bancario.dao.ModelTipoFuncAgencia.getInstance().getTiposFuncAgenciaTipo("S")); %>
				
</head>
<body>
	
	<div id="container">
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>
		
		<div id="content">		
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;   Comissão Sinergia > Lista    &nbsp;
			</legend>
			<center>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="6">
						<html:form action="/actionComissaoSinergia">
						<html:hidden property="m" value="lista"/>
						Comissões do Sinergia
						<html:select property="cmsncgtfc" onchange="this.form.submit();">
							<html:optionsCollection name="ls_tipofuncagencia" label="tfacdesc" value="tfancodg"/>
						</html:select>
						<html:submit styleClass="btn_hot">OK</html:submit>
						</html:form>
					</th>
				</tr>
				<tr>
					<td colspan="6" style="font-size: 14px;">
						CARGO: <B><bean:write name="tipofuncagencia" property="tfacdesc"/></B>				
					</td>
				</tr>
				<tr>
					<td colspan="6">
						&nbsp;	
					</td>
				</tr>
			</tbody>
			</table>
			<div style="width: 100%; height: 350px; overflow: auto;">
			<table style="width: 100%;">
			<tbody>
				<tr style="background: #D00; color: white; border-style: dotted;">
					<th style="font-size: 10px;" rowspan="2">
						Produto
					</th>
					<th style="font-size: 10px;" rowspan="2">
						Tabela
					</th>
					<th style="font-size: 10px;" rowspan="2">
						Prazo
					</th>
					<th style="font-size: 10px; text-align: center;" colspan="4">
						Comissões
					</th>					
				</tr>				
				<tr style="background: #D00; color: white; border-style: dotted;">
					<th style="font-size: 10px; text-align: center;">
						90-100%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						100-110%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						110-120%
					</th>					
					<th style="font-size: 10px; text-align: center;">
						+ 120%
					</th>					
				</tr>	
				<logic:empty name="ls_comissaosinergia">
				<tr>
					<td colspan="6" style="color: red;font-size: 10px;">
						Não há comissões para este cargo.
					</td>
				</tr>				
				</logic:empty>
				<logic:notEmpty name="ls_comissaosinergia">
				<html:form action="actionComissaoSinergia" focusIndex="0">
				<input type="hidden" name="cmsncgtfc" value="<bean:write name="tipofuncagencia" property="tfancodg"/>">
				<html:hidden property="m" value="update"/>
				<% int c = 0; %>
				<logic:iterate id="b" name="ls_comissaosinergia">
				<tr style="background-color: <%= (c++%2==0?"#DDD":"#FFF") %>">
					<html:hidden name="b" property="cmsncodg"/>
					<th style="font-size: 10px;">
						<bean:write name="b" property="cmscdcpd"/>
					</th>											
					<th style="font-size: 10px;">
						<bean:write name="b" property="cmscdctp"/>
					</th>
					<th style="font-size: 10px;">
						<bean:write name="b" property="cmsnparc"/> meses
					</th>				
					<th style="font-size: 10px;">
						<input type="text" name="cmsnperc" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="cmsnperc"/>" onfocus="if (this.value == '0,00'){this.value = '';}">		
					</th>
					<th style="font-size: 10px;">
						<input type="text" name="cmsnpc100" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="cmsnpc100"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
					<th style="font-size: 10px;">
						<input type="text" name="cmsnpc150" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="cmsnpc150"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
					<th style="font-size: 10px;">
						<input type="text" name="cmsnpc151" onkeydown="Formata(this,8,event,2)" style="text-align: right;" size="8" maxlength="8" value="<bean:write name="b" property="cmsnpc151"/>" onfocus="if (this.value == '0,00'){this.value = '';}">			
					</th>		
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="6" style="text-align: right;">
						<html:submit styleClass="btn_hot">Salva Comissões</html:submit>
					</td>
				</tr>		
				</html:form>
				</logic:notEmpty>
			</tbody>			
			</table>
			</div>
			</center>			
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
</body>
</html>