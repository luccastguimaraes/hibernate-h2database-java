package com.eu.dao;

import com.eu.modelo.Produto;

import javax.persistence.EntityManager;

public class ProdutoDao {

    private final EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto){
        this.em.merge(produto);
    }

    public void remover(Produto produto){
        produto = this.em.merge(produto);
        this.em.remove(produto);
    }


}
