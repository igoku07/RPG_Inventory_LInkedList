import java.io.Serializable;

/**
 * Interface comum para os tipos de item do sistema.
 * Define os getters genericos usados pela interface grafica.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thieery Nadjarian da Silva
 * @author Guilherme Pequeneza
 */
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
