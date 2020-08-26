package projet

import interfaces.ChatEventI
import interfaces.ChatUpdateI
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import vue.ChatIHM


fun main(): Unit = runBlocking {
    launch {
        val controler: ChatEventI = ChatControler()
    }
}

class ChatControler : ChatEventI {

    //On créer l'interface graphique en donnant la référence du controleur
    val ihm: ChatUpdateI = ChatIHM(this)


    /* -------------------------------- */
    // Evenement de l'interface graphique
    /* -------------------------------- */

    /**
     * Un clic sur le bouton envoyer à eu lieu
     * @message : Le message de la zone de texte
     */
    override fun onBtEnvoyerClick(message: String) {
        //TODO
        ihm.updateMessagesList("<b><font color=\"yellow\">Message a envoyer : $message </font></b><br />")
    }

    /**
     * Un clic sur le bouton rafraichir a eu lieu
     */
    override fun onBtRafraichirClick() {
        //TODO
        ihm.updateMessagesList("<b><font color=\"red\">Click sur rafraichir</font></b><br />")
    }

    /**
     * Un clic sur le bouton Connexion a eu lieu
     * @pseudo : le pseudo dans la zone de texte
     * @mdp : le mdp dans la zone de texte
     */
    override fun onBtConnexionClick(pseudo: String, mdp: String) {

        try {
            //TODO faire la requete
            ihm.updateMessagesList("<b><font color=\"green\">Connecté</font></b><br />")
            ihm.setConnectedState(true)
        } catch (e: Exception) {
            ihm.updateError("${e.message}")
        }


    }

    /**
     * Un clic sur le bouton Inscription a eu lieu
     * @pseudo : le pseudo dans la zone de texte
     * @mdp : le mdp dans la zone de texte
     */
    override fun onBtInscriptionClick(pseudo: String, mdp: String) {
        //TODO
        ihm.updateMessagesList("<b><font color=\"blue\">inscrit</font></b><br />")
        ihm.setConnectedState(true)
    }


}