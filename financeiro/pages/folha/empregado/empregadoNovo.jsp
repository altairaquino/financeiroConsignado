<%@include file="/pages/topo.jsp" %>
<style type="text/css">
	.style1{
		text-align: right;
		width: 25%;
		color:#660000;
	}
</style>
<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="dwr/interface/Ocupacao.js"></script>
  	<script type="text/javascript" src="dwr/engine.js"></script>
  	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript">
			function preencheOcupacao(elem){		
				Ocupacao.getOcupacao(retorno,elem.value);						
			}
			
			function retorno(valor){
				var s = valor.split('&');
				DWRUtil.setValue('ocpcdesc',s[0]);
				DWRUtil.setValue('ocpncodg',s[1]);
			}
			
			function init(){
				DWRUtil.useLoadingMessage("Carregando Aguarde!!");
			}
	</script>
</head>
<body>
	<div id="container">
		<%@include file="/pages/menu.jsp" %>
		<%@include file="/pages/header.jsp" %>
		<div id="content">
		<html:errors/>
		<fieldset>
			<legend>
				&nbsp;Cadastro de Dados Funcionais do Empregado &nbsp;
			</legend>
            <html:form action="actionEmpregado" onsubmit="return window.confirm('Confirmar a inclusão do empregado com os dados fornecidos?')">
			<html:hidden property="m" value="cadastro"/>
			<input type="hidden" name="empncgen" value="<bean:write name="funcionario" property="enncodg"/>">
			<table style="width: 100%;">
			<tbody>
				<tr>
					<th colspan="2" style="text-align: center; background-color: #DDDDDD;"> 
						Cadastro de Dados Funcionais do Empregado
					</th>
				</tr>
				<tr>
					<td class="style1">
						Nome:
					</td>
					<td style="font-weight: bold;">
						<bean:write name="funcionario" property="encnome"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ativo?
					</td>
					<td>
						<html:radio property="emplativ" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="emplativ" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Sem registro em carteira?
					</td>
					<td>
						<html:radio property="emplsreg" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="emplsreg" value="F">Não</html:radio>
					</td>
				</tr>
                <tr>
					<td class="style1">
						Situação:
					</td>
					<td>
						<html:select property="empncgste" style="width:350px;">
							<html:optionsCollection name="listasituacao_empregado" label="steccodg_stecdesc" value="stencodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Categoria:
					</td>
					<td>
						<html:select property="empncgcte" style="width:350px;">
							<html:optionsCollection name="listacategoria_empregado" label="ctecdesc" value="ctencodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Vínculo:
					</td>
					<td>
						<html:select property="empncgvin" style="width:350px;">
							<html:optionsCollection name="listavinculo" label="vinccodg_vincdesc" value="vinncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ocupação:
					</td>
					<td>
						<table style="margin: 0px; padding: 0px;">
							<tr align="left">
								<td>
									<html:text property="empccgocp" styleId="ocpccodg" size="6" maxlength="6" onblur="preencheOcupacao(this)"/>
									<html:hidden styleId="ocpncodg" property="empncgocp"/>
								</td>
								<td>
									<html:text property="empcdcocp" styleId="ocpcdesc" disabled="true" size="45"/>
								</td>
								<td>
									<img src="imagens/pesquisa.jpg" alt="pesquisar" title="pesquisar" style="cursor: pointer;top:10px;"  onclick="window.open('ocupacaoPesquisa.do', 'JANELA', 'toolbar=no, location=no, status=no, resizable=no, scrollbars=no, menubar=no, top=' +( (screen.height) ? (screen.height-450)/2 : 0) + ',left=' + ((screen.width) ? (screen.width-550)/2:0) + ',height = 450, width = 550');"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Tipo de Admissão:
					</td>
					<td>
						<html:select property="empncgtad" style="width:350px;">
							<html:optionsCollection name="listatipo_admissao" label="tadcdesc" value="tadncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Grau de instrução:
					</td>
					<td>
						<html:select property="empncggin" style="width:350px;">
							<html:optionsCollection name="listagrau_instrucao" label="gincdesc" value="ginncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Filial:
					</td>
					<td>
						<html:select property="empncgfl" style="width:350px;">
							<html:optionsCollection name="ls_filial" label="flcnome" value="flncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Setor:
					</td>
					<td>
						<html:select property="empncgset" style="width:350px;">
							<html:optionsCollection name="ls_setor" label="setcdesc" value="setncodg"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Título de eleitor:
					</td>
					<td>
						<html:text property="empctitl" size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						RG:
					</td>
					<td>
						<html:text property="empcrg" size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Orgão emissor:
					</td>
					<td>
						<html:text property="empcorg"  size="6" maxlength="6"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						UF:
					</td>
					<td>
						<html:select property="empcufrg">
							<html:option value="">BR</html:option>
							<html:option value="AC">AC</html:option>
							<html:option value="AL">AL</html:option>
							<html:option value="AM">AM</html:option>
							<html:option value="AP">AP</html:option>
							<html:option value="BA">BA</html:option>
							<html:option value="CE">CE</html:option>
							<html:option value="DF">DF</html:option>
							<html:option value="ES">ES</html:option>
							<html:option value="GO">GO</html:option>
							<html:option value="MA">MA</html:option>
							<html:option value="MG">MG</html:option>
							<html:option value="MS">MS</html:option>
							<html:option value="MT">MT</html:option>
							<html:option value="PA">PA</html:option>
							<html:option value="PB">PB</html:option>
							<html:option value="PE">PE</html:option>
							<html:option value="PI">PI</html:option>
							<html:option value="PR">PR</html:option>
							<html:option value="RJ">RJ</html:option>
							<html:option value="RN">RN</html:option>
							<html:option value="RO">RO</html:option>
							<html:option value="RR">RR</html:option>
							<html:option value="RS">RS</html:option>
							<html:option value="SC">SC</html:option>
							<html:option value="SE">SE</html:option>
							<html:option value="SP">SP</html:option>
							<html:option value="TO">TO</html:option>
						</html:select>
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
						<html:text property="empcncat" onkeyup="criaMascara(this,'###########');" size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Série:
					</td>
					<td>
						<html:text property="empcscat" onkeyup="criaMascara(this,'###########');"  size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						UF:
					</td>
					<td>
						<html:select property="empcufct">
							<html:option value="">BR</html:option>
							<html:option value="AC">AC</html:option>
							<html:option value="AL">AL</html:option>
							<html:option value="AM">AM</html:option>
							<html:option value="AP">AP</html:option>
							<html:option value="BA">BA</html:option>
							<html:option value="CE">CE</html:option>
							<html:option value="DF">DF</html:option>
							<html:option value="ES">ES</html:option>
							<html:option value="GO">GO</html:option>
							<html:option value="MA">MA</html:option>
							<html:option value="MG">MG</html:option>
							<html:option value="MS">MS</html:option>
							<html:option value="MT">MT</html:option>
							<html:option value="PA">PA</html:option>
							<html:option value="PB">PB</html:option>
							<html:option value="PE">PE</html:option>
							<html:option value="PI">PI</html:option>
							<html:option value="PR">PR</html:option>
							<html:option value="RJ">RJ</html:option>
							<html:option value="RN">RN</html:option>
							<html:option value="RO">RO</html:option>
							<html:option value="RR">RR</html:option>
							<html:option value="RS">RS</html:option>
							<html:option value="SC">SC</html:option>
							<html:option value="SE">SE</html:option>
							<html:option value="SP">SP</html:option>
							<html:option value="TO">TO</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
						<font style="font-weight: bold;">FGTS</font>
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Conta:
					</td>
					<td>
						<html:text property="empcfgts"  size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data:
					</td>
					<td>
						<html:text property="empdfgts" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Opção por Vale Transporte?
					</td>
					<td>
						<html:radio property="emplvale" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="emplvale" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Comissionado?
					</td>
					<td>
						<html:radio property="emplcoms" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="emplcoms" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Cargo/Função:
					</td>
					<td>
						<html:text property="empccarg" size="45" maxlength="45" style="width:350px;"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						PIS/PASEP/NIT:
					</td>
					<td>
						<html:text property="empcpis"  size="25" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Horas semanais:
					</td>
					<td>
						<html:text property="empnhrsm" onkeyup="criaMascara(this, '##');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Salário Base:
					</td>
					<td>
						<html:text property="empybase" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Ajuda de Custo Diária:
					</td>
					<td>
						<html:text property="empyajud" onkeydown="Formata(this,10,event,2)"/>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Data de admissão:
					</td>
					<td>
						<html:text property="empdadms" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>			
				<tr>
					<td class="style1">
						Data de início das atividade:
					</td>
					<td>
						<html:text property="empdinat" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Término do contrato:
					</td>
					<td>
						<html:text property="empdterm" onkeyup="criaMascara(this, '##/##/####');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Mês de Antecipação<br>
						do 13º salário:
					</td>
					<td>
						<html:text property="empnma13" onkeyup="criaMascara(this,'##');"></html:text>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Deduz parcela do IR?
					</td>
					<td>
						<html:radio property="emplnir" value="T">Sim</html:radio>&nbsp;&nbsp;
						<html:radio property="emplnir" value="F">Não</html:radio>
					</td>
				</tr>
				<tr>
					<td class="style1">
						Capital segurado:
					</td>
					<td>
						<html:text property="empycpsg" onkeydown="Formata(this,10,event,2)"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn_hot" value="Voltar" onclick="window.location = 'actionEntidade.do?m=opcoesFuncionario&enncodg=<bean:write name="funcionario" property="enncodg"/>'">
					</td>
					<td style="text-align: right;">
						<html:submit styleClass="btn_hot">Cadastrar empregado</html:submit>
					</td>
				</tr>
			</tbody>			
			</table>
            </html:form>
		</fieldset>
		</div>
		<%@include file="/pages/footer.jsp" %>
	</div>
</body>
</html>
