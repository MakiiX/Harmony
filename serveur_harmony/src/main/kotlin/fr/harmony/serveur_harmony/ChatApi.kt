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

    //Connecte l'utilisateur et lui reconnais un idSession
    @PostMapping("/login")
    fun login(@RequestBody user: UserBean): DataPackaged {
        println("Tentative de connexion..")

        val check = checkPwd(user)

        //Vérifie si le nom user est bien déjà enregistrer
        if (check == null) return DataPackaged(ResponseApiEnumBean.ERR_UNKNOW_USER.rab)

        //Vérifie le mot de passe
        else if (check == false) return DataPackaged(ResponseApiEnumBean.ERR_WRONG_PWD.rab)

        //Retour positif, retourne l'User avec son IdSession
        else {
            val userOnList = listUser.find { it.login == user.login && it.pwd == user.pwd }
            userOnList!!.idSession = randomIdSession()
            return DataPackaged(ResponseApiEnumBean.OK.rab, userOnList)
        }
    }

    fun receiveMsg(msg: MsgBean) {
        //TODO
    }

    fun sendListMsg() {
        //TODO
    }


}