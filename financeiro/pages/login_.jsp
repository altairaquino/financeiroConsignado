<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html>
	<head>
	<!-- Area 10 - meta tags -->
		<title><bean:message key="welcome.title"/></title>
		<meta content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
		<meta name="keywords" content="banco, exata, home, emprestimo, corretora, assessoria, juridica, veiculos">
		<meta content="all, index, follow" name="robots">
		<meta content="document" name="resource-type">

		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="STYLESHEET" type="text/css" href="stylesheet/stylesite.css">
		
		<%-- 
		<script type="text/javascript">
			function verificaNavegador(){
				if (navigator.appName == 'Netscape'){
					document.getElementById('formLogin').submit();
				}else{
					alert('Por medidas de segurança para acesso ao sistema EXATA \nutilize o Navegador Mozila Firefox.');
				}				
			}
		</script>
		--%>

	</head>
	<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginheight="0" marginwidth="0" bgcolor="#ffffff">
	<table width="780" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
		<td height="110" valign="top">
		<!-- <topo> -->
			
			<div id="lytTopo">
				<div class="topoSuperior">
					<div class="ferramentas" style="padding-right: 45px; text-align: left; color: white; font-weight: bold;">
						Acesso ao Sistema Exata
					</div>
						<br style="clear: both;">
						<div class="blocoIB"> 
							<!-- incio do codigo para outras areas -->
							<div style="position: absolute; left: 522px; top: 22px;">
								<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
								<tr>
									<td>
										<form method="post" action="actionLogin.do" name="formLogin" id="formLogin" onsubmit="if (navigator.appName == 'Netscape'){return true;}else{alert('Por medidas de segurança e para um melhor funcionamento do sistema EXATA, \nutilize o navegador Mozila Firefox.\nObs: o download pode ser feito no link abaixo.'); return false;}">
										<input name="m" value="autenticaUsuario" type="hidden">
										<table border="0" cellpadding="0" cellspacing="0">
											<tbody>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td>
															</td>
															<td>
																<img src="imgsite/dot.gif"  height="1" width="2" border="0">
															</td>
															<td style="font-weight: bold; color: white;">
																LOGIN
															</td>
															<td>
																<img src="imgsite/dot.gif"  height="1" width="4" border="0">
															</td>
															<td>
																<input name="login" style="width: 50px;" size="10" maxlength="15" class="input2" type="text">
															</td>
															<td>
																&nbsp;
															</td>
															<td style="font-weight: bold; color: white;">
																SENHA
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																<input name="senha" style="width: 50px;" size="10" class="input2" maxlength="15" type="password">
															</td>
															<td>
																&nbsp;
															</td>
															<td>
																<input type="image" src="imgsite/btn_topo_ok.gif">
															</td>
														</tr>
														</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="7" style="color: white; font-weight: bold; text-align: right;">
														<a href="javascript:alert('Envie e-mail para suporte@exatagrupo.com.br\nsolicitando uma nova senha.')" style="color: yellow;">Esqueceu sua senha?</a>													
													</td>
												</tr>
												</tbody>
												</table>
											</td>
										</tr>
										</tbody>
										</table>
										</form>
									</div>
								</div>
							<div style="margin: 0pt 0pt 0pt 25px;">
								<a href="login.do"><img src="imgsite/dot.gif"  height="25" width="175" border="0"></a>
							</div>
				</div>
				<style>
					#lytTopo .menuDireito img{float:left;margin:4px 0 0 0;}
				</style>
				<div class="menuDireito clrfix">
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" >Parceiros</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" >Trabalhe conosco</a>
				</div>
				<div class="menuEsquerdo clrfix">
					<a href="#" style="font-weight: bold;">Home</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" target="_self">Serviços</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" >Financiamentos</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" target="_self">A Empresa</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" target="_self">Missão</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a href="#" >Quem Somos</a>
				</div>
				<div class="subMenu" style="margin: 7px 8px 0pt 5px;">
					<!-- Montando as Subareas do Menu de segmentos -->
					<!-- Montando as Subareas do Menu de Soluções -->
					<img src="imgsite/icone_home_on.gif" alt="Home" style="margin: 0px; vertical-align: top;" height="12" width="14" border="0">
					<a class="menuSubAarea" href="http://email.exatagrupo.com.br" target="_blank">
						WebMail Exata
					</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a class="menuSubAarea" href="http://pt-br.www.mozilla.com/pt-BR/firefox/" target="_blank" style="color: #D00;">
						Download Firefox
					</a>
					<img src="imgsite/img_separador_link_topo.gif"  border="0">
					<a class="menuSubAarea" href="LogMeIn.msi" target="_blank" style="color: #D00;">
						Download LogMeIn
					</a>
				</div>
			</div>	
			
		<!-- </topo> -->
		</td>
	</tr>
