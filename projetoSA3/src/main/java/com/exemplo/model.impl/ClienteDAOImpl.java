package com.exemplo.model.impl;

import com.exemplo.model.Cliente;
import com.exemplo.model.ClienteDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/databasetestesa";
    private static final String USUARIO = "usuarioTeste";
    private static final String SENHA = "senhaTeste";

    @Override
    public void inserir(Cliente cliente) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setInt(3, cliente.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "DELETE FROM clientes WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM clientes WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return construirCliente(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String sql = "SELECT * FROM clientes";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        clientes.add(construirCliente(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private Cliente construirCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }
}
