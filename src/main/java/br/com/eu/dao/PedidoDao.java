package br.com.eu.dao;

import br.com.eu.modelo.Pedido;
import br.com.eu.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

  private final EntityManager em;


  public PedidoDao(EntityManager em) {
    this.em = em;
  }

  public void cadastrar(Pedido pedido) {
    this.em.persist(pedido);
  }

  public void remover(Pedido pedido) {
    pedido = this.em.merge(pedido);
    this.em.remove(pedido);
  }

  public Pedido buscarPorId(Long id) {
    return em.find(Pedido.class, id);
  }

  public Pedido buscarPedidoComCliente(Long id) {
    return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = ?1", Pedido.class)
      .setParameter(1, id)
      .getSingleResult();
  }

  public List<Pedido> buscarTodos() {
    String jpql = "SELECT p FROM Pedido p";
    return em.createQuery(jpql, Pedido.class).getResultList();
  }

  public BigDecimal valorTotalVendido() {
    String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
    return em.createQuery(jpql, BigDecimal.class)
      .getSingleResult();
  }

  public List<RelatorioDeVendasVo> relatorioDeVendas() {
    String jpql = "SELECT new br.com.eu.vo.RelatorioDeVendasVo("
      + "produto.nome, "
      + "SUM(item.quantidade) as quantidadeTotal, "
      + "MAX(pedido.data)) "
      + "FROM Pedido pedido "
      + "JOIN pedido.itens item "
      + "JOIN item.produto produto "
      + "GROUP BY produto.nome "
      + "ORDER BY quantidadeTotal DESC";
    return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
  }


}
