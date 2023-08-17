package com.exemplo.model;

import java.util.List;

public interface ClienteDAO {
    void inserir(Cliente cliente);
    void atualizar(Cliente cliente);
    void excluir(int id);
    Cliente buscarPorId(int id);
    List<Cliente> listarTodos();
}
