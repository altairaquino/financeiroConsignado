<%@include file="/pages/topo.jsp" %>
<script type="text/javascript" src="scripts/jquery.js"></script>
<style type="text/css">
	.style1{
		text-align: right;
		width: 25%;
		color:#660000;
	}
</style>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp; Dados Cadastrais do Empregado &nbsp;
			</legend>
			<table style="width: 100%;">
			<tbody>
				<tr>
					<td>					
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="empregado" property="empncgen"/>'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionEmpregado.do?m=editar&empncodg=<bean:write name="empregado" property="empncodg"/>'">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;">
						Dados Cadastrais do Empregado 
					</th>
				</tr>
        		<tr>
					<td class="style1">
						Nome:
					</td>
					<td style="color: #00D; font-weight: bold;">
						<bean:write name="empregado" property="empcnmen"/>
					</td>
				</tr>
        		<tr>
					<td class="style1">
						C.P.F.:
					</td>
					<td style="color: #00D; font-weight: bold;">
						<bean:write name="empregado" property="empccpf"/>
					</td>
				</tr>
        		<tr>
					<td class="style1">
						Matrícula:
					</td>
					<td style="color: #00D; font-weight: bold;">
						<bean:write name="empregado" property="empncodg"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ativo?
					</td>
					<td>
						<logic:equal value="T" name="empregado" property="emplativ"> 
							Sim
						</logic:equal>
						<logic:equal value="F" name="empregado" property="emplativ"> 
							Não
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sem registro em carteira?
					</td>
					<td>
						<logic:equal value="T" name="empregado" property="emplsreg"> 
							Sim
						</logic:equal>
						<logic:equal value="F" name="empregado" property="emplsreg"> 
							Não
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Situação:
					</td>
					<td>						
						<bean:write name="empregado" property="empccgste"/>&nbsp;-&nbsp;
						<bean:write name="empregado" property="empcdcste"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Categoria:
					</td>
					<td>
						<bean:write name="empregado" property="empcdccte"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Vínculo:
					</td>
					<td>
						<bean:write name="empregado" property="empccgvin"/>&nbsp;-&nbsp;
						<bean:write name="empregado" property="empcdcvin"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ocupação:
					</td>
					<td>
						<bean:write name="empregado" property="empccgocp"/>&nbsp;-&nbsp;
						<bean:write name="empregado" property="empcdcocp"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Tipo de admissão:
					</td>
					<td>
						<bean:write name="empregado" property="empcdctad"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Grau de instrução:
					</td>
					<td>
						<bean:write name="empregado" property="empcdcgin"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Filial:
					</td>
					<td>
						<bean:write name="empregado" property="empcdcfl"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Setor:
					</td>
					<td>
						<bean:write name="empregado" property="empcdcset"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Título de eleitor:
					</td>
					<td>
						<bean:write name="empregado" property="empctitl"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						RG:
					</td>
					<td>
						<bean:write name="empregado" property="empcrg"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Orgão emissor:
					</td>
					<td>
						<bean:write name="empregado" property="empcorg"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						UF:
					</td>
					<td>
						<bean:write name="empregado" property="empcufrg"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
						<font style="font-weight: bold;">Carteira de Trabalho</font>
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Número:
					</td>
					<td>
						<bean:write name="empregado" property="empcncat"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Série:
					</td>
					<td>
						<bean:write name="empregado" property="empcscat"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						UF:
					</td>
					<td>
						<bean:write name="empregado" property="empcufct"/>
						
					</td>
				</tr>
				<tr>
					<td colspan="2" style="font-weight: bold;">
						<hr/>
							FGTS
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Conta:
					</td>
					<td>
						<bean:write name="empregado" property="empcfgts"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data:
					</td>
					<td>
						<bean:write name="empregado" property="empdfgts"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Opção por Vale Trasnporte?
					</td>
					<td>
						
						<logic:equal value="T" name="empregado" property="emplvale"> 
							Sim
						</logic:equal>
						<logic:notEqual value="T" name="empregado" property="emplvale"> 
							Não
						</logic:notEqual>
						
					</td>
				</tr>
				<tr>
					<td class="style1">
						Comissionado?
					</td>
					<td>
						
						<logic:equal value="T" name="empregado" property="emplcoms"> 
							Sim
						</logic:equal>
						<logic:notEqual value="T" name="empregado" property="emplcoms"> 
							Não
						</logic:notEqual>
						
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cargo/Função:
					</td>
					<td>
						<bean:write name="empregado" property="empccarg"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						PIS/PASEP/NIT:
					</td>
					<td>
						<bean:write name="empregado" property="empcpis"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Horas semanais:
					</td>
					<td>
						<bean:write name="empregado" property="empnhrsm"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Salário Base:
					</td>
					<td>
						R$ <bean:write name="empregado" property="empybase"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ajuda de Custo Diária:
					</td>
					<td>
						R$ <bean:write name="empregado" property="empyajud"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de Admissão:
					</td>
					<td>
						<bean:write name="empregado" property="empdadms"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de Demissão:
					</td>
					<td>
						<bean:write name="empregado" property="empddems"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de Início das Atividades:
					</td>
					<td>
						<bean:write name="empregado" property="empdinat"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Término do contrato:
					</td>
					<td>
						<bean:write name="empregado" property="empdterm"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Mês de Antecipação<br>
						do 13º salário:
					</td>
					<td>
						<bean:write name="empregado" property="empnma13"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cadastro:
					</td>
					<td>
						<bean:write name="empregado" property="empdcadt"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Alteração:
					</td>
					<td>
						<bean:write name="empregado" property="empdaltr"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Deduz parcela do IR?:
					</td>
					<td>
						
						<logic:equal value="T" name="empregado" property="emplnir"> 
						Sim
						</logic:equal>
						<logic:notEqual value="T" name="empregado" property="emplnir"> 
						Não
						</logic:notEqual>
						
					</td>
				</tr>
				<tr>
					<td class="style1">
						Capital segurado:
					</td>
					<td>
						<bean:write name="empregado" property="empycpsg"/>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td>					
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="empregado" property="empncgen"/>'">
					</td>
					<td style="text-align: right;">
						<input type="button" class="btn_hot" value="Editar Dados" onclick="window.location = 'actionEmpregado.do?m=editar&empncodg=<bean:write name="empregado" property="empncodg"/>'">
					</td>
				</tr>
			</tbody>			
			</table>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
