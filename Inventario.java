import java.io.Serializable;

/**
 * Gerencia os itens do jogador usando uma lista circular duplamente ligada.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thierry Nadjarian Rocha da Silva
 * @author Guilherme Pequeneza
 */
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Lista circular duplamente ligada que armazena os itens. */
    private ListaDuplamenteLigadaCirc lista;

    /**
     * Cria um inventario vazio com uma lista circular duplamente ligada.
     */
    public Inventario() {
        lista = new ListaDuplamenteLigadaCirc();
    }

    /**
     * Adiciona um item ao inventario.
     *
     * @param item item recebido pelo inventario
     */
    public void adicionarItem(Item item) {
        lista.adicionar(item);
    }

    /**
     * Remove um item do inventario.
     *
     * @param item item que deve ser removido
     * @return true se o item foi removido; false se nao foi encontrado
     */
    public boolean removerItem(Item item) {
        return lista.remover(item);
    }

    /**
     * Retorna a lista usada pelo inventario para exibicao e iteracao.
     *
     * @return lista circular duplamente ligada
     */
    public ListaDuplamenteLigadaCirc getLista() {
        return lista;
    }

    /**
     * Retorna a quantidade de itens armazenados.
     *
     * @return quantidade de itens no inventario
     */
    public int getQuantidadeItens() {
        return lista.getTamanho();
    }
}
