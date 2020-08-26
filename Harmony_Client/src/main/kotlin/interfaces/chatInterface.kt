package interfaces

public interface ChatEventI {
    fun onBtEnvoyerClick(message: String)
    fun onBtRafraichirClick()
    fun onBtConnexionClick(pseudo: String, mdp: String)
    fun onBtInscriptionClick(pseudo: String, mdp: String)
}

public interface ChatUpdateI {
    /**
     * Modifie le message d'erreur
     */
    fun updateError(errorMessage: String)

    /**
     * Met à jour la liste des messages
     * Peut recevoir de l'HTML
     * Remplace automatiquement les \n par des <br />
     */
    fun updateMessagesList(message: String)

    /**
     * Vide la zone de texte
     */
    fun clearSendMessage()

    /**
     * Permet de passer l'IHM en état connecté ou déconnecté
     */
    fun setConnectedState(connected: Boolean)
}