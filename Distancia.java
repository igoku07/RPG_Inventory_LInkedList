public class Distancia implements ITipoItem {
    private static final long serialVersionUID = 1L;

    private int dano;
    private int alcance;

    /**
     * Cria um tipo de item de ataque a distancia.
     *
     * @param dano valor de dano causado pelo item
     * @param alcance distancia de uso do item
     */
    public Distancia(int dano, int alcance) {
        this.dano = dano;
        this.alcance = alcance;
    }

    /**
     * Retorna o dano do item a distancia.
     *
     * @return dano do item
     */
    @Override
    public int getDano() {
        return dano;
    }

    /**
     * Distancia nao usa defesa.
     *
     * @return sempre 0
     */
    @Override
    public int getDefesa() {
        return 0;
    }

    /**
     * Retorna o alcance do item.
     *
     * @return alcance do item
     */
    @Override
    public int getAlcance() {
        return alcance;
    }

    /**
     * Retorna o nome do tipo para exibicao na interface.
     *
     * @return texto "Distancia"
     */
    @Override
    public String getNomeTipo() {
        return "Distancia";
    }

    /**
     * Monta o texto com os atributos deste tipo.
     *
     * @return descricao contendo dano e alcance
     */
    @Override
    public String getDescricaoAtributos() {
        return "Dano: " + dano + " | Alcance: " + alcance;
    }
}
