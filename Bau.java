import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bau {
    private final Random random = new Random();

    /**
     * Abre o bau e entrega um item aleatorio.
     *
     * @return item gerado pelo bau
     */
    public Item abrir() {
        return gerarItem();
    }

    /**
     * Gera aleatoriamente um item fisico, de distancia ou de defesa.
     *
     * @return novo item com nome, atributos, raridade e imagem pixel art
     */
    private Item gerarItem() {
        int tipo = random.nextInt(3);
        Raridade raridade = gerarRaridade();
        ITipoItem tipoItem;
        String nomeBase;
        String nome;
        String descricao;

        if (tipo == 0) {
            int dano = gerarValor(8, 26, raridade);
            tipoItem = new Fisico(dano);
            nomeBase = escolher("Espada", "Machado", "Lanca");
            nome = nomeBase + " " + sufixo(raridade);
            descricao = "Arma fisica para combate corpo a corpo.";
        } else if (tipo == 1) {
            int dano = gerarValor(6, 22, raridade);
            int alcance = gerarValor(3, 12, raridade);
            tipoItem = new Distancia(dano, alcance);
            nomeBase = escolher("Arco", "Besta", "Cajado");
            nome = nomeBase + " " + sufixo(raridade);
            descricao = "Item de ataque a distancia.";
        } else {
            int defesa = gerarValor(5, 24, raridade);
            tipoItem = new Defesa(defesa);
            nomeBase = escolher("Escudo", "Armadura", "Elmo");
            nome = nomeBase + " " + sufixo(raridade);
            descricao = "Equipamento focado em protecao.";
        }

        return new Item(nome, descricao, criarImagem(raridade, nomeBase), raridade, tipoItem);
    }

    /**
     * Sorteia a raridade do item usando porcentagens diferentes.
     *
     * @return raridade sorteada
     */
    private Raridade gerarRaridade() {
        int chance = random.nextInt(100);
        if (chance < 55) {
            return Raridade.COMUM;
        }
        if (chance < 80) {
            return Raridade.RARO;
        }
        if (chance < 95) {
            return Raridade.EPICO;
        }
        return Raridade.LENDARIO;
    }

    /**
     * Gera um valor de atributo ajustado pela raridade.
     *
     * @param minimo menor valor base possivel
     * @param maximo maior valor base possivel
     * @param raridade raridade usada como bonus no valor final
     * @return valor aleatorio final
     */
    private int gerarValor(int minimo, int maximo, Raridade raridade) {
        int base = minimo + random.nextInt(maximo - minimo + 1);
        return base + (raridade.ordinal() * 6);
    }

    /**
     * Escolhe uma opcao aleatoria entre os textos recebidos.
     *
     * @param opcoes lista de nomes possiveis
     * @return uma das opcoes recebidas
     */
    private String escolher(String... opcoes) {
        return opcoes[random.nextInt(opcoes.length)];
    }

    /**
     * Define o sufixo do nome do item de acordo com a raridade.
     *
     * @param raridade raridade do item
     * @return sufixo textual usado no nome
     */
    private String sufixo(Raridade raridade) {
        switch (raridade) {
            case RARO:
                return "do Vento";
            case EPICO:
                return "Arcano";
            case LENDARIO:
                return "Ancestral";
            case COMUM:
            default:
                return "de Treino";
        }
    }

    /**
     * Cria a imagem pixelizada do item.
     *
     * @param raridade raridade usada para cor da borda e fundo
     * @param nomeBase nome principal do item, como Espada ou Escudo
     * @return imagem gerada para o card
     */
    private BufferedImage criarImagem(Raridade raridade, String nomeBase) {
        int largura = 120;
        int altura = 90;
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagem.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        desenharFundo(g, largura, altura, raridade);
        desenharSprite(g, nomeBase);

        g.setColor(new Color(0, 0, 0, 70));
        g.fillRect(0, altura - 18, largura, 18);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(nomeBase, 8, altura - 6);

        g.setColor(raridade.getCor());
        g.setStroke(new BasicStroke(5f));
        g.drawRect(2, 2, largura - 4, altura - 4);
        g.dispose();

        return imagem;
    }

    /**
     * Desenha o fundo pixelizado do card do item.
     *
     * @param g contexto grafico onde o fundo sera desenhado
     * @param largura largura da imagem
     * @param altura altura da imagem
     * @param raridade raridade usada para colorir detalhes
     */
    private void desenharFundo(Graphics2D g, int largura, int altura, Raridade raridade) {
        Color base = raridade.getCor();
        g.setColor(new Color(24, 25, 31));
        g.fillRect(0, 0, largura, altura);
        g.setColor(new Color(base.getRed(), base.getGreen(), base.getBlue(), 70));
        g.fillRect(8, 8, largura - 16, altura - 28);
        g.setColor(new Color(255, 255, 255, 18));
        for (int y = 8; y < altura - 18; y += 12) {
            g.fillRect(8, y, largura - 16, 3);
        }
    }

    /**
     * Escolhe qual sprite pixel art sera desenhado.
     *
     * @param g contexto grafico onde o sprite sera desenhado
     * @param nomeBase nome principal do item
     */
    private void desenharSprite(Graphics2D g, String nomeBase) {
        switch (nomeBase) {
            case "Espada":
                desenharEspada(g);
                break;
            case "Machado":
                desenharMachado(g);
                break;
            case "Lanca":
                desenharLanca(g);
                break;
            case "Arco":
                desenharArco(g);
                break;
            case "Besta":
                desenharBesta(g);
                break;
            case "Cajado":
                desenharCajado(g);
                break;
            case "Escudo":
                desenharEscudo(g);
                break;
            case "Armadura":
                desenharArmadura(g);
                break;
            case "Elmo":
                desenharElmo(g);
                break;
            default:
                desenharEspada(g);
                break;
        }
    }

    /**
     * Desenha um bloco retangular em escala pixel art.
     *
     * @param g contexto grafico onde o bloco sera desenhado
     * @param cor cor do bloco
     * @param x posicao horizontal em unidades de pixel art
     * @param y posicao vertical em unidades de pixel art
     * @param w largura em unidades de pixel art
     * @param h altura em unidades de pixel art
     */
    private void px(Graphics2D g, Color cor, int x, int y, int w, int h) {
        int escala = 6;
        g.setColor(cor);
        g.fillRect(x * escala, y * escala, w * escala, h * escala);
    }

    /**
     * Desenha o sprite de uma espada.
     *
     * @param g contexto grafico onde a espada sera desenhada
     */
    private void desenharEspada(Graphics2D g) {
        Color metal = new Color(220, 225, 232);
        Color brilho = Color.WHITE;
        Color cabo = new Color(116, 73, 34);
        px(g, metal, 11, 3, 1, 7);
        px(g, metal, 10, 4, 3, 1);
        px(g, brilho, 12, 3, 1, 6);
        px(g, cabo, 9, 10, 5, 1);
        px(g, cabo, 11, 11, 1, 2);
        px(g, new Color(80, 45, 24), 10, 13, 3, 1);
    }

    /**
     * Desenha o sprite de um machado.
     *
     * @param g contexto grafico onde o machado sera desenhado
     */
    private void desenharMachado(Graphics2D g) {
        Color metal = new Color(210, 216, 220);
        Color cabo = new Color(122, 77, 38);
        px(g, cabo, 11, 4, 1, 10);
        px(g, metal, 8, 3, 4, 2);
        px(g, metal, 8, 5, 3, 2);
        px(g, metal, 12, 4, 2, 2);
        px(g, Color.WHITE, 8, 3, 1, 3);
        px(g, new Color(80, 45, 24), 10, 13, 3, 1);
    }

    /**
     * Desenha o sprite de uma lanca.
     *
     * @param g contexto grafico onde a lanca sera desenhada
     */
    private void desenharLanca(Graphics2D g) {
        Color metal = new Color(220, 225, 232);
        Color cabo = new Color(126, 82, 39);
        px(g, metal, 11, 2, 1, 1);
        px(g, metal, 10, 3, 3, 2);
        px(g, metal, 11, 5, 1, 1);
        px(g, cabo, 11, 6, 1, 8);
        px(g, new Color(80, 45, 24), 10, 13, 3, 1);
    }

    /**
     * Desenha o sprite de um arco.
     *
     * @param g contexto grafico onde o arco sera desenhado
     */
    private void desenharArco(Graphics2D g) {
        Color madeira = new Color(145, 88, 38);
        Color corda = new Color(235, 235, 220);
        Color flecha = new Color(214, 214, 214);
        px(g, madeira, 8, 4, 1, 6);
        px(g, madeira, 9, 3, 1, 2);
        px(g, madeira, 9, 9, 1, 2);
        px(g, corda, 13, 3, 1, 8);
        px(g, flecha, 8, 7, 7, 1);
        px(g, flecha, 14, 6, 1, 3);
    }

    /**
     * Desenha o sprite de uma besta.
     *
     * @param g contexto grafico onde a besta sera desenhada
     */
    private void desenharBesta(Graphics2D g) {
        Color madeira = new Color(128, 76, 36);
        Color metal = new Color(215, 215, 215);
        px(g, madeira, 7, 6, 9, 1);
        px(g, madeira, 10, 4, 3, 5);
        px(g, metal, 6, 5, 2, 3);
        px(g, metal, 15, 5, 2, 3);
        px(g, metal, 8, 6, 8, 1);
        px(g, madeira, 11, 9, 1, 4);
    }

    /**
     * Desenha o sprite de um cajado.
     *
     * @param g contexto grafico onde o cajado sera desenhado
     */
    private void desenharCajado(Graphics2D g) {
        Color madeira = new Color(116, 72, 36);
        Color cristal = new Color(95, 212, 236);
        px(g, madeira, 11, 5, 1, 9);
        px(g, madeira, 10, 4, 1, 2);
        px(g, madeira, 12, 4, 1, 2);
        px(g, cristal, 11, 2, 1, 1);
        px(g, cristal, 10, 3, 3, 1);
        px(g, cristal, 11, 4, 1, 1);
        px(g, Color.WHITE, 12, 3, 1, 1);
    }

    /**
     * Desenha o sprite de um escudo.
     *
     * @param g contexto grafico onde o escudo sera desenhado
     */
    private void desenharEscudo(Graphics2D g) {
        Color metal = new Color(190, 196, 205);
        Color detalhe = new Color(82, 132, 215);
        px(g, metal, 8, 4, 8, 1);
        px(g, metal, 7, 5, 10, 4);
        px(g, metal, 8, 9, 8, 2);
        px(g, metal, 10, 11, 4, 1);
        px(g, detalhe, 11, 5, 2, 6);
        px(g, Color.WHITE, 8, 5, 2, 1);
    }

    /**
     * Desenha o sprite de uma armadura.
     *
     * @param g contexto grafico onde a armadura sera desenhada
     */
    private void desenharArmadura(Graphics2D g) {
        Color metal = new Color(176, 184, 194);
        Color sombra = new Color(92, 100, 110);
        px(g, metal, 9, 4, 6, 2);
        px(g, metal, 8, 6, 8, 6);
        px(g, sombra, 10, 6, 1, 6);
        px(g, sombra, 13, 6, 1, 6);
        px(g, metal, 7, 7, 2, 3);
        px(g, metal, 15, 7, 2, 3);
        px(g, Color.WHITE, 9, 4, 2, 1);
    }

    /**
     * Desenha o sprite de um elmo.
     *
     * @param g contexto grafico onde o elmo sera desenhado
     */
    private void desenharElmo(Graphics2D g) {
        Color metal = new Color(184, 192, 204);
        Color sombra = new Color(72, 79, 90);
        px(g, metal, 9, 4, 6, 1);
        px(g, metal, 8, 5, 8, 4);
        px(g, metal, 9, 9, 6, 2);
        px(g, sombra, 10, 7, 4, 1);
        px(g, sombra, 11, 8, 2, 3);
        px(g, Color.WHITE, 9, 5, 2, 1);
    }
}
