package model
import model.ResponseApiEnumBean.*

//Class UserBean
data class UserBean(val id : Int?=null, var login: String?=null, var pwd : String?=null, var idSession : Long? = null)

//Class MsgBean
data class MsgBean(val id : Int?=null, val text: String, var date: Long? = null, val user: UserBean)

//Class ResponseApiBean
data class ResponseApiBean(val code: Int=0, val msg: String="")


//Class d'envoi et réception des données
data class DataPackaged(val codeMsg:ResponseApiBean=OK.rab, val user: UserBean?=null, val msg:MsgBean?=null, val listMsg:List<MsgBean>?=null){
    fun getTheOne():Any?{
        return when(codeMsg.code){
            201,202-> user
            203-> msg
            204->listMsg
            else-> codeMsg
        }
    }
    fun getRab()=codeMsg
}

//Class enumération des ResponceApiBean
enum class ResponseApiEnumBean(val rab:ResponseApiBean){
    OK(ResponseApiBean(200, "OK")),
    USER_OK(ResponseApiBean(201,"User OK")),
    ID_SESS_OK(ResponseApiBean(202,"ID Session OK")),
    MSG_OK(ResponseApiBean(203,"Message OK")),
    MSG_LIST_OK(ResponseApiBean(204,"Message List OK")),
    ERR_EXIST_LOG(ResponseApiBean(301,"Login already used")),
    ERR_UNKNOWN_USER(ResponseApiBean(304, "User unknown")),
    ERR_WRONG_PWD(ResponseApiBean(305,"Wrong Password"));
}