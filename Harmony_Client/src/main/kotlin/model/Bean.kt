package model

//Class UserBean
data class UserBean(val id : Int?, var login: String?, private  var pwd : String?, var idSession : Long? = null)

//Class MsgBean
data class MsgBean(val id : Int?, val text: String, var date: Long? = null, val user: UserBean)

//Class ResponseApiBean
data class ResponseApiBean(val code: Int, val msg: String)