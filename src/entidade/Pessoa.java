package entidade;

import java.util.ArrayList;
import java.util.List;

public class Pessoa extends Entidade {
    private String codigo;
    private String nome;
    private String tipoPessoa; 
    private List<Endereco> enderecos;

    public Pessoa(String codigo, String nome, String tipoPessoa, boolean ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.ativo = ativo;
        this.enderecos = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getTipoPessoa() { return tipoPessoa; }
    public List<Endereco> getEnderecos() { return enderecos; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
}