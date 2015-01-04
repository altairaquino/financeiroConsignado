	<%@include file="../topo.jsp" %>
	<style type="text/css">
		#div_pop{
			padding: 15px;
			position: absolute;
			border: 2px solid;
			border-color: green;
			color: black;
			width: 620px;
			height: 450px;
			top: 100px;
			background-color: #EEE;
			overflow: auto;
		}
	</style>
</head>
<body>
	
	<div id="container">
		<%@include file="../menu.jsp" %>
		<%@include file="../header.jsp" %>
		
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend class="red">
				&nbsp;   Opções do processo  > <bean:write name="processo" property="procnmcl"/> (<bean:write name="processo" property="procnumr"/>)  &nbsp;
			</legend>
			<div id="div_pop">
				<table style="width: 600px;" align="center" cellspacing="0" cellpadding="4">
				<tbody>
					<tr>
						<td style="text-align: left;">
							<input type="image" src="imagens/print.gif" title="Imprimir" onclick="imprimirDiv('div_pop');">						
						</td>
						<td style="text-align: right;">
							<input type="image" src="imagens/cancela.jpg" title="Fechar" onclick="document.getElementById('div_pop').style.display = 'none';">						
						</td>						
					</tr>
					<tr>
						<th colspan="2">
							<img src="imagens/logo_exata.gif" width="80" height="40">
						</th>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center; background-color: #CCC;">
							Dados do Cliente
						</th>
					</tr>
					<tr>
						<th>
							C.P.F.
						</th>
						<td>
							<bean:write name="processo"  property="proccpf"/>
						</td>
					</tr>
					<tr>
						<th style="width: 25%">
							Nome
						</th>
						<td style="width: 75%">
							<bean:write name="processo"  property="procnmcl"/>
						</td>
					</tr>
					<tr>
						<th>
							Data Nascimento
						</th>
						<td>					
							<bean:write name="processo"  property="prodnasc"/>
							&nbsp;<b>Sexo:</b>&nbsp;
							<logic:equal name="processo" property="procsexo" value="M">
								Masculino
							</logic:equal>
							<logic:equal name="processo" property="procsexo" value="F">
								Feminino
							</logic:equal>			
						</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center; background-color: #CCC;">
							Endereço do Cliente
						</th>
					</tr>
					<tr>
						<th style="width: 25%">
							Fones para Contato
						</th>
						<td style="width: 75%">
							<bean:write name="cliente" property="encfone"/> / <bean:write name="cliente" property="enccell"/>
						</td>
					</tr>								
					<tr>
						<th style="width: 25%">
							Endereço
						</th>
						<td style="width: 75%">
							<bean:write name="cliente" property="encdctl"/> <bean:write name="cliente" property="enlendr"/>
						</td>
					</tr>								
					<tr>
						<th style="width: 25%">
							Cidade/Estado
						</th>
						<td style="width: 75%">
							<bean:write name="cliente" property="encufcd"/> <bean:write name="cliente" property="encdccd"/>
						</td>
					</tr>								
					<tr>
						<th style="width: 25%">
							CEP/Bairro
						</th>
						<td style="width: 75%">
							<bean:write name="cliente" property="enccep"/> <bean:write name="cliente" property="encbair"/>
						</td>
					</tr>								
					<tr>
						<th colspan="2" style="text-align: center;background-color: #CCC;"> 
							Contatos
						</th>
					</tr>
					<tr>
						<th style="width: 25%">
							Angariador
						</th>
						<td style="width: 75%">
							<bean:write name="processo"  property="procnmcn"/>
						</td>
					</tr>
					<tr>
						<th style="width: 25%">
							Consultor
						</th>
						<td style="width: 75%">
							<bean:write name="processo" property="proccons"/>
						</td>
					</tr>
					<tr>
						<th style="width: 25%">
							Supervisor
						</th>
						<td style="width: 75%">
							<bean:write name="processo" property="procsupe"/>			
						</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center; background-color: #CCC;">
							Dados do Processo
						</th>
					</tr>
					<tr>
						<th style="width: 25%">
							Nº do Processo
						</th>
						<td style="width: 75%">
							<bean:write name="processo"  property="procnumr"/>					
						</td>
					</tr>
					<tr>
						<th style="width: 25%">
							Código Interno
						</th>
						<td style="width: 75%">
							<bean:write name="processo"  property="proncodg2"/>					
						</td>
					</tr>
					<tr>
						<th>
							Tipo de Ação
						</th>
						<td>
							<bean:write name="processo" property="procdctac"/>
						</td>
					</tr>
					<tr>
						<th style="width: 25%"> 
							Localização Interna
						</th>
						<td style="width: 75%">
							<bean:write name="processo"  property="procloca"/>			
						</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center; background-color: #CCC;">
							Observações do Processo
						</th>
					</tr>
					<tr>
						<td colspan="2" style="height: 30px; vertical-align: top;">					
							<bean:write name="processo" property="procobs"/>
						</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center; background-color: #CCC;"> 
							Andamento do Processo
						</th>
					</tr>
					<logic:present name="ls_andamentoprocesso">
					<logic:iterate id="b" name="ls_andamentoprocesso">
					<tr>
						<td style="font-weight: bold;">
							<bean:write name="b" property="anpddata"/>
						</td>
						<td>
							<b><bean:write name="b" property="anpcdcfap"/></b> - <bean:write name="b" property="anpcdesc"/>			
						</td>
					</tr>
					</logic:iterate>
					</logic:present>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>	
				</tbody>			
				</table>
			</div>
			<h3>Código Interno: <bean:write name="processo" property="proncodg2"/></h3>
			<table style="width: 100%">
			<tbody>
				<tr style="background-color: #DDD; text-align: center;">
					<th colspan="2" style="text-align: center;">
						Opções do processo
						<input type="image" src="imagens/estrela.gif" title="Abrir Dados do Processo" onclick="document.getElementById('div_pop').style.display = 'block';">
					</td>
				</tr>	
				<tr>
					<td>
						Dados do Cliente
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionEntidade.do?m=editarClienteJuridico&enncodg=<bean:write name="processo" property="proncgen"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Dados do Processo
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionProcesso.do?m=dados&proncodg=<bean:write name="processo" property="proncodg"/>'">
					</td>
				</tr>
				<tr>
					<td>
						Visualiza andamento do processo
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionAndamentoProcesso.do?m=lista&proncodg=<bean:write name="processo" property="proncodg"/>'">						
					</td>
				</tr>
				<tr>
					<td>
						Audiências Marcadas do processo
					</td>
					<td>
						<input type="button" class="btn_hot" value="Visualizar" onclick="window.location = 'actionAudiencia.do?m=lista&proncodg=<bean:write name="processo" property="proncodg"/>'">						
					</td>
				</tr>
				<tr>
					<td>
						Imprimir Documentos
					</td>
					<td>
						<input type="button" class="btn_hot" value="Ofício" onclick="window.location = 'actionProcesso.do?m=documentos&tipo=1&proncodg=<bean:write name="processo" property="proncodg"/>'">						
						<input type="button" class="btn_hot" value="Etiqueta" onclick="window.location = 'actionProcesso.do?m=documentos&tipo=2&proncodg=<bean:write name="processo" property="proncodg"/>'">						
						<input type="button" class="btn_hot" value="Envelope" onclick="window.location = 'actionProcesso.do?m=documentos&tipo=3&proncodg=<bean:write name="processo" property="proncodg"/>'">						
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="Nova Pesquisa" class="btn_hot" onclick="window.location = 'processoPesquisa.do'">
					</td>
				</tr>
			
			</tbody>			
			</table>	
		
		</fieldset>
			
		</div>
		
		<%@include file="../footer.jsp" %>
	</div>
	
</body>
</html>