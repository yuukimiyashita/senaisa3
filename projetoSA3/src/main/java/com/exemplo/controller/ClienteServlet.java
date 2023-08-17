package com.exemplo.controller;

import com.exemplo.model.Cliente;
import com.exemplo.model.ClienteDAO;
import com.exemplo.model.impl.ClienteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clientes/*")
public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/":
                listarClientes(request, response);
                break;
            case "/novo":
                exibirFormulario(request, response);
                break;
            case "/editar":
                exibirFormularioEdicao(request, response);
                break;
            case "/excluir":
                excluirCliente(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/novo":
                inserirCliente(request, response);
                break;
            case "/editar":
                atualizarCliente(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.listarTodos();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/listarClientes.jsp").forward(request, response);
    }

    private void exibirFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/formularioCliente.jsp").forward(request, response);
    }

    private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarPorId(id);
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("/formularioCliente.jsp").forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);

        clienteDAO.inserir(cliente);

        response.sendRedirect(request.getContextPath() + "/clientes/");
    }

    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setEmail(email);

        clienteDAO.atualizar(cliente);

        response.sendRedirect(request.getContextPath() + "/clientes/");
    }

    private void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        clienteDAO.excluir(id);

        response.sendRedirect(request.getContextPath() + "/clientes/");
    }
}
