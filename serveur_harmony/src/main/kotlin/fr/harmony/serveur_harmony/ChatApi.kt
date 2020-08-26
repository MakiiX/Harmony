package fr.harmony.serveur_harmony

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

//Indique que c'est un webService
@RestController
class ChatApi {

    //Indique que c'est une méthode Get et l'url
    @GetMapping("/test")
    fun test(): String {
        println("Test")

        return "Hello World"
    }

}