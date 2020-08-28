package fr.harmony.serveur_harmony

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*


//Indique que c'est un webService
@RestController
class ChatApi {

    //Méthode test..
    @GetMapping("/test")
    fun test(): String {
        println("/test")
        return "Hello World"
    }

    //Enregistre un nouvel utilisateur et retourne un ResponseApiBean en réponse
    @PostMapping("/register")
    fun register(@RequestBody user: UserBean): DataPackaged {
        println("/register \nuser: $user")
        try {
            addUser(user)
            return DataPackaged(ResponseApiEnumBean.OK.rab)
        } catch (e: MyException) {
            return DataPackaged(e.responseApiBean)
        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_ERR.rab)
        }
    }

    //Connecte l'utilisateur et lui reconnais un idSession
    @PostMapping("/login")
    fun login(@RequestBody user: UserBean): DataPackaged {
        println("/login \nuser: $user")

        try {
            val check = checkPwd(user)

            //Vérifie si le nom user est bien déjà enregistrer
            if (check == null) return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_USER.rab)

            //Vérifie le mot de passe
            else if (check == false) return DataPackaged(ResponseApiEnumBean.ERR_WRONG_PWD.rab)

            //Retour positif, modifie l' idSession de l'user de la listeUser, et retourne celui-ci au client (avec l' idSession)
            else {
                val userOnList = listUser.find { it.login == user.login && it.pwd == user.pwd }
                userOnList!!.idSession = randomIdSession()
                return DataPackaged(ResponseApiEnumBean.USER_OK.rab, userOnList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_ERR.rab)
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
                return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_USER.rab)
            } else {
                //Ajouter le login, la date puis le msg à la liste
                msg.user.login = userOnList.login
                msg.date = Date().time//Ajoute la date en format Long
                listMsgs.add(msg)//Ajouter le msg dans la liste

                return DataPackaged(ResponseApiEnumBean.OK.rab)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_ERR.rab)
        }
    }

    //Retourne un DataPackaged avec la liste des messages
    @GetMapping("/requestListMsg")
    fun sendListMsg(): DataPackaged {
        println("/requestListMsg")
        try {
            return DataPackaged(ResponseApiEnumBean.MSG_LIST_OK.rab, listMsg = listMsgs)

        } catch (e: Exception) {
            e.printStackTrace()
            return DataPackaged(ResponseApiEnumBean.ERR_UNKNOWN_ERR.rab)
        }
    }
}