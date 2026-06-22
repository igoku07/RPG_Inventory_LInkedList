public class Defesa implements ITipoItem {
    private static final long serialVersionUID = 1L;

    private int defesa;

    /**
     * Cria um tipo de item defensivo.
     *
     * @param defesa valor de defesa do equipamento
     */
    public Defesa(int defesa) {
        this.defesa = defesa;
    }

    /**
     * Defesa nao usa dano.
     *
     * @return sempre 0
     */
    @Override
    public int getDano() {
        return 0;
    }

    /**
     * Retorna o valor defensivo do item.
     *
     * @return defesa do equipamento
     */
    @Override
    public int getDefesa() {
        return defesa;
    }

    /**
     * Defesa nao usa alcance.
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
     * @return texto "Defesa"
     */
    @Override
    public String getNomeTipo() {
        return "Defesa";
    }

    /**
     * Monta o texto com os atributos deste tipo.
     *
     * @return descricao contendo a defesa
     */
    @Override
    public String getDescricaoAtributos() {
        return "Defesa: " + defesa;
    }
}
