package fr.harmony.serveur_harmony

import fr.harmony.serveur_harmony.ResponseApiEnumBean.OK

//Class UserBean
data class UserBean(val id: Int? = null, var login: String?, var pwd: String?, var idSession: Long? = null)

//Class MsgBean
data class MsgBean(val id: Int? = null, val text: String, var date: Long? = null, val user: UserBean)

//Class ResponseApiBean
data class ResponseApiBean(val code: Int, val msg: String)


//Class d'envoi et réception des données
data class DataPackaged(val codeMsg: ResponseApiBean = OK.rab, val user: UserBean? = null, val msg: MsgBean? = null, val listMsg: List<MsgBean>? = null)

//Class enumeration des ResponseApiBean
public enum class ResponseApiEnumBean(val rab: ResponseApiBean) {
    OK(ResponseApiBean(200, "OK")),
    USER_OK(ResponseApiBean(201, "User OK")),
    ID_SESS_OK(ResponseApiBean(202, "ID Session OK")),
    MSG_LIST_OK(ResponseApiBean(204, "Message List OK")),
    ERR_EXIST_LOG(ResponseApiBean(301, "Login already used")),
    ERR_UNKNOWN_USER(ResponseApiBean(304, "User unknown")),
    ERR_WRONG_PWD(ResponseApiBean(305, "Wrong Password")),
    ERR_UNKNOWN_ERR(ResponseApiBean(306, "Erreur inconnue"))
}

class MyException(val responseApiBean: ResponseApiBean) : Exception(responseApiBean.msg)