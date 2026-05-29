package entidade;

public class Endereco extends Entidade {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String tipoEndereco;

    public Endereco(String cep, String logradouro, String numero, String complemento, String tipoEndereco, boolean ativo) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.tipoEndereco = tipoEndereco;
        this.ativo = ativo;
    }

   
    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getTipoEndereco() { return tipoEndereco; }

    public void setCep(String cep) { this.cep = cep; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public void setTipoEndereco(String tipoEndereco) { this.tipoEndereco = tipoEndereco; }
}