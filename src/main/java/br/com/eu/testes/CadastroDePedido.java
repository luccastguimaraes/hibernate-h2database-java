package br.com.eu.testes;

import br.com.eu.dao.ClienteDao;
import br.com.eu.dao.PedidoDao;
import br.com.eu.dao.ProdutoDao;
import br.com.eu.modelo.Produto;
import br.com.eu.modelo.Cliente;
import br.com.eu.modelo.ItemPedido;
import br.com.eu.modelo.Pedido;
import br.com.eu.util.JPAUtil;

import javax.persistence.EntityManager;

public class CadastroDePedido {

    public static void main(String[] args) {
        CadastroDeProduto.cadastrarProduto();
        cadastrarCliente();
        cadastrarPedido();

    }

    public static void cadastrarPedido(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1l);

        Cliente cliente = new ClienteDao(em).buscarPorId(1l);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(6, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();

    }

    public static void cadastrarCliente(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        ClienteDao clienteDao = new ClienteDao(em);
        clienteDao.cadastrar(new Cliente("Luccas", "02334232555"));
        em.getTransaction().commit();
        em.close();
    }

}
