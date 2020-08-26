package vue;

import interfaces.ChatEventI;
import interfaces.ChatUpdateI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ChatIHM implements ChatUpdateI {


    //Composants graphiques
    private JFrame frame;
    private JTextField tvMsg;
    private JTextPane tvTchat;
    private JButton btEnvoyer;
    private JTextField tfpseudo;
    private JTextField tfMdp;
    private JLabel tvError;
    private JButton btInscription;
    private JButton btConnexion;
    private JButton btRafraichir;
    private JLabel tvPseudo;
    private JLabel tvMdp;

    //Controler
    private ChatEventI controler;


    /**
     * Create the application.
     *
     * @param controler
     */
    public ChatIHM(ChatEventI controler) {
        this.controler = controler;
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 727, 428);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        tvTchat = new JTextPane();
        tvTchat.setContentType("text/html");
        tvTchat.setBounds(10, 11, 437, 306);
        frame.getContentPane().add(tvTchat);

        btEnvoyer = new JButton("Envoyer");
        btEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //Un clic sur le bouton envoyer declanche la méthode actionperformed
                controler.onBtEnvoyerClick(tvMsg.getText());
            }
        });
        btEnvoyer.setBounds(470, 355, 89, 23);
        frame.getContentPane().add(btEnvoyer);

        tvMsg = new JTextField();
        tvMsg.setBounds(10, 356, 437, 20);
        frame.getContentPane().add(tvMsg);
        tvMsg.setColumns(10);

        tvPseudo = new JLabel("Pseudo");
        tvPseudo.setBounds(457, 16, 46, 14);
        frame.getContentPane().add(tvPseudo);

        tvMdp = new JLabel("Mdp");
        tvMdp.setBounds(457, 40, 46, 14);
        frame.getContentPane().add(tvMdp);

        tfpseudo = new JTextField();
        tfpseudo.setBounds(542, 13, 127, 20);
        frame.getContentPane().add(tfpseudo);
        tfpseudo.setColumns(10);

        tfMdp = new JTextField();
        tfMdp.setBounds(542, 37, 127, 20);
        frame.getContentPane().add(tfMdp);
        tfMdp.setColumns(10);

        btInscription = new JButton("Inscription");
        btInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controler.onBtInscriptionClick(tfpseudo.getText(), tfMdp.getText());
            }
        });
        btInscription.setBounds(580, 67, 89, 23);
        frame.getContentPane().add(btInscription);

        btConnexion = new JButton("Connexion");
        btConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controler.onBtConnexionClick(tfpseudo.getText(), tfMdp.getText());
            }
        });
        btConnexion.setBounds(467, 68, 106, 22);
        frame.getContentPane().add(btConnexion);

        btRafraichir = new JButton("Rafraichir");
        btRafraichir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controler.onBtRafraichirClick();
            }
        });
        btRafraichir.setBounds(580, 355, 89, 23);
        frame.getContentPane().add(btRafraichir);

        tvError = new JLabel("New label");
        tvError.setForeground(Color.RED);
        tvError.setHorizontalAlignment(SwingConstants.CENTER);
        tvError.setBounds(457, 101, 244, 37);
        frame.getContentPane().add(tvError);
    }

    /* -------------------------------- */
    // interface
    /* -------------------------------- */

    @Override
    public void updateError(String errorMessage) {
        tvError.setText(errorMessage);
    }

    @Override
    public void updateMessagesList(String message) {
        tvTchat.setText(message.replaceAll("\n", "<br />"));
    }

    @Override
    public void clearSendMessage() {
        tvMsg.setText("");
    }

    @Override
    public void setConnectedState(boolean connected) {
        btConnexion.setVisible(!connected);
        btInscription.setVisible(!connected);
        tvMdp.setVisible(!connected);
        tfMdp.setVisible(!connected);
        tfpseudo.setEnabled(!connected);

        btEnvoyer.setEnabled(connected);
        btRafraichir.setEnabled(connected);
        tvMsg.setEnabled(connected);
    }
}
