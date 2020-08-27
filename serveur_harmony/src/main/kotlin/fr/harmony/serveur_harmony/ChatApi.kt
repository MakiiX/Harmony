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
    fun register(@RequestBody user: UserBean): DataPackaged {

        println("Tentative d'enregistrement...")
        if (checkLoginDoublon(user)) {
            return DataPackaged(ResponseApiEnumBean.ERR_EXIST_LOG.rab)
        }
        //Ajoute l'user dans la base de données
        else {
            addUser(user)
            return DataPackaged(ResponseApiEnumBean.OK.rab)
        }
    }

    @PostMapping("/login")
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