</tbody></table>
<table width="780" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr>
		<td height="100%" valign="middle" style="height: 400px;" align="center">
			<img src="imgsite/emconstrucao.jpg" width="160" height="160"><br>
			<p style="font-size: 14px; font-weight: bold;">Site em Construção</p>
<!-- <meio> -->
	<%-- 
			<table height="100%" width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody><tr>
					<td valign="top">
						<!-- Area 1 - humanização -->
						<div style="border: 1px solid red; margin: 10px 0pt 10px 4px; padding-left: 7px; height: 200px;">
							<h2>Notícias</h2>
						</div>
						<!-- Area 2 - destaque -->
						<div style="float: left; padding-left: 7px; margin-top: 10px; width: 187px;">
							<div style="width: 187px;">
								<div style="border: 1px solid rgb(197, 197, 197); height: 98px;">
									<div style="margin: 5px 5px 5px 49px; text-align: right; font-family: Tahoma; font-weight: bold; font-size: 11px; color: rgb(214, 42, 38);">
										<img style="margin-right: 3px;" src="imgsite/ico_saibaMais.gif">
										<a style="font-family: Tahoma; font-size: 11px; color: rgb(240, 20, 20); font-weight: bold;" href="#" target="_self">
											Crédito Fácil
										</a>
									</div>
									<div style="padding: 0pt 5px 5px 33px; font-family: Tahoma; font-size: 11px; color: rgb(126, 126, 126); text-align: right;">
										<a style="font-family: Tahoma; font-size: 11px;" href="#" target="_self">
											A Exata tem empréstimo fácil para aposentados e pensionistas do INSS. 
										</a>
										<br style="clear: both;">
									</div>
								</div>
							</div>
						</div>
						<!-- Area 2 - destaque -->
						<div style="float: left; padding-left: 7px; margin-top: 10px; width: 187px;">
							<div style="width: 187px;">
								<div style="border: 1px solid rgb(197, 197, 197); height: 98px;">
									<div style="margin: 5px 5px 5px 49px; text-align: right; font-family: Tahoma; font-weight: bold; font-size: 11px; color: rgb(214, 42, 38);">
										<img style="margin-right: 3px;" src="imgsite/ico_saibaMais.gif">
											<a style="font-family: Tahoma; font-size: 11px; color: rgb(240, 20, 20); font-weight: bold;" href="#" target="_self">
												Veiculos
											</a>
									</div>
									<div style="padding: 0pt 5px 5px 33px; font-family: Tahoma; font-size: 11px; color: rgb(126, 126, 126); text-align: right;">
										<a style="font-family: Tahoma; font-size: 11px;" href="#" target="_self">
											Financie o veículo de seu amigo na Exata.
										</a>
										<br style="clear: both;">
									</div>
								</div>
							</div>
						</div>
						<!-- Area 2 - destaque -->
						<div style="float: left; padding-left: 7px; margin-top: 10px; width: 187px;">
							<div style="width: 187px;">
								<div style="border: 1px solid rgb(197, 197, 197); height: 98px;">
									<div style="margin: 5px 5px 5px 49px; text-align: right; font-family: Tahoma; font-weight: bold; font-size: 11px; color: rgb(214, 42, 38);">
										<img style="margin-right: 3px;" src="imgsite/ico_saibaMais.gif">
										<a style="font-family: Tahoma; font-size: 11px; color: rgb(240, 20, 20); font-weight: bold;" href="#" target="_self">
											Serviços Jurídicos
										</a>
									</div>
									<div style="padding: 0pt 5px 5px 33px; font-family: Tahoma; font-size: 11px; color: rgb(126, 126, 126); text-align: right;">
										<a style="font-family: Tahoma; font-size: 11px;" href="#" target="_self">
											Com a Exata você conta com assessoria jurídica para diversos tipos de processos.  
										</a>
										<br style="clear: both;">
									</div>
								</div>
							</div>
						</div>
						<br style="clear: both;">
						<table border="0" cellpadding="0" cellspacing="0">
							<tbody><tr>
								<td valign="top" width="188">
								<!-- Area 3 - atendimento -->
									<div style="padding-left: 7px; margin-top: 10px; border: 1px solid gray;">
										<div style="background: transparent url(../../img/bkg_atendimento_modulo.jpg) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; width: 187px; height: 151px;">
										<!-- 
											<ul class="atendimentoHome">
												<li>
													<span>»</span>
													<a href="#" target="_self" title="nas Agências">nas Agências</a>
												</li>
												<li>
													<span>»</span>
													<a href="#" target="_self" title="nas Ruas ">nas Ruas </a>
												</li>
												<li>
													<span>»</span>
													<a href="#" target="_self" title="no Telefone">no Telefone</a>
												</li>
												<li>
													<span>»</span>
													<a href="#" target="_self" title="no Celular">no Celular</a>
												</li>
												<li>
													<span>»</span>
													<a href="#" target="_self" title="na Internet">na Internet</a>
												</li>
												<li>
													<span>»</span><a href="#" target="_self" title="no Auto-Atendimento">no Auto-Atendimento</a>
												</li>
											</ul>
											-->
										</div>
									</div>
									<!-- Area 4 - banner esquerdo -->
									<div style="padding-left: 7px; margin-top: 10px;">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td align="center">
													<span style="color: red; font-weight: bold;">
														SAC<br>
														Serviço de Apoio ao Cliente
													</span>													
													<span style="font-weight: bold; font-size: 14px;">
														<br><br>(0xx87) 2101-2150
													</span>
													<!-- a href="#" target="_self">
														<img  src="imgsite/SAC_40648.jpg" border="0">
													</a -->
												</td>
											</tr>
										</tbody>
										</table>
										</div>
										</td>
										<td valign="top">
										<!-- Area 5 - dicas -->
												<div style="padding: 0pt 12px 0pt 7px; margin-top: 10px;">
												<div style="background: transparent url(imgsite/bkg_dicas.jpg) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; width: 381px; height: 260px;">
												<div class="layer4Dicas" id="layerUniversidadesTxt" style="display: block;">
													<!-- a href="#" target="_self"  -->
														<h1>Crédito e Confiança</h1>
														Essa é a marca que e EXATA deixa onde ela já prestou serviços, pois trabalha com seriedade nos seus diversos ramos e busca em primeiro 
														lugar a satisfação do cliente.
													<!-- /a -->
													<img style="margin: 15px -13px -15px; display: block;"  src="imgsite/fio_divisao_4abas.jpg" height="1" width="353" border="0"> 
													<a href="#" target="_self">
														<h1>Conheça os nosso serviços</h1>
														Visite o link Serviços no nosso menu principal e veja tudo que e EXATA 
														tem para oferecer a seus clientes.
													</a> 
												</div></div></div>
											</td>
										</tr>
									</tbody>
									</table>
								</td>
								<td valign="top" width="188">
									<div style="text-align: left;">
										<div style="margin: 0pt 0pt 0pt 3px;">
											<img  src="imgsite/para_voce.jpg" border="0">
										</div>
										<div style="border-bottom: 1px solid rgb(202, 202, 202); margin: 0pt 0pt 10px 3px; background: rgb(229, 229, 229) url(../../img/bkg_solucoes_voce_novo.jpg) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; width: 178px; height: 126px;">
											<ul style="margin: 0pt; padding: 5px 0pt 0pt; list-style-type: none; list-style-image: none; list-style-position: outside;">
											<!-- Area 6 - soluções -->
												<li>
													<div style="padding: 6px 7px;">
														<img style="margin-right: 7px;"  src="imgsite/ico_seta_solucoes.gif" align="absmiddle">
															<a style="font-family: verdana; font-size: 10px; color: rgb(113, 113, 113); font-weight: bold;" href="#" target="_self">
																Empréstimos
															</a>
													</div>
												</li>
												<!-- Area 6 - soluções -->
												<li style="background: transparent url(../../img/img_pontilhado_solucoes.gif) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
													<div style="padding: 6px 7px;">
														<img style="margin-right: 7px;"  src="imgsite/ico_seta_solucoes.gif" align="absmiddle">
															<a style="font-family: verdana; font-size: 10px; color: rgb(113, 113, 113); font-weight: bold;" href="#" target="_self">
																Financiamentos
															</a>
													</div>
												</li>
												<!-- Area 6 - soluções -->
												<li style="background: transparent url(../../img/img_pontilhado_solucoes.gif) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
													<div style="padding: 6px 7px;">
														<img style="margin-right: 7px;"  src="imgsite/ico_seta_solucoes.gif" align="absmiddle">
														<a style="font-family: verdana; font-size: 10px; color: rgb(113, 113, 113); font-weight: bold;" href="#" target="_self">
															Investimentos
														</a>
													</div>
												</li>
												<!-- Area 6 - soluções -->
												<li style="background: transparent url(../../img/img_pontilhado_solucoes.gif) no-repeat scroll 0% 0%; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
													<div style="padding: 6px 7px;">
														<img style="margin-right: 7px;"  src="imgsite/ico_seta_solucoes.gif" align="absmiddle">
														<span style="font-family: verdana; font-size: 10px; color: rgb(113, 113, 113); font-weight: bold;">
															Central de Serviços
														</span>
														<div style="padding: 5px 0pt 0pt 10px;">
															<select class="menuAcesso" style="width: 150px;" id="143109152239" onchange="direciona(this);">
																<option value="">O que você deseja?</option>
																<option value="#">Empréstimo?</option>
																<option value="#">O carro novo?</option>
																<option value="#">Investimentos?</option>
																<option value="#">Serviços Jurídicos?</option>
																<option value="#">Comprar um imóvel?</option>
															</select>
														</div>
													</div>					
												</li>
													<!-- Area 6 - soluções -->
													<!-- <li style="-moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
														<div style="padding: 6px 7px;">
															<img style="margin-right: 7px;"  src="imgsite/ico_seta_solucoes.gif" align="absmiddle">
															<a style="font-family: verdana; font-size: 10px; color: rgb(113, 113, 113); font-weight: bold;" href="#" target="_self">Simuladores</a>
														</div>
													</li> -->
												</ul>
										</div>
							<!-- Area 7 - vendas -->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td align="center">
										<a href="http://www.bmc.com.br" target="_self">
											<img  src="imgsite/banco_bmc.jpg" border="0" width="178">
										</a>
									</td>
								</tr>
								<tr>
									<td align="center">
										<a href="#" target="_self">
											<img  src="imgsite/banner.gif" border="0" width="178">
										</a>
									</td>
								</tr>
							</tbody>
							</table>
							<div style="border: 1px solid rgb(204, 204, 204); margin: 10px 0pt 10px 4px; width: 175px;">
								<div style="margin: 7px;">
									<!-- <menus direito> -->
									
									<!-- <acesso rapido> -->
										
									<div style="text-align: left;">
										<img src="imgsite/img_acesso_rapido.gif"  style="display: block;" border="0">
										<select name="escolhaSecao" onchange="if (this.value != ''){direciona(this.value);}" style="width: 161px;" class="menuAcesso">
											<option value="" selected="selected">Ir para:</option>
											<option value="#">Financiamentos</option>			
											<option value="#">Assessoria Jurídica</option>
											<option value="#">Empréstimos</option>								
										</select>
									</div>
									<script type="text/javascript">
										function direciona(href){
											window.location = ''+href;
										}										
									</script>
									<!-- </acesso rapido> -->
								</div>
							</div>
								<!-- Area 8 - banner direito -->							
							</div>
						</td>
						</tr>
					</tbody>
					</table>
					--%>
					<!-- </meio> -->
				</td>
			</tr>
			<tr>
				<td height="22">
				<!-- <rodape> -->
					<div id="lytRodape">
						<div class="esquerda">
							<a href="#" >Sobre a empresa</a>
							<img src="imgsite/img_separador_link_rodape.gif"  border="0">
							<a href="#" >Parceiros</a>
							<img src="imgsite/img_separador_link_rodape.gif"  border="0">
							<a href="#" >Onde estamos</a>
							<img src="imgsite/img_separador_link_rodape.gif"  border="0">
							<a href="#" >Trabalhe&nbsp;Conosco</a>
							<img src="imgsite/img_separador_link_rodape.gif"  border="0">
							<br style="clear: both;">
						</div>
						<div class="direita" style="margin-top: 0pt;">
							&nbsp;
						</div>
						<div class="nome" style="padding: 9px 5px 5px 30px;">
							<span>&nbsp;</span>
						</div>
					</div>
				<!-- </rodape> -->
				</td>
			</tr>
		</tbody>
		</table>
	<!-- Area 9 - destaque lateral externo -->	
	<logic:present name="login_erro">
		<input type="hidden" value="<bean:write name="login_erro"/>" name="msg" id="msg">
		<script>
			var x = document.getElementById('msg').value;
			alert(x);
		</script>
	</logic:present>
</body>

</html>