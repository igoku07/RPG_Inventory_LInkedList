import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe principal do sistema de inventario RPG.
 * Responsavel por criar a interface grafica Swing e integrar bau,
 * inventario e persistencia.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thieery Nadjarian da Silva
 * @author Guilherme Pequeneza
 */
public class App extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String ARQUIVO_PADRAO = "inventario.dat";

    /** Inventario atualmente exibido e manipulado pela interface. */
    private Inventario inventario;
    /** Bau responsavel por gerar novos itens aleatorios. */
    private final Bau bau;
    /** Objeto usado para salvar e carregar o inventario em arquivo. */
    private final ArquivoBinario persistencia;
    /** Painel central onde os cards dos itens sao exibidos. */
    private final JPanel painelGrade;
    /** Texto de status com a quantidade atual de itens. */
    private final JLabel status;

    /**
     * Cria a janela principal e inicializa os objetos do sistema.
     */
    public App() {
        super("Inventario RPG");
        inventario = new Inventario();
        bau = new Bau();
        persistencia = new ArquivoBinario(ARQUIVO_PADRAO);
        painelGrade = new JPanel(new GridLayout(0, 4, 12, 12));
        status = new JLabel("Inventario vazio");

        configurarJanela();
        montarInterface();
        atualizarGrade();
    }

    /**
     * Define configuracoes basicas da janela Swing.
     */
    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
        setMinimumSize(new Dimension(860, 560));
        setLocationRelativeTo(null);
    }

    /**
     * Monta os paineis, botoes e area de grade da interface.
     */
    private void montarInterface() {
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton btnAbrirBau = new JButton("Abrir Baú");
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCarregar = new JButton("Carregar");

        btnAbrirBau.addActionListener(e -> abrirBau());
        btnSalvar.addActionListener(e -> salvarInventario());
        btnCarregar.addActionListener(e -> carregarInventario());

        painelSuperior.add(btnAbrirBau);
        painelSuperior.add(btnSalvar);
        painelSuperior.add(btnCarregar);
        painelSuperior.add(status);

        painelGrade.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        painelGrade.setBackground(new Color(245, 246, 248));

        JScrollPane scroll = new JScrollPane(painelGrade);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    /**
     * Acao do botao Abrir Bau: gera um item e adiciona ao inventario.
     */
    private void abrirBau() {
        Item item = bau.abrir();
        inventario.adicionarItem(item);
        atualizarGrade();
        mostrarMensagem("Item encontrado: " + item.getNome());
    }

    /**
     * Acao do botao Salvar: escolhe um arquivo e grava o inventario.
     */
    private void salvarInventario() {
        File arquivo = escolherArquivoParaSalvar();
        if (arquivo == null) {
            return;
        }

        boolean salvo = persistencia.gravarObj(inventario, arquivo.getAbsolutePath());
        if (salvo) {
            mostrarMensagem("Inventario salvo em " + arquivo.getName());
        } else {
            mostrarErro("Nao foi possivel salvar o inventario.");
        }
    }

    /**
     * Acao do botao Carregar: escolhe um arquivo e substitui o inventario atual.
     */
    private void carregarInventario() {
        File arquivo = escolherArquivoParaCarregar();
        if (arquivo == null) {
            return;
        }

        Object obj = persistencia.lerObj(arquivo.getAbsolutePath());
        if (obj instanceof Inventario) {
            inventario = (Inventario) obj;
            atualizarGrade();
            mostrarMensagem("Inventario carregado de " + arquivo.getName());
        } else {
            mostrarErro("Nenhum inventario valido foi encontrado.");
        }
    }

    /**
     * Abre uma janela para o usuario escolher onde salvar o inventario.
     *
     * @return arquivo escolhido ou null se o usuario cancelar
     */
    private File escolherArquivoParaSalvar() {
        JFileChooser seletor = criarSeletorArquivo();
        seletor.setSelectedFile(new File(ARQUIVO_PADRAO));

        int opcao = seletor.showSaveDialog(this);
        if (opcao != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File arquivo = seletor.getSelectedFile();
        if (!arquivo.getName().toLowerCase().endsWith(".dat")) {
            arquivo = new File(arquivo.getParentFile(), arquivo.getName() + ".dat");
        }

        if (arquivo.exists()) {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "O arquivo ja existe. Deseja sobrescrever?",
                    "Confirmar salvamento",
                    JOptionPane.YES_NO_OPTION);
            if (resposta != JOptionPane.YES_OPTION) {
                return null;
            }
        }

        return arquivo;
    }

    /**
     * Abre uma janela para o usuario escolher qual inventario carregar.
     *
     * @return arquivo escolhido ou null se o usuario cancelar
     */
    private File escolherArquivoParaCarregar() {
        JFileChooser seletor = criarSeletorArquivo();

        int opcao = seletor.showOpenDialog(this);
        if (opcao != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        return seletor.getSelectedFile();
    }

    /**
     * Cria o seletor de arquivos usado para salvar e carregar.
     *
     * @return JFileChooser configurado para arquivos .dat
     */
    private JFileChooser criarSeletorArquivo() {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Escolha o arquivo do inventario");
        seletor.setFileFilter(new FileNameExtensionFilter("Arquivos de inventario (*.dat)", "dat"));
        return seletor;
    }

    /**
     * Atualiza a grade visual de acordo com os itens existentes no inventario.
     */
    private void atualizarGrade() {
        painelGrade.removeAll();

        if (inventario.getQuantidadeItens() == 0) {
            JLabel vazio = new JLabel("Nenhum item no inventario. Abra o bau para gerar itens.", SwingConstants.CENTER);
            vazio.setFont(vazio.getFont().deriveFont(Font.BOLD, 16f));
            painelGrade.setLayout(new BorderLayout());
            painelGrade.add(vazio, BorderLayout.CENTER);
        } else {
            painelGrade.setLayout(new GridLayout(0, 4, 12, 12));
            for (Item item : inventario.getLista()) {
                painelGrade.add(criarCardItem(item));
            }
        }

        status.setText("Itens: " + inventario.getQuantidadeItens());
        painelGrade.revalidate();
        painelGrade.repaint();
    }

    /**
     * Cria o card visual de um item na grade.
     *
     * @param item item que sera exibido no card
     * @return painel contendo imagem, textos e botoes do item
     */
    private JPanel criarCardItem(Item item) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(8, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(item.getRaridade().getCor(), 3),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel imagem = new JLabel(new ImageIcon(item.getImagem()), SwingConstants.CENTER);
        JLabel nome = new JLabel("<html><center>" + item.getNome() + "</center></html>", SwingConstants.CENTER);
        nome.setFont(nome.getFont().deriveFont(Font.BOLD, 13f));

        JLabel raridade = new JLabel(item.getRaridade().name(), SwingConstants.CENTER);
        raridade.setForeground(item.getRaridade().getCor());
        raridade.setFont(raridade.getFont().deriveFont(Font.BOLD, 12f));

        JLabel tipo = new JLabel(item.getTipoItem().getNomeTipo(), SwingConstants.CENTER);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(nome);
        textos.add(raridade);
        textos.add(tipo);

        JButton btnDetalhes = new JButton("Detalhes");
        btnDetalhes.addActionListener(e -> exibirDetalhes(item));

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> removerItem(item));

        JPanel acoes = new JPanel();
        acoes.setOpaque(false);
        acoes.setLayout(new BoxLayout(acoes, BoxLayout.X_AXIS));
        acoes.add(btnDetalhes);
        acoes.add(Box.createHorizontalStrut(6));
        acoes.add(btnRemover);

        card.add(imagem, BorderLayout.NORTH);
        card.add(textos, BorderLayout.CENTER);
        card.add(acoes, BorderLayout.SOUTH);
        card.setToolTipText(item.getDescricao() + " " + item.getTipoItem().getDescricaoAtributos());
        return card;
    }

    /**
     * Remove um item do inventario apos confirmacao do usuario.
     *
     * @param item item que deve ser removido
     */
    private void removerItem(Item item) {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Remover o item \"" + item.getNome() + "\" do inventario?",
                "Remover item",
                JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        if (inventario.removerItem(item)) {
            atualizarGrade();
            mostrarMensagem("Item removido: " + item.getNome());
        } else {
            mostrarErro("Nao foi possivel remover o item.");
        }
    }

    /**
     * Mostra uma janela com os detalhes completos do item.
     *
     * @param item item cujas informacoes serao exibidas
     */
    private void exibirDetalhes(Item item) {
        String mensagem = "Nome: " + item.getNome()
                + "\nDescricao: " + item.getDescricao()
                + "\nRaridade: " + item.getRaridade()
                + "\nTipo: " + item.getTipoItem().getNomeTipo()
                + "\nAtributos: " + item.getTipoItem().getDescricaoAtributos();

        JOptionPane.showMessageDialog(this, mensagem, "Detalhes do Item", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(item.getImagem()));
    }

    /**
     * Exibe uma mensagem informativa para o usuario.
     *
     * @param mensagem texto que sera mostrado
     */
    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Inventario RPG", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de erro para o usuario.
     *
     * @param mensagem texto de erro que sera mostrado
     */
    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Ponto de entrada do programa. Inicializa a interface usando a EDT do Swing.
     *
     * @param args argumentos de linha de comando nao utilizados
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("Nao foi possivel aplicar o visual do sistema: " + ex.getMessage());
            }

            App app = new App();
            app.setVisible(true);
        });
    }
}
