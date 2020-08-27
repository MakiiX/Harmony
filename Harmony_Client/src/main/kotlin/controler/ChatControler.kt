package projet

import interfaces.ChatEventI
import interfaces.ChatUpdateI
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.*
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
        if(CURRENT_USER != null) {
            try {
                sendMsg(MsgBean(text = message, user = UserBean(idSession = CURRENT_USER!!.idSession)))
                ihm.updateMessagesList("<b><font color=\"yellow\">Message envoyé</font></b><br />")
            }catch (erreur:Exception) {
                erreur.printStackTrace()
            }
        }else{
            ihm.updateMessagesList("<b><font color=\"yellow\">USER NOT FOUND</font></b><br />")
        }
    }

    /**
     * Un clic sur le bouton rafraichir a eu lieu
     */
    override fun onBtRafraichirClick() {
        val listMsg = requestListMsg()
        try {
            ihm.updateMessagesList(listMsg.toString())
        }catch (erreur:Exception) {
            erreur.printStackTrace()
        }
    }

    /**
     * Un clic sur le bouton Connexion a eu lieu
     * @pseudo : le pseudo dans la zone de texte
     * @mdp : le mdp dans la zone de texte
     */
    override fun onBtConnexionClick(pseudo: String, mdp: String) {

        try {
            login(UserBean(login=pseudo, pwd=mdp))
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
        try {
            register(UserBean(login = pseudo, pwd = mdp))
            ihm.updateMessagesList("<b><font color=\"blue\">inscrit</font></b><br />")
            ihm.setConnectedState(true)
        }catch (erreur:Exception) {
            erreur.printStackTrace()
        }
    }


}