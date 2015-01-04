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
						Matr�cula:
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
							N�o
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
							N�o
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Situa��o:
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
						V�nculo:
					</td>
					<td>
						<bean:write name="empregado" property="empccgvin"/>&nbsp;-&nbsp;
						<bean:write name="empregado" property="empcdcvin"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ocupa��o:
					</td>
					<td>
						<bean:write name="empregado" property="empccgocp"/>&nbsp;-&nbsp;
						<bean:write name="empregado" property="empcdcocp"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Tipo de admiss�o:
					</td>
					<td>
						<bean:write name="empregado" property="empcdctad"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Grau de instru��o:
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
						T�tulo de eleitor:
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
						Org�o emissor:
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
						N�mero:
					</td>
					<td>
						<bean:write name="empregado" property="empcncat"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						S�rie:
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
						Op��o por Vale Trasnporte?
					</td>
					<td>
						
						<logic:equal value="T" name="empregado" property="emplvale"> 
							Sim
						</logic:equal>
						<logic:notEqual value="T" name="empregado" property="emplvale"> 
							N�o
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
							N�o
						</logic:notEqual>
						
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cargo/Fun��o:
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
						Sal�rio Base:
					</td>
					<td>
						R$ <bean:write name="empregado" property="empybase"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ajuda de Custo Di�ria:
					</td>
					<td>
						R$ <bean:write name="empregado" property="empyajud"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de Admiss�o:
					</td>
					<td>
						<bean:write name="empregado" property="empdadms"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de Demiss�o:
					</td>
					<td>
						<bean:write name="empregado" property="empddems"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de In�cio das Atividades:
					</td>
					<td>
						<bean:write name="empregado" property="empdinat"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						T�rmino do contrato:
					</td>
					<td>
						<bean:write name="empregado" property="empdterm"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						M�s de Antecipa��o<br>
						do 13� sal�rio:
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
						Altera��o:
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
						N�o
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
