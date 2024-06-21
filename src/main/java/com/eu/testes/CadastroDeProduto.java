package com.eu.testes;

import com.eu.dao.CategoriaDao;
import com.eu.dao.ProdutoDao;
import com.eu.modelo.Categoria;
import com.eu.modelo.Produto;
import com.eu.util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto = produtoDao.buscarPorId(1L);
        System.out.println(produto.getPreco());

        List<Produto> produtoList = produtoDao.buscarTodos();
        produtoList.forEach(System.out::println);

        List<Produto> produtoList2 = produtoDao.buscarPorNome("Xiaomi Redmi");
        produtoList2.forEach(System.out::println);

        List<Produto> produtoList3 = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        produtoList3.forEach(System.out::println);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
