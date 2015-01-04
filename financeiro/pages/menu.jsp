<div style="background-color:#FF4500; width: 100%; height: 21px; color: white; font-weight: bold; font-size: 14px; text-align: right;">
	Consignado WEB - Todos os direitos reservados.&nbsp;&nbsp;&nbsp;
</div>
<logic:notPresent name="bloqueiamenu" scope="session">
<ul class="main_menu">
	<li>
		<a href="home.do" title="Página Inicial">Home</a>
	</li>
		
	<%= ((String)session.getAttribute("menu")) %>
		
	<li>
		<a href="#" title="Opcões do Usuário">Opções</a>
		<ul>
			<!--
			<li>
				<a href="actionOuvidoria.do?m=novo" title="Fale Conosco">Fale Conosco</a>
			</li>
			-->
			<li>
				<a href="actionEntidade.do?m=editarUsuario" title="Alterar Meus Dados">Alterar Meus Dados</a>
			</li>
			<li>
				<a href="usuarioAlteraSenha.do" title="Alterar Senha">Alterar Senha</a>
			</li>
		</ul>		
	</li>	
	<li>
		<a href="logout.do" title="Sair do sistema">Sair</a>
	</li>
</ul>
</logic:notPresent>