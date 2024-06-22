package br.edu.br.modelos;

import java.time.LocalDateTime;

public class Instrutor extends Pessoa{
    private Double salario;
    private String email;
    private LocalDateTime dataAdmissao;

    public Instrutor(String nome) {
        super(nome);
    }
    public Instrutor(String nome, int id) {
        super(nome, id);
    }
    public Instrutor(String nome, Double salario, String email) {
        super(nome);
        this.salario = salario;
        this.dataAdmissao = LocalDateTime.now();
        this.email = email;
    }
    public Instrutor(String nome, int id, Double salario, String email) {
        super(nome, id);

        this.salario = salario;
        this.dataAdmissao = LocalDateTime.now();
        this.email = email;
    }

    public Instrutor(String nome, int id, Double salario, String email, LocalDateTime dataAdmissao) {
        super(nome, id);

        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDateTime dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public String toString() {
        return "Instrutor{" +
                "salario=" + salario +
                ", email='" + email + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", avaliacoes=" + avaliacoes +
                '}';
    }
}
