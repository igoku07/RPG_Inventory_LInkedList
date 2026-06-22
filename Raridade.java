import java.awt.Color;
import java.io.Serializable;

/**
 * Enum que representa as raridades possiveis dos itens.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thieery Nadjarian da Silva
 * @author Guilherme Pequeneza
 */
public enum Raridade implements Serializable {
    /** Raridade basica, usada para itens mais comuns. */
    COMUM(new Color(130, 130, 130)),
    /** Raridade intermediaria, usada para itens melhores que os comuns. */
    RARO(new Color(42, 117, 211)),
    /** Raridade alta, usada para itens poderosos. */
    EPICO(new Color(130, 64, 190)),
    /** Raridade maxima, usada para itens especiais. */
    LENDARIO(new Color(222, 155, 35));

    /** Cor associada a raridade para uso visual na interface. */
    private final Color cor;

    /**
     * Cria uma raridade associada a uma cor usada na interface.
     *
     * @param cor cor visual usada para bordas e detalhes do item
     */
    Raridade(Color cor) {
        this.cor = cor;
    }

    /**
     * Retorna a cor que representa esta raridade na tela.
     *
     * @return cor da raridade
     */
    public Color getCor() {
        return cor;
    }
}
