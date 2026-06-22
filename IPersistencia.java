public interface IPersistencia {
    /**
     * Grava um objeto em um arquivo.
     *
     * @param obj objeto que sera serializado
     * @param nomeArq caminho ou nome do arquivo de destino
     * @return true se gravou com sucesso; false se ocorreu erro
     */
    boolean gravarObj(Object obj, String nomeArq);

    /**
     * Le um objeto de um arquivo.
     *
     * @param nomeArq caminho ou nome do arquivo de origem
     * @return objeto lido ou null se ocorrer erro
     */
    Object lerObj(String nomeArq);
}
