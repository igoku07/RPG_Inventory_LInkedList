import java.awt.Color;
import java.io.Serializable;

public enum Raridade implements Serializable {
    COMUM(new Color(130, 130, 130)),
    RARO(new Color(42, 117, 211)),
    EPICO(new Color(130, 64, 190)),
    LENDARIO(new Color(222, 155, 35));

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
