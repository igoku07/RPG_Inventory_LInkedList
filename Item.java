import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private transient BufferedImage imagem;
    private Raridade raridade;
    private ITipoItem tipoItem;

    /**
     * Cria um item completo para o inventario.
     *
     * @param nome nome exibido do item
     * @param descricao descricao textual do item
     * @param imagem imagem usada no card da interface
     * @param raridade raridade do item
     * @param tipoItem objeto que define atributos especificos do item
     */
    public Item(String nome, String descricao, BufferedImage imagem, Raridade raridade, ITipoItem tipoItem) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.raridade = raridade;
        this.tipoItem = tipoItem;
    }

    /**
     * Retorna o nome do item.
     *
     * @return nome do item
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do item.
     *
     * @param nome novo nome do item
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a descricao do item.
     *
     * @return descricao do item
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Altera a descricao do item.
     *
     * @param descricao nova descricao do item
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a imagem exibida no inventario.
     *
     * @return imagem do item
     */
    public BufferedImage getImagem() {
        return imagem;
    }

    /**
     * Altera a imagem exibida no inventario.
     *
     * @param imagem nova imagem do item
     */
    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    /**
     * Retorna a raridade do item.
     *
     * @return raridade do item
     */
    public Raridade getRaridade() {
        return raridade;
    }

    /**
     * Altera a raridade do item.
     *
     * @param raridade nova raridade do item
     */
    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    /**
     * Retorna o objeto que guarda os atributos especificos do item.
     *
     * @return tipo do item
     */
    public ITipoItem getTipoItem() {
        return tipoItem;
    }

    /**
     * Altera o tipo e os atributos especificos do item.
     *
     * @param tipoItem novo tipo do item
     */
    public void setTipoItem(ITipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

    /**
     * Salva o item em arquivo, convertendo a BufferedImage para bytes PNG.
     *
     * @param out stream de serializacao usado pelo Java
     * @throws IOException caso ocorra erro ao gravar a imagem ou o objeto
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (imagem == null) {
            out.writeInt(0);
            return;
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imagem, "png", buffer);
        byte[] bytes = buffer.toByteArray();
        out.writeInt(bytes.length);
        out.write(bytes);
    }

    /**
     * Le o item do arquivo, reconstruindo a BufferedImage a partir dos bytes PNG.
     *
     * @param in stream de leitura usado pelo Java
     * @throws IOException caso ocorra erro ao ler a imagem ou o objeto
     * @throws ClassNotFoundException caso alguma classe serializada nao seja encontrada
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int tamanho = in.readInt();
        if (tamanho > 0) {
            byte[] bytes = new byte[tamanho];
            in.readFully(bytes);
            imagem = ImageIO.read(new ByteArrayInputStream(bytes));
        }
    }
}
