package br.com.eu.dao;

import br.com.eu.modelo.Pedido;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao {

    private final EntityManager em;


    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public void remover(Pedido pedido){
        pedido = this.em.merge(pedido);
        this.em.remove(pedido);
    }

    public Pedido buscarPorId(Long id){
        return em.find(Pedido.class, id);
    }

    public List<Pedido> buscarTodos() {
        String jpql = "SELECT p FROM Pedido p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }
}
