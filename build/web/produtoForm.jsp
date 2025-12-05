<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Produto" %>
<%
    Produto produto = (Produto) request.getAttribute("produto");
    boolean editando = (produto != null);
%>
<html>
<head>
    <title><%= editando ? "Editar Produto" : "Cadastrar Produto" %></title>
    
    <link rel="stylesheet" href="estilos/estilos.css">

</head>
<body>


<h2><%= editando ? "Editar Produto" : "Cadastrar Produto" %></h2>
<button type="button" onclick="history.back()">Voltar</button>
<form action="ProdutoServlet" method="post">
    <input type="hidden" name="acao" value="<%= editando ? "atualizar" : "inserir" %>">
    <% if (editando) { %>
        <input type="hidden" name="id" value="<%= produto.getId() %>">
    <% } %>

    <label>Nome:</label>
    <input type="text" name="nome" value="<%= editando ? produto.getNome() : "" %>" required
        pattern=".*[a-zA-Z]+.*"
        title="O campo deve conter pelo menos uma letra.">

    <label>Descrição:</label>
    <input type="text" name="descricao" value="<%= editando ? produto.getDescricao() : "" %>" required
    pattern=".*[a-zA-Z]+.*"
    title="O campo deve conter pelo menos uma letra.">


    <label>Preço:</label>
    <input type="number" step="0.01" name="preco" value="<%= editando ? produto.getPreco() : "" %>" required>

    <label>Quantidade:</label>
    <input type="number" name="quantidade" value="<%= editando ? produto.getQuantidade() : "" %>" required>

    <label>Categoria:</label>
    <input type="text" name="categoria" value="<%= editando ? produto.getCategoria() : "" %>" required
        pattern=".*[a-zA-Z]+.*"
        title="O campo deve conter pelo menos uma letra.">

    <label>Fornecedor:</label>
    <input type="text" name="fornecedor" value="<%= editando ? produto.getFornecedor() : "" %>" required
        pattern=".*[a-zA-Z]+.*"
        title="O campo deve conter pelo menos uma letra.">

    <label>Data de Validade:</label>
    <input type="date" name="dataValidade" id="dataValidade" value="<%= editando ? produto.getDataValidade() : "" %>" required
    oninvalid="this.setCustomValidity('A data de validade não pode ser anterior à data atual.')"
    oninput="this.setCustomValidity('')">

    <label>Peso:</label>
    <input type="number" step="0.01" name="peso" value="<%= editando ? produto.getPeso() : "" %>" required>

    <label>Marca:</label>
    <input type="text" name="marca" value="<%= editando ? produto.getMarca() : "" %>" required
        pattern=".*[a-zA-Z]+.*"
        title="O campo deve conter pelo menos uma letra.">

    <button type="submit"><%= editando ? "Atualizar" : "Cadastrar" %></button>
       
</form>
<script>
    window.onload = function() {
        const today = new Date().toISOString().split("T")[0];
        document.getElementById("dataValidade").setAttribute("min", today);       //data
    };
</script>


</body>
</html>

