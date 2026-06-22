import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArquivoBinario implements IPersistencia {
    private String nomeArq;

    /**
     * Cria um gerenciador de persistencia binaria.
     *
     * @param nomeArq nome padrao do arquivo usado quando outro nao for informado
     */
    public ArquivoBinario(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    /**
     * Retorna o nome padrao do arquivo.
     *
     * @return nome do arquivo padrao
     */
    public String getNomeArq() {
        return nomeArq;
    }

    /**
     * Altera o nome padrao do arquivo.
     *
     * @param nomeArq novo nome padrao
     */
    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    /**
     * Serializa um objeto em arquivo binario.
     *
     * @param obj objeto que sera gravado
     * @param nomeArq caminho do arquivo de destino
     * @return true se a gravacao ocorreu; false se houve erro de IO
     */
    @Override
    public boolean gravarObj(Object obj, String nomeArq) {
        String arquivo = resolverNome(nomeArq);
        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            saida.writeObject(obj);
            return true;
        } catch (IOException ex) {
            System.err.println("Erro ao salvar arquivo: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Le e desserializa um objeto de um arquivo binario.
     *
     * @param nomeArq caminho do arquivo de origem
     * @return objeto lido ou null se a leitura falhar
     */
    @Override
    public Object lerObj(String nomeArq) {
        String arquivo = resolverNome(nomeArq);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo))) {
            return entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Erro ao carregar arquivo: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Decide qual nome de arquivo sera usado.
     *
     * @param nomeArq nome recebido pelo metodo de salvar/carregar
     * @return nome informado, quando valido, ou o nome padrao da classe
     */
    private String resolverNome(String nomeArq) {
        if (nomeArq != null && !nomeArq.trim().isEmpty()) {
            return nomeArq;
        }
        return this.nomeArq;
    }
}
