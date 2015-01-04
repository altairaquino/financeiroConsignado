	<%@include file="topo.jsp" %>
	
	</head>		
	<body bgcolor="#CCCCCC" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	
	<div id="container">		
		
		<%@include file="menu.jsp" %>
		<%@include file="header.jsp" %>

		<div id="content" style="border-color: #D00; border-style: solid; border-width: 2px;">
				<table align="center" border="0" cellpadding="0" cellspacing="0" height="370" width="100%">
  				<tbody>
  					<tr align="center" bgcolor="#dededc" valign="middle">
    					<td align="left" height="356" valign="top" width="245" style="padding: 10px; vertical-align: top;">
    							<logic:present name="msg">
									<input type="hidden" value="<bean:write name="msg"/>" name="msg" id="msg">
									<script>
										var x = document.getElementById('msg').value;
										alert(x);
									</script>
								</logic:present>    							
    							<logic:present name="msg2">
									<input type="hidden" value="<bean:write name="msg2"/>" name="msg2" id="msg2">
									<script>
										var x = document.getElementById('msg2').value;
										alert(x);
									</script>
								</logic:present>
								<br>
    							<%-- 
    							   BeanEntidade usuario = (BeanEntidade)session.getAttribute("usuario"); 
    							   String mensagem = ModelAngariador.getInstance().mensagemProducaoSinergia(Integer.parseInt(usuario.getEnncodg()));
    							   if (!mensagem.isEmpty()){ 
    							 --%>
    								   <p style="font-size: 15px; font-weight: bold; color: #00d;">
    								   		<%-- = mensagem --%> 
    								   </p>													
    						 	<%--
    							   }
    							--%>       							
       							
						</td>
			  		</tr>
				</tbody>
				</table>
			</div>
		<%@include file="footer.jsp" %>
	</div>
</body>

</html>