<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.Produto" %>
<html>
<head>
    <title>Lista de Produtos</title>
    <link rel="stylesheet" type="text/css" href="estilos/estilos.css">
    
</head>
<body>
    <button type="button" onclick="history.back()">Voltar</button>

<%@ page session="true" %>
<%
    String mensagem = (String) session.getAttribute("mensagem");
    String tipoMensagem = (String) session.getAttribute("tipoMensagem"); 
    if (mensagem != null && tipoMensagem != null) {
%>
    <div class="msg-box <%= tipoMensagem %>">
        <%= mensagem %>
    </div>
<%
        session.removeAttribute("mensagem");
        session.removeAttribute("tipoMensagem");
    }
%>

<h2>Lista de Produtos</h2>

<div style="text-align:center; margin-bottom: 20px;">
    <a href="ProdutoServlet?acao=novo">Cadastrar Novo Produto</a>
</div>

<form action="ProdutoServlet" method="get" style="text-align: center;">
    <input type="hidden" name="acao" value="pesquisar">
    <input type="text" name="termo" placeholder="Digite o ID ou nome do produto" required>
    <input type="submit" value="Pesquisar">
    


</form>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Quantidade</th>
        <th>Categoria</th>
        <th>Fornecedor</th>
        <th>Data de Validade</th>
        <th>Peso</th>
        <th>Marca</th>
        <th>Ações</th>
    </tr>

<%
    List<Produto> lista = (List<Produto>) request.getAttribute("listaProdutos");
    if (lista != null) {
        for (Produto p : lista) {
%>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNome() %></td>
        <td><%= p.getDescricao() %></td>
        <td>R$ <%= String.format("%.2f", p.getPreco()) %></td>
        <td><%= p.getQuantidade() %></td>
        <td><%= p.getCategoria() %></td>
        <td><%= p.getFornecedor() %></td>
        <td><%= p.getDataValidade() %></td>
        <td><%= p.getPeso() %> kg</td>
        <td><%= p.getMarca() %></td>
        
            
    <td>
    <a href="ProdutoServlet?acao=editar&id=<%= p.getId() %>" style="display: inline-block; margin-right: 10px;">Editar</a>
    <a href="ProdutoServlet?acao=excluir&id=<%= p.getId() %>" style="display: inline-block;" onclick="return confirm('Confirma a exclusão?')">Excluir</a>
</td>


    </tr>
<%
        }
    }
%>
</table>

<%
    Double total = (Double) request.getAttribute("totalOrcamento");
    if (total == null) {
        total = 0.0;
    }
%>
<div style="text-align:center; margin-top: 20px; font-size: 18px;">
    <strong>Total do Orçamento:</strong> R$ <%= String.format("%.2f", total) %>
</div>

</body>
</html>
