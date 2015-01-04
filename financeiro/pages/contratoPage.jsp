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
				&nbsp;   Opções do Contrato  > (<bean:write name="contrato" property="ctnnumr"/> - R$ <bean:write name="contrato" property="ctyvalr"/>)  &nbsp;
			</legend>
			<table style="width: 100%" cellpadding="20">
			<tbody>
				<tr>
					<th colspan="2">
						Cliente: <bean:write name="contrato" property="ctcnmcl"/>
					</th>
				</tr>
				<tr>
					<th colspan="2">
						Agente de Crédito: <bean:write name="contrato" property="ctcnman"/>
					</th>
				</tr>				
				<tr>
					<th colspan="2">
						Status: <bean:write name="contrato" property="ctcdcsc"/>
					</th>
				</tr>
				<logic:equal name="contrato" property="ctlfisi" value="T">
				<tr>
					<th colspan="2">
						Baixa de Físico em <bean:write name="contrato" property="ctdbxfi"/> por <bean:write name="contrato" property="ctcusbx"/>
					</th>
				</tr>
				</logic:equal>
				<tr>
					<th colspan="2">
						Data de Cadastro: <bean:write name="contrato" property="ctdcadt"/> - <bean:write name="contrato" property="cttcadt"/>
					</th>
				</tr>
				<logic:notEmpty name="contrato" property="ctdsqop">
				<tr>
					<th colspan="2">
						Data de Confirmação de O.P.: <bean:write name="contrato" property="ctdsqop"/> por <bean:write name="contrato" property="ctcsqop"/>. 
					</th>
				</tr>
				</logic:notEmpty>
				<tr>
					<th colspan="2" style="background-color: #DDD; text-align: center;">
						Opções do Contrato
					</th>
				</tr>
				<tr>
					<td style="width: 60%;">
						Dados do Contrato
					</td>
					<td style="width: 40%;">
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContrato.do?m=dados&ctncodg=<bean:write name="contrato" property="ctncodg"/>'">
					</td>
				</tr>
				<logic:equal name="contrato" property="ctlativ" value="T">
				<tr>
					<td>
						Registra data de Estorno
					</td>
					<td style="color: red; font-weight: bold;">
						<logic:notEmpty name="contrato" property="ctdextn">
							<bean:write name="contrato" property="ctdextn"/>
						</logic:notEmpty>
						<logic:empty name="contrato" property="ctdextn">
							<html:form action="actionContrato" onsubmit="return window.confirm('Confirmar o extorno do Contrato para a data Informada?')">
							<html:hidden property="m" value="registraExtorno"/>
							<html:hidden name="contrato" property="ctncodg"/>
								Data: <html:text property="ctdextn" styleId="ctdextn" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')"/>
							<input type="image" id="b2" src="jscalendar/img.gif">
							<html:submit styleClass="btn_hot">Registrar</html:submit>
						</html:form>					
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td>
						Registra data de Averbação
					</td>
					<td style="color: red; font-weight: bold;">										
						<logic:notEmpty name="contrato" property="ctdverb">
							<bean:write name="contrato" property="ctdverb"/>
						</logic:notEmpty>
						<logic:empty name="contrato" property="ctdverb">
						<logic:equal name="contrato" property="ctncgsc" value="8">
							<bean:write name="contrato" property="ctcdcsc"/>
						</logic:equal>
						<logic:notEqual name="contrato" property="ctncgsc" value="8">
						<logic:empty name="contrato" property="ctdextn">
						<html:form action="actionContrato" onsubmit="return window.confirm('Confirmar a averbação do Contrato para a data informada?')">
							<html:hidden property="m" value="registraAvervacao"/>
							<html:hidden name="contrato" property="ctncodg"/>
								Data: <input type="text" name="ctdverb" id="ctdverb" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
							<input type="image" id="b3" src="jscalendar/img.gif">
							<html:submit styleClass="btn_hot">Registrar</html:submit>
						</html:form>
						</logic:empty>
						</logic:notEqual>
						</logic:empty>
					
					</td>
				</tr>
				<logic:notEmpty name="contrato" property="ctdverb">
				<tr>
					<td>
						Registra data de Pagamento
					</td>
					<td style="color: red; font-weight: bold;">	
					<logic:equal name="contrato" property="ctncgsc" value="8">
						<bean:write name="contrato" property="ctcdcsc"/>
					</logic:equal>
					<logic:notEqual name="contrato" property="ctncgsc" value="8">
						<logic:notEmpty name="contrato" property="ctdpgto">
							<bean:write name="contrato" property="ctdpgto"/>
						</logic:notEmpty>
						<logic:empty name="contrato" property="ctdpgto">
						<logic:empty name="contrato" property="ctdextn">
						<html:form action="actionContrato" onsubmit="return window.confirm('Confirmar o pagamento do Contrato para a data informada?')">
							<html:hidden property="m" value="registraPagamento"/>
							<html:hidden name="contrato" property="ctncodg"/>
								Data: <input type="text" name="ctdpgto" id="ctdpgto" size="11" maxlength="10" onkeyup="criaMascara(this,'##/##/####')">
							<input type="image" id="b4" src="jscalendar/img.gif">
							<html:submit styleClass="btn_hot">Registrar</html:submit>
						</html:form>
						</logic:empty>
						</logic:empty>
					</logic:notEqual>
					</td>
				</tr>
				</logic:notEmpty>
				</logic:equal>
				<%-- 
				<logic:equal name="contrato" property="ctlativ" value="T">
				<tr>
					<td>
						Liberar contrato para pagamento
					</td>
					<td style="color: red; font-weight: bold;">
						<logic:notPresent name="negativo">
							<logic:equal value="6" name="contrato" property="ctncgsc">
								<bean:write name="contrato" property="ctcdcsc"/>
							</logic:equal>
							<logic:notEqual value="T" name="contrato" property="ctlpgcm">
								<logic:equal value="8" name="contrato" property="ctncgsc">
									<bean:write name="contrato" property="ctcdcsc"/>
								</logic:equal>
								<logic:notEqual value="8" name="contrato" property="ctncgsc">
									<input type="button" class="btn_hot" value="Registrar" onclick="if (window.confirm('Confirmar a liberação do Contrato para Pagamento?\nObs: Após Finalizar não poderá ser revertido.')){window.location = 'actionContrato.do?m=finalizaContrato&ctncodg=<bean:write name="contrato" property="ctncodg"/>'}">
								</logic:notEqual>
							</logic:notEqual>
						</logic:notPresent>
						<logic:present name="negativo">
							Spread Negativo. Reveja comissões.
						</logic:present>
					</td>
				</tr>
				</logic:equal>
				--%>
				<%-- 
				<tr>
					<td>
						Documentos Anexados do contrato
					</td>
					<td style="color: red; font-weight: bold;">
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionContrato.do?m=listaArquivos&ctncodg=<bean:write name="contrato" property="ctncodg"/>'">						
					</td>
				</tr>
				--%>
				<logic:equal name="contrato" property="ctlativ" value="F">
				<tr>
					<td colspan="2" style="color: red; font-weight: bold; font-size: 13px;">
						Contrato <bean:write name="contrato" property="ctcdcsc"/>.
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="contrato" property="ctlpgcm" value="F">
				<logic:notEqual name="contrato" property="ctncgsc" value="9">
				<tr>
					<td>
						Cancelar Contrato
					</td>
					<td>
						<input type="button" class="btn_hot" value="Cancelar" onclick="if (window.confirm('Confirmar o cancelamento do Contrato?')){window.location = 'actionContrato.do?m=cancela&ctncodg=<bean:write name="contrato" property="ctncodg"/>'}">
					</td>
				</tr>
				</logic:notEqual>
				</logic:equal>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Nova Pesquisa" class="btn_hot" onclick="window.location = 'contratoPesquisa.do'">
					</td>
				</tr>
			
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="footer.jsp" %>
	</div>
	<logic:equal name="contrato" property="ctlativ" value="T">
	<logic:notEqual name="contrato" property="ctncgsc" value="8">
	<script type="text/javascript">	
		<logic:empty name="contrato" property="ctdextn">    
	    Calendar.setup({
	        inputField     :    "ctdextn",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b2",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    </logic:empty>
	    <logic:empty name="contrato" property="ctdverb">
	    Calendar.setup({
	        inputField     :    "ctdverb",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b3",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    </logic:empty>
	    <logic:notEmpty name="contrato" property="ctdverb">
	    <logic:empty name="contrato" property="ctdpgto">
	    Calendar.setup({
	        inputField     :    "ctdpgto",     // id of the input field
	        ifFormat       :    "%d/%m/%Y",      // format of the input field
	        button         :    "b4",  // trigger for the calendar (button ID)
	        align          :    "BR",           // alignment (defaults to "Bl")
	        singleClick    :    true
	    });
	    </logic:empty>
	    </logic:notEmpty>
	</script>
	</logic:notEqual>
	</logic:equal>
</body>
</html>