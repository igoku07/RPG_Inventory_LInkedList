/**
 * Tipo de item fisico, contendo o atributo de dano.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thierry Nadjarian Rocha da Silva
 * @author Guilherme Pequeneza
 */
public class Fisico implements ITipoItem {
    private static final long serialVersionUID = 1L;

    /** Valor de dano do item fisico. */
    private int dano;

    /**
     * Cria um tipo de item fisico.
     *
     * @param dano valor de dano causado pelo item
     */
    public Fisico(int dano) {
        this.dano = dano;
    }

    /**
     * Retorna o dano do item fisico.
     *
     * @return dano do item
     */
    @Override
    public int getDano() {
        return dano;
    }

    /**
     * Fisico nao usa defesa.
     *
     * @return sempre 0
     */
    @Override
    public int getDefesa() {
        return 0;
    }

    /**
     * Fisico nao usa alcance.
     *
     * @return sempre 0
     */
    @Override
    public int getAlcance() {
        return 0;
    }

    /**
     * Retorna o nome do tipo para exibicao na interface.
     *
     * @return texto "Fisico"
     */
    @Override
    public String getNomeTipo() {
        return "Fisico";
    }

    /**
     * Monta o texto com os atributos deste tipo.
     *
     * @return descricao contendo dano
     */
    @Override
    public String getDescricaoAtributos() {
        return "Dano: " + dano;
    }
}
