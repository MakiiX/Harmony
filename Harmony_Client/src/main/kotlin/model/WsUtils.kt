package model

import com.google.gson.Gson


fun register(user:UserBean){
    if(!user.login.isNullOrBlank() || !user.pwd.isNullOrBlank()) {
        var json = Gson().toJson(user, UserBean::class.java)
        json = sendPostOkHttpRequest(URL_SERV_REGISTER, json)
        val receivedPure = (Gson().fromJson(json, DataPackaged::class.java)).getRab()
        if (receivedPure.code !in 200..299) {
            throw Exception((receivedPure.msg))
        }
    }else{throw Exception("Veuillez remplir les champs")}
}

fun login (user:UserBean) {
    if(!user.login.isNullOrBlank() || !user.pwd.isNullOrBlank()) {
        var json = Gson().toJson(user, UserBean::class.java)
        json = sendPostOkHttpRequest(URL_SERV_LOGIN, json)
        val receivedPure = Gson().fromJson(json, DataPackaged::class.java)
        val receivedTreated = receivedPure.getTheOne()
        if (receivedTreated is UserBean) {
            user.idSession = receivedTreated.idSession
        } else {
            throw Exception((receivedPure.getRab()).msg)
        }
    }else{throw Exception("Veuillez remplir les champs")}
}
fun sendMsg(msg:MsgBean, currentUser:UserBean?) {
    var received=(Gson().fromJson(sendPostOkHttpRequest(URL_SERV_SEND_MSG, Gson().toJson(msg, MsgBean::class.java)), DataPackaged::class.java)).getRab()
    if((received as ResponseApiBean).code!=200) throw Exception(received.msg) else println("msg envoyé")

}

fun requestListMsg(): List<MsgBean> {
    var json = sendGetOkHttpRequest(URL_SERV_REQUEST_LIST_MSG)
    val receivedPure= Gson().fromJson(json,DataPackaged::class.java)
    val receivedTreated= receivedPure.getTheOne()
    if(receivedTreated is List<*>) return receivedTreated as List<MsgBean> else throw Exception(receivedPure.getRab().msg)
}
