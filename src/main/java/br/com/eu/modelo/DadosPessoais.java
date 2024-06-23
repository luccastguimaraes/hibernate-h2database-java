package br.com.eu.modelo;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class DadosPessoais implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String nome;
  private String cpf;

  public DadosPessoais(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
  }

  public DadosPessoais() {
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }
}
