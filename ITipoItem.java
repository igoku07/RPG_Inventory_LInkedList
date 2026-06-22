import java.io.Serializable;

public interface ITipoItem extends Serializable {
    /**
     * Retorna o dano do item, quando o tipo possuir esse atributo.
     *
     * @return valor de dano ou 0 quando nao se aplica
     */
    int getDano();

    /**
     * Retorna a defesa do item, quando o tipo possuir esse atributo.
     *
     * @return valor de defesa ou 0 quando nao se aplica
     */
    int getDefesa();

    /**
     * Retorna o alcance do item, quando o tipo possuir esse atributo.
     *
     * @return valor de alcance ou 0 quando nao se aplica
     */
    int getAlcance();

    /**
     * Retorna o nome da classe/tipo do item para exibicao.
     *
     * @return nome do tipo do item
     */
    String getNomeTipo();

    /**
     * Monta uma descricao textual dos atributos especificos do tipo.
     *
     * @return texto com os atributos do item
     */
    String getDescricaoAtributos();
}
