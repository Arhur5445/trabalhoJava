package entidade;

public abstract class Entidade {
    protected boolean ativo;

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}