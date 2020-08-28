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
    var userConnected:UserBean?=null


    /* -------------------------------- */
    // Evenement de l'interface graphique
    /* -------------------------------- */
    /**
     * Un clic sur le bouton envoyer à eu lieu
     * @message : Le message de la zone de texte
     */
    override fun onBtEnvoyerClick(message: String) {
        try {
            sendMsg(MsgBean(text = message, user = UserBean(idSession = userConnected!!.idSession)),userConnected)
            ihm.updateMessagesList(requestListMsg().toView())
        }catch (erreur:Exception) {
            erreur.printStackTrace()
            ihm.updateError("${erreur.message}")
        }
    }

    /**
     * Un clic sur le bouton rafraichir a eu lieu
     */
    override fun onBtRafraichirClick() {
        try {
            ihm.updateMessagesList(requestListMsg().toView())
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
        val usr=UserBean(login = pseudo, pwd = mdp)
        if(!pseudo.isNullOrBlank()&&!mdp.isNullOrBlank()){
            try {
                login(usr)
                ihm.updateMessagesList("<b><font color=\"green\">Connecté</font></b><br />")
                ihm.setConnectedState(true)
                userConnected=usr
            } catch (erreur: Exception) {
                erreur.printStackTrace()
                ihm.updateError("${erreur.message}")
            }
        }else{
            ihm.updateError("Veuillez remplir les champs")
        }


    }

    /**
     * Un clic sur le bouton Inscription a eu lieu
     * @pseudo : le pseudo dans la zone de texte
     * @mdp : le mdp dans la zone de texte
     */
    override fun onBtInscriptionClick(pseudo: String, mdp: String) {
        val usr=UserBean(login = pseudo, pwd = mdp)
        try {
            register(usr)
            login(usr)
            ihm.updateMessagesList("<b><font color=\"blue\">inscrit</font></b><br />")
            userConnected=usr
            ihm.setConnectedState(true)
        }catch (erreur:Exception) {
            erreur.printStackTrace()
            ihm.updateError("${erreur.message}")
        }
    }


}