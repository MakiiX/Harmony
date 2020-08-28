package fr.harmony.serveur_harmony

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*


//TODO println chaque start requêtes
//TODO Gérer tous les try catch

/* register & login
{
  "id":null,
  "login":"Toto",
  "pwd":"motdepasse85",
  "idSession":null
}
*/

/*
{
  "id": null,
  "text": "Mon superbe message",
  "date": null,
  "user": {
    "id": null,
    "login": null,
    "pwd": null,
    "idSession": 2951871338625046000
  }
}
*/



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
        try {
            addUser(user)
            return DataPackaged(ResponseApiEnumBean.OK.rab)
        } catch (e: MyException) {
            return DataPackaged(e.responseApiBean)
        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOW_ERR.rab)
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

        //Retour positif, modifie l' idSession de l'user de la listeUser, et retourne celui-ci au client (avec l' idSession)
        else {
            val userOnList = listUser.find { it.login == user.login && it.pwd == user.pwd }
            userOnList!!.idSession = randomIdSession()
            return DataPackaged(ResponseApiEnumBean.USER_OK.rab, userOnList)
        }
    }

    //Réceptionne un message d'un utilisateur, lui donne une date et l'ajoute à la liste des messages (BDD)
    @PostMapping("/sendMsg")
    fun receiveMsg(@RequestBody msg: MsgBean): DataPackaged {

        println("/sendMsg \n msg = $msg")

        try {
            //Rechercher l'user grace à l' idSession
            val idSessionMsg = msg.user.idSession
            val userOnList = getUserBySession(idSessionMsg)

            if (userOnList == null) {
                return DataPackaged(ResponseApiEnumBean.ERR_UNKNOW_USER.rab)
            } else {
                //Ajouter la date puis le msg à la liste
                msg.date = Date().time//Ajoute la date en format Long
                listMsgs.add(msg)//Ajouter le msg dans la liste

                return DataPackaged(ResponseApiEnumBean.OK.rab)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOW_ERR.rab)
        }

    }

    //Retourne un DataPackaged avec la liste des messages
    @GetMapping("/requestListMsg")
    fun sendListMsg(): DataPackaged {
        return DataPackaged(ResponseApiEnumBean.MSG_LIST_OK.rab, listMsg = listMsgs)
    }
}