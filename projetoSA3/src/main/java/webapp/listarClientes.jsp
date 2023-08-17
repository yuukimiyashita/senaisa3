<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
    <h1>Lista de Clientes</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <% for (Cliente cliente : clientes) { %>
                <tr>
                    <td><%= cliente.getId() %></td>
                    <td><%= cliente.getNome() %></td>
                    <td><%= cliente.getEmail() %></td>
                    <td>
                        <a href="${request.contextPath}/clientes/editar?id=<%= cliente.getId() %>">Editar</a>
                        <a href="${request.contextPath}/clientes/excluir?id=<%= cliente.getId() %>">Excluir</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <a href="${request.contextPath}/clientes/novo">Novo Cliente</a>
</body>
</html>
