<fieldset style="border-color: #D00; border-width: 2px;">
<legend class="red">Dados do Usuário</legend>
<table style="width: 100%">
	<tbody>
		<tr>
			<th style="width:10%; vertical-align: top;">		
				<img class="avatar" src="imagens/logo_exata.gif" width="125" height="65"/>
			</th>
			<td style="vertical-align:top">
				<div id="usuario" style="font-size: 13px; font-family: arial monospace; font-style: inherit;">
						<b>EMPRESA:</b> <bean:write name="empresa" property="epcnome"/> - <bean:write name="empresa" property="epcdccd"/>/<bean:write name="empresa" property="epcufcd"/><br> 
						<b>USUÁRIO:</b> <bean:write name="usuario" property="encnome"/> &nbsp;&nbsp;&nbsp;<b>[<bean:write name="usuario" property="encdcte"/>]</b>	
				</div>
				<div id="agora" style="text-align: right;">
					<strong>
						<%= new com.grupoexata.bancario.utils.FormataObj().getDataPorExtensoComDia(new java.util.Date())%>
					</strong>
				</div>
			</td>
		</tr>
	</tbody>
</table>
</fieldset>