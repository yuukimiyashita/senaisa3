<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Cliente</title>
</head>
<body>
    <h1>Formulário de Cliente</h1>
    <form action="${request.contextPath}/clientes/<%= cliente == null ? 'novo' : 'editar' %>" method="post">
        <% if (cliente != null) { %>
            <input type="hidden" name="id" value="<%= cliente.getId() %>">
        <% } %>
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= cliente == null ? '' : cliente.getNome() %>"><br>

        <label for="email">Email:</label>
        <input type="text" name="email" id="email" value="<%= cliente == null ? '' : cliente.getEmail() %>"><br>

        <input type="submit" value="Salvar">
    </form>
    <a href="${request.contextPath}/clientes/">Voltar</a>
</body>
</html>
