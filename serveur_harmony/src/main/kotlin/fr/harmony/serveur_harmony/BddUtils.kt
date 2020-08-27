package fr.harmony.serveur_harmony

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


var listMsgs: ArrayList<MsgBean> = ArrayList()
var listUser: ArrayList<UserBean> = ArrayList()

val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss") // SimpleDateFormat

//Recherche le même nom de user, puis compare les mot de passe des deux Users
//Si bon mot de passe -> true
//Si mauvais mot de passe -> false
//Si pas de user reconnue -> null
fun checkPwd(user: UserBean): Boolean? {
    val userCompare = listUser.find { it.login == user.login && it.pwd == user.pwd }

    if (userCompare != null) return userCompare.pwd == user.pwd
    else return null
}

fun addUser(user: UserBean) {
    listUser.add(user)
}

fun addMsg(msg: MsgBean) {
    listMsgs.add(msg)
}

fun getUserBySession(userIdSession: Long): UserBean? {
    for (user in listUser) {
        if (user.idSession == userIdSession) return user
    }
    return null
}

fun getListMsg(): ArrayList<MsgBean> {
    return listMsgs
}

//Retourne true si le login existe déjà
fun checkLoginDoublon(user: UserBean): Boolean {
    for (userOnList in listUser) {
        if (userOnList.login == user.login)
            return true
    }
    return false
}

//Crée un idSession aléatoirement
//TODO vérifier que l'Id Session n'existe pas déjà sur un autre utilisateur
fun randomIdSession(): Long {
    return Random.nextLong()
}

fun getCurrentDate(): String {
    return sdf.format(Date())
}