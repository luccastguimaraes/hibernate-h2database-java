package br.com.eu.dao;

import br.com.eu.modelo.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {

    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void remover(Cliente cliente){
        cliente = this.em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscarPorId(Long id){
        return em.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodos() {
        String jpql = "SELECT p FROM Cliente p";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }
}
