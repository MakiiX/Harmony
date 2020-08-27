package model

import com.google.gson.Gson
import model.ResponseApiEnumBean.*


fun register(user:UserBean){
    var json = Gson().toJson(user, UserBean::class.java)
    json = sendPostOkHttpRequest(URL_SERV_REGISTER, json)
    val receivedPure= (Gson().fromJson(json,DataPackaged::class.java)).getRab()
    if(receivedPure.code !in 200..299){
        throw Exception((receivedPure.msg))
    }

}

fun login (user:UserBean) {
    var json = Gson().toJson(user, UserBean::class.java)
    json = sendPostOkHttpRequest(URL_SERV_LOGIN, json)
    val receivedPure= Gson().fromJson(json,DataPackaged::class.java)
    val receivedTreated= receivedPure.getTheOne()
    if(receivedTreated is UserBean){
        user.idSession=receivedTreated.idSession
        CURRENT_USER=user
    }else{
        throw Exception((receivedPure.getRab()).msg)
    }
}

fun sendMsg(msg:MsgBean) {
    var received=sendPostOkHttpRequest(URL_SERV_SEND_MSG, Gson().toJson(msg, MsgBean::class.java))
    if((received as ResponseApiBean).code!=200) throw Exception(received.msg)

}

fun requestListMsg(): List<MsgBean> {
    var json = sendGetOkHttpRequest(URL_SERV_REQUEST_LIST_MSG)
    val receivedPure= Gson().fromJson(json,DataPackaged::class.java)
    val receivedTreated= receivedPure.getTheOne()
    if(receivedTreated is List<*>) return receivedTreated as List<MsgBean> else throw Exception(receivedPure.getRab().msg)
}
