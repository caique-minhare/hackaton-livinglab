package br.com.igym.domain.entities

sealed class FieldErrorType : Exception() {
    object InvalidEmail : FieldErrorType()
    object InvalidPassword : FieldErrorType()
    object PasswordTooShort : FieldErrorType()
    object EmailAlreadyExists : FieldErrorType()
}