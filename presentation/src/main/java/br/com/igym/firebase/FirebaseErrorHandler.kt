package br.com.igym.firebase

interface FirebaseErrorHandler {

    fun getMessageError(exception: Exception): String
}