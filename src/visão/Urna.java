/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visão;

import java.awt.CardLayout;
import java.awt.Color;
import modelo.*;
import controle.*;
import java.applet.Applet;
import java.applet.AudioClip;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Silas
 */
public class Urna extends javax.swing.JFrame {

    private ControleEleitor ce;
    private ControleCandidato cc;
    private ControleUrnaEletronica cUrna;
    private final CardLayout tela;
    private String ultimoUsado; // Para guardar o nome do ultimo CardLayout(Usado quando o elitor cancela o voto nulo ou branco)

    /**
     * Creates new form Urna
     */
    public Urna(){
        initComponents();
        setTitle("Urna Eletrônica");
        setResizable(false); // Tamanho fixo
        setLocationRelativeTo(null);//Aparecer no centro da tela
        this.getContentPane().setBackground(Color.WHITE);
        tela = (CardLayout) jPanel1.getLayout();
        ce = new ControleEleitor();
        cc = new ControleCandidato();
        cUrna = new ControleUrnaEletronica();

        tela.show(jPanel1, "TelaVereador");
        ultimoUsado = "TelaVereador";

        Num1P.setEditable(false); // Não permitir entrada de dados pelo teclado
        Num2P.setEditable(false);
        Num1V.setEditable(false);
        Num2V.setEditable(false);
        Num3V.setEditable(false);
        Num4V.setEditable(false);
        Num5V.setEditable(false);
        CampoNomeP.setEditable(false);
        CampoNumeroP.setEditable(false);
        CampoPartidoP.setEditable(false);
        CampoNomeV.setEditable(false);
        CampoNumeroV.setEditable(false);
        CampoPartidoV.setEditable(false);
        setDefaultCloseOperation(Urna.DO_NOTHING_ON_CLOSE);//Não permite o usuário encerrar a urna sem antes terminar a votação
    }

