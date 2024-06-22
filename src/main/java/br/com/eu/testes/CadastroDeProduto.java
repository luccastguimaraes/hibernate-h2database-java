package br.com.eu.testes;

import br.com.eu.dao.ProdutoDao;
import br.com.eu.dao.CategoriaDao;
import br.com.eu.modelo.Categoria;
import br.com.eu.modelo.Produto;
import br.com.eu.util.JPAUtil;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

public class CadastroDeProduto {

    public static void cadastrarProduto() {
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
