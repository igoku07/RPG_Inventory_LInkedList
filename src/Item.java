import java.awt.image.BufferedImage;

public class Item{
    enum Raridade {COMUM, RARO, EPICO, LENDARIO;}
    String nome;
    String descricao;
    BufferedImage imagem;
    Raridade rari;

    public Item(String nome, String descricao, BufferedImage imagem, Raridade rari)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.rari = rari;
    }

    public Raridade getRari() {
        return rari;
    }

    public String getDescricao() {
        return descricao;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRari(Raridade rari) {
        this.rari = rari;
    }

    public String toString() {
        return "" + getNome() + "\n" + getDescricao() + "\n" + getRari() + "\n";
    }
}