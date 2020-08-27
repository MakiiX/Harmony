package fr.harmony.serveur_harmony

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

//Indique que c'est un webService
@RestController
class ChatApi {

    //Méthode test..
    @GetMapping("/test")
    fun test(): String {
        println("Test")
        return "Hello World"
    }

    //Enregistre un nouvel utilisateur et retourne un ResponseApiBean en réponse
    @PostMapping("/register")
    fun register(@RequestBody user: UserBean) : ResponseApiBean {

        println("Tentative d'enregistrement...")

        if (checkLoginDoublon(user)) {
            return ResponseApiBean(504, "Erreur, login déjà existant, choisissez un autre login")
        }
        //Ajoute l'user dans la base de données
        else {
            addUser(user)
            return ResponseApiBean(200, "Vous avez bien été enregistrer")
        }

    }

    fun login(user: UserBean) {
        //TODO
    }
    fun receiveMsg(msg: MsgBean) {
        //TODO
    }
    fun sendListMsg() {
        //TODO
    }



}