    public void Finaliza(){
        new Thread (){
            public void run(){
                tela.show(jPanel1, "TelaDeFim");
                for(int i=0;i<=100;i++){ // Carrega a barra de progresso
                    try {
                        sleep(5);
                        Fim.setValue(i);
                    } catch (Exception e) {
                    }
                }
                tela.show(jPanel1, "TelaFim");
                TocarAudio();
                try {
                    sleep(1500);//Espera 1 minuto e meio antes de finalizar 
                } catch (InterruptedException ex) {
                    Logger.getLogger(Urna.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose(); //Fecha a tela Urna
            }
        }.start();
    }
    
    public void TocarAudio() {
        URL url = getClass().getResource("Som.wav");
        AudioClip audio = Applet.newAudioClip(url);
        audio.play();
    }

    public int NumeroCand() {
        int num = 0;
        if (PanelVereador.isShowing()) {
            int n1 = Integer.parseInt(Num1V.getText()) * 10000;
            int n2 = Integer.parseInt(Num2V.getText()) * 1000;
            int n3 = Integer.parseInt(Num3V.getText()) * 100;
            int n4 = Integer.parseInt(Num4V.getText()) * 10;
            int n5 = Integer.parseInt(Num5V.getText());
            num = n1 + n2 + n3 + n4 + n5;
        }
        if (PanelPrefeito.isShowing()) {
            int n6 = Integer.parseInt(Num1P.getText()) * 10;
            int n7 = Integer.parseInt(Num2P.getText());
            num = n6 + n7;
        }
        return num;
    }

    public void LimpaCampos() {
        if (PanelPrefeito.isShowing()) {
            Num1P.setText("");
            Num2P.setText("");
            CampoNomeP.setText("");
            CampoNumeroP.setText("");
            CampoPartidoP.setText("");
            ImagemP.setIcon(null);
        }
        if (PanelVereador.isShowing()) {
            Num1V.setText("");
            Num2V.setText("");
            Num3V.setText("");
            Num4V.setText("");
            Num5V.setText("");
            CampoNomeV.setText("");
            CampoNumeroV.setText("");
            CampoPartidoV.setText("");
            ImagemV.setIcon(null);
        }
    }

    public boolean CamposPreenchidos() {
        if (PanelPrefeito.isShowing() && !Num1P.getText().isEmpty() && !Num2P.getText().isEmpty()) {
            return true;
        }

        if (PanelVereador.isShowing() && !Num1V.getText().isEmpty() && !Num2V.getText().isEmpty()
                && !Num3V.getText().isEmpty() && !Num4V.getText().isEmpty() && !Num5V.getText().isEmpty()) {
            return true;
        }

        return false;
    }

    public void PreencheCandidatos() {
        if (CamposPreenchidos()) {
            int num = NumeroCand();
            Candidato c = cc.buscaCandidato(num);
            if (c != null) {
                if (PanelVereador.isShowing()) {
                    CampoNomeV.setText(c.getNome());
                    CampoNumeroV.setText(Integer.toString(c.getNumero()));
                    CampoPartidoV.setText(c.getPartido());
                    ImageIcon img = new ImageIcon(c.getCaminho());
                    ImagemV.setIcon(img);
                }
                if (PanelPrefeito.isShowing()) {
                    CampoNomeP.setText(c.getNome());
                    CampoNumeroP.setText(Integer.toString(c.getNumero()));
                    CampoPartidoP.setText(c.getPartido());
                    ImageIcon img = new ImageIcon(c.getCaminho());
                    ImagemP.setIcon(img);
                }
            } else {
                LimpaCampos();
                tela.show(jPanel1, "TelaNulo");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PainelBarra = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Fim = new javax.swing.JProgressBar();
        PanelBranco = new javax.swing.JPanel();
        LabelBranco = new javax.swing.JLabel();
        PanelNulo = new javax.swing.JPanel();
        LabelNulo = new javax.swing.JLabel();
        PanelFim = new javax.swing.JPanel();
        LabelFim = new javax.swing.JLabel();
        PanelVereador = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Num1V = new javax.swing.JTextField();
        Num2V = new javax.swing.JTextField();
        Num3V = new javax.swing.JTextField();
        Num4V = new javax.swing.JTextField();
        Num5V = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CampoNomeV = new javax.swing.JTextField();
        CampoNumeroV = new javax.swing.JTextField();
        CampoPartidoV = new javax.swing.JTextField();
        ImagemV = new javax.swing.JLabel();
        PanelPrefeito = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Num1P = new javax.swing.JTextField();
        Num2P = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CampoNomeP = new javax.swing.JTextField();
        CampoNumeroP = new javax.swing.JTextField();
        CampoPartidoP = new javax.swing.JTextField();
        ImagemP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        BotBranco = new javax.swing.JToggleButton();
        BotCancelar = new javax.swing.JToggleButton();
        BotConfirmar = new javax.swing.JToggleButton();
        But0 = new javax.swing.JButton();
        But1 = new javax.swing.JButton();
        But2 = new javax.swing.JButton();
        But3 = new javax.swing.JButton();
        But4 = new javax.swing.JButton();
        But5 = new javax.swing.JButton();
        But6 = new javax.swing.JButton();
        But7 = new javax.swing.JButton();
        But8 = new javax.swing.JButton();
        But9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Fim.setBackground(new java.awt.Color(255, 255, 255));
        Fim.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Fim.setForeground(new java.awt.Color(0, 0, 0));
        Fim.setStringPainted(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Fim, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(Fim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelBarraLayout = new javax.swing.GroupLayout(PainelBarra);
        PainelBarra.setLayout(PainelBarraLayout);
        PainelBarraLayout.setHorizontalGroup(
            PainelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelBarraLayout.setVerticalGroup(
            PainelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PainelBarra, "TelaDeFim");

        LabelBranco.setBackground(new java.awt.Color(255, 255, 255));
        LabelBranco.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        LabelBranco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelBranco.setText("BRANCO");
        LabelBranco.setOpaque(true);

        javax.swing.GroupLayout PanelBrancoLayout = new javax.swing.GroupLayout(PanelBranco);
        PanelBranco.setLayout(PanelBrancoLayout);
        PanelBrancoLayout.setHorizontalGroup(
            PanelBrancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelBranco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelBrancoLayout.setVerticalGroup(
            PanelBrancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelBranco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelBranco, "TelaBranco");

        LabelNulo.setBackground(new java.awt.Color(255, 255, 255));
        LabelNulo.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        LabelNulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelNulo.setText("NULO");
        LabelNulo.setOpaque(true);

        javax.swing.GroupLayout PanelNuloLayout = new javax.swing.GroupLayout(PanelNulo);
        PanelNulo.setLayout(PanelNuloLayout);
        PanelNuloLayout.setHorizontalGroup(
            PanelNuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelNulo, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );
        PanelNuloLayout.setVerticalGroup(
            PanelNuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelNulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelNulo, "TelaNulo");

        LabelFim.setBackground(new java.awt.Color(255, 255, 255));
        LabelFim.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        LabelFim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelFim.setText("FIM");
        LabelFim.setOpaque(true);

        javax.swing.GroupLayout PanelFimLayout = new javax.swing.GroupLayout(PanelFim);
        PanelFim.setLayout(PanelFimLayout);
        PanelFimLayout.setHorizontalGroup(
            PanelFimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelFimLayout.setVerticalGroup(
            PanelFimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelFim, "TelaFim");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seu voto para vereador(a):");
        jLabel1.setOpaque(true);

        Num1V.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num1V.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num1V.setFocusable(false);
        Num1V.setRequestFocusEnabled(false);

        Num2V.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num2V.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num2V.setFocusable(false);

        Num3V.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num3V.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num3V.setFocusable(false);

        Num4V.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num4V.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num4V.setFocusable(false);

        Num5V.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num5V.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num5V.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nome:");

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Número:");

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Partido:");

        CampoNomeV.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoNomeV.setFocusable(false);

        CampoNumeroV.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoNumeroV.setFocusable(false);

        CampoPartidoV.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoPartidoV.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        CampoPartidoV.setFocusable(false);

        ImagemV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImagemV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImagemV.setFocusable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoNomeV)
                            .addComponent(CampoNumeroV)
                            .addComponent(CampoPartidoV)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ImagemV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(Num1V, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Num2V, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Num3V, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Num4V, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Num5V, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Num3V, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Num2V, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Num1V, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Num5V, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Num4V, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(ImagemV, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNomeV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNumeroV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoPartidoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        Num1V.getAccessibleContext().setAccessibleName("Campo1");
        Num1V.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout PanelVereadorLayout = new javax.swing.GroupLayout(PanelVereador);
        PanelVereador.setLayout(PanelVereadorLayout);
        PanelVereadorLayout.setHorizontalGroup(
            PanelVereadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelVereadorLayout.setVerticalGroup(
            PanelVereadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelVereador, "TelaVereador");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seu voto para prefeito(a):");
        jLabel2.setOpaque(true);

        Num1P.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num1P.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num1P.setFocusable(false);

        Num2P.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        Num2P.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Num2P.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nome:");

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Número:");

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Partido:");

        CampoNomeP.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoNomeP.setFocusable(false);

        CampoNumeroP.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoNumeroP.setFocusable(false);

        CampoPartidoP.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        CampoPartidoP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        CampoPartidoP.setFocusable(false);

        ImagemP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ImagemP.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CampoNomeP, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(CampoNumeroP)
                            .addComponent(CampoPartidoP)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(Num1P, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Num2P, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(ImagemP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Num2P, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Num1P, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(ImagemP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNomeP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoNumeroP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoPartidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout PanelPrefeitoLayout = new javax.swing.GroupLayout(PanelPrefeito);
        PanelPrefeito.setLayout(PanelPrefeitoLayout);
        PanelPrefeitoLayout.setHorizontalGroup(
            PanelPrefeitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelPrefeitoLayout.setVerticalGroup(
            PanelPrefeitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(PanelPrefeito, "TelaPrefeito");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        BotBranco.setBackground(new java.awt.Color(255, 255, 255));
        BotBranco.setText("BRANCO");
        BotBranco.setFocusable(false);
        BotBranco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotBrancoActionPerformed(evt);
            }
        });

        BotCancelar.setBackground(new java.awt.Color(255, 153, 51));
        BotCancelar.setText("CANCELAR");
        BotCancelar.setFocusable(false);
        BotCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotCancelarActionPerformed(evt);
            }
        });

        BotConfirmar.setBackground(new java.awt.Color(51, 204, 0));
        BotConfirmar.setText("CONFIRMAR");
        BotConfirmar.setFocusable(false);
        BotConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotConfirmarActionPerformed(evt);
            }
        });

        But0.setBackground(new java.awt.Color(0, 0, 0));
        But0.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But0.setForeground(new java.awt.Color(255, 255, 255));
        But0.setText("0");
        But0.setFocusable(false);
        But0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But0ActionPerformed(evt);
            }
        });

        But1.setBackground(new java.awt.Color(0, 0, 0));
        But1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But1.setForeground(new java.awt.Color(255, 255, 255));
        But1.setText("1");
        But1.setFocusable(false);
        But1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But1ActionPerformed(evt);
            }
        });

        But2.setBackground(new java.awt.Color(0, 0, 0));
        But2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But2.setForeground(new java.awt.Color(255, 255, 255));
        But2.setText("2");
        But2.setFocusable(false);
        But2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But2ActionPerformed(evt);
            }
        });

        But3.setBackground(new java.awt.Color(0, 0, 0));
        But3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But3.setForeground(new java.awt.Color(255, 255, 255));
        But3.setText("3");
        But3.setFocusable(false);
        But3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But3ActionPerformed(evt);
            }
        });

        But4.setBackground(new java.awt.Color(0, 0, 0));
        But4.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But4.setForeground(new java.awt.Color(255, 255, 255));
        But4.setText("4");
        But4.setFocusable(false);
        But4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But4ActionPerformed(evt);
            }
        });

        But5.setBackground(new java.awt.Color(0, 0, 0));
        But5.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But5.setForeground(new java.awt.Color(255, 255, 255));
        But5.setText("5");
        But5.setFocusable(false);
        But5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But5ActionPerformed(evt);
            }
        });

        But6.setBackground(new java.awt.Color(0, 0, 0));
        But6.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But6.setForeground(new java.awt.Color(255, 255, 255));
        But6.setText("6");
        But6.setFocusable(false);
        But6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But6ActionPerformed(evt);
            }
        });

        But7.setBackground(new java.awt.Color(0, 0, 0));
        But7.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But7.setForeground(new java.awt.Color(255, 255, 255));
        But7.setText("7");
        But7.setFocusable(false);
        But7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But7ActionPerformed(evt);
            }
        });

        But8.setBackground(new java.awt.Color(0, 0, 0));
        But8.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But8.setForeground(new java.awt.Color(255, 255, 255));
        But8.setText("8");
        But8.setFocusable(false);
        But8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But8ActionPerformed(evt);
            }
        });

        But9.setBackground(new java.awt.Color(0, 0, 0));
        But9.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        But9.setForeground(new java.awt.Color(255, 255, 255));
        But9.setText("9");
        But9.setFocusable(false);
        But9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                But9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotBranco, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(But7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(But8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(But9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(But4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(But5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(But6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(But0, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(But1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(But2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(But3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(But7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(But6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(But1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(But3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(But0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotBranco, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotBrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotBrancoActionPerformed
        if(!PanelFim.isShowing())
            tela.show(jPanel1, "TelaBranco");
    }//GEN-LAST:event_BotBrancoActionPerformed

    private void But0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But0ActionPerformed
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("0");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("0");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("0");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("0");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("0");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("0");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("0");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But0ActionPerformed

    private void BotCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotCancelarActionPerformed
        LimpaCampos();

        if (PanelBranco.isShowing() && ultimoUsado.compareTo("TelaVereador") == 0) {
            tela.show(jPanel1, "TelaVereador");
            LimpaCampos();
        }

        if (PanelBranco.isShowing() && ultimoUsado.compareTo("TelaPrefeito") == 0) {
            tela.show(jPanel1, "TelaPrefeito");
            LimpaCampos();
        }

        if (PanelNulo.isShowing() && ultimoUsado.compareTo("TelaVereador") == 0) {
            tela.show(jPanel1, "TelaVereador");
            LimpaCampos();
        }

        if (PanelNulo.isShowing() && ultimoUsado.compareTo("TelaPrefeito") == 0) {
            tela.show(jPanel1, "TelaPrefeito");
            LimpaCampos();
        }
    }//GEN-LAST:event_BotCancelarActionPerformed

    private void But1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But1ActionPerformed
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("1");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("1");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("1");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("1");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("1");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("1");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("1");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }

    }//GEN-LAST:event_But1ActionPerformed

    private void But2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But2ActionPerformed
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("2");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("2");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("2");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("2");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("2");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("2");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("2");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But2ActionPerformed

    private void But3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But3ActionPerformed
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("3");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("3");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("3");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("3");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("3");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("3");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("3");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But3ActionPerformed

    private void But4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But4ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("4");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("4");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("4");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("4");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("4");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("4");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("4");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But4ActionPerformed

    private void But5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But5ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("5");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("5");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("5");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("5");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("5");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("5");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("5");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But5ActionPerformed

    private void But6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But6ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("6");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("6");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("6");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("6");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("6");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("6");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("6");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But6ActionPerformed

    private void But7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But7ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("7");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("7");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("7");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("7");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("7");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("7");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("7");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But7ActionPerformed

    private void But8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But8ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("8");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("8");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("8");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("8");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("8");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("8");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("8");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But8ActionPerformed

    private void But9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_But9ActionPerformed
        // TODO add your handling code here:
        if (PanelVereador.isShowing()) {
            if (Num1V.getText().equalsIgnoreCase("")) {
                Num1V.setText("9");
            } else if (Num2V.getText().equalsIgnoreCase("")) {
                Num2V.setText("9");
            } else if (Num3V.getText().equalsIgnoreCase("")) {
                Num3V.setText("9");
            } else if (Num4V.getText().equalsIgnoreCase("")) {
                Num4V.setText("9");
            } else if (Num5V.getText().equalsIgnoreCase("")) {
                Num5V.setText("9");
            }
        }

        if (PanelPrefeito.isShowing()) {
            if (Num1P.getText().equalsIgnoreCase("")) {
                Num1P.setText("9");
            } else if (Num2P.getText().equalsIgnoreCase("")) {
                Num2P.setText("9");
            }
        }

        if (CamposPreenchidos()) {
            PreencheCandidatos();
        }
    }//GEN-LAST:event_But9ActionPerformed

    private void BotConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotConfirmarActionPerformed

        if (CamposPreenchidos()) {
            int num = NumeroCand();
            Candidato c = cc.buscaCandidato(num);
            if (c != null) {
                cc.IncrementaVotos(num);
                if (PanelVereador.isShowing()) {
                    ultimoUsado = "TelaPrefeito";
                    tela.show(jPanel1, "TelaPrefeito");
                } else if (PanelPrefeito.isShowing()) {
                    Finaliza();
                }
            }
        }

        if (PanelBranco.isShowing() && ultimoUsado.compareTo("TelaVereador") == 0) {
            cUrna.IncrementaBrancosV();
            ultimoUsado = "TelaPrefeito";
            tela.show(jPanel1, "TelaPrefeito");
        }

        if (PanelBranco.isShowing() && ultimoUsado.compareTo("TelaPrefeito") == 0) {
            cUrna.IncrementaBrancosP();
            Finaliza();
        }

        if (PanelNulo.isShowing() && ultimoUsado.compareTo("TelaVereador") == 0) {
            cUrna.IncrementaNulosV();
            ultimoUsado = ultimoUsado = "TelaPrefeito";
            tela.show(jPanel1, "TelaPrefeito");
        }

        if (PanelNulo.isShowing() && ultimoUsado.compareTo("TelaPrefeito") == 0) {
            cUrna.IncrementaNulosP();
            Finaliza();
        }
    }//GEN-LAST:event_BotConfirmarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Urna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Urna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Urna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Urna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new Urna().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BotBranco;
    private javax.swing.JToggleButton BotCancelar;
    private javax.swing.JToggleButton BotConfirmar;
    private javax.swing.JButton But0;
    private javax.swing.JButton But1;
    private javax.swing.JButton But2;
    private javax.swing.JButton But3;
    private javax.swing.JButton But4;
    private javax.swing.JButton But5;
    private javax.swing.JButton But6;
    private javax.swing.JButton But7;
    private javax.swing.JButton But8;
    private javax.swing.JButton But9;
    private javax.swing.JTextField CampoNomeP;
    private javax.swing.JTextField CampoNomeV;
    private javax.swing.JTextField CampoNumeroP;
    private javax.swing.JTextField CampoNumeroV;
    private javax.swing.JTextField CampoPartidoP;
    private javax.swing.JTextField CampoPartidoV;
    private javax.swing.JProgressBar Fim;
    private javax.swing.JLabel ImagemP;
    private javax.swing.JLabel ImagemV;
    private javax.swing.JLabel LabelBranco;
    private javax.swing.JLabel LabelFim;
    private javax.swing.JLabel LabelNulo;
    private javax.swing.JTextField Num1P;
    private javax.swing.JTextField Num1V;
    private javax.swing.JTextField Num2P;
    private javax.swing.JTextField Num2V;
    private javax.swing.JTextField Num3V;
    private javax.swing.JTextField Num4V;
    private javax.swing.JTextField Num5V;
    private javax.swing.JPanel PainelBarra;
    private javax.swing.JPanel PanelBranco;
    private javax.swing.JPanel PanelFim;
    private javax.swing.JPanel PanelNulo;
    private javax.swing.JPanel PanelPrefeito;
    private javax.swing.JPanel PanelVereador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
