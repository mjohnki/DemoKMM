package de.johnki.app.demokmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}