package br.com.igym.domain.interactors

import br.com.igym.domain.entities.FieldErrorType
import br.com.igym.domain.utils.FieldValidator
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class SigInValidation {

    fun execute(email: String, password: String): Single<List<FieldErrorType>> {
        return FieldValidator.validateEmail(email)
                .zipWith(FieldValidator.validatePassword(password),
                        BiFunction { fieldEmail, fieldPassword -> concatResults(fieldEmail, fieldPassword) })
    }

    private fun concatResults(emailError: FieldErrorType, passwordError: FieldErrorType) = mutableListOf<FieldErrorType>().apply { add(emailError); add(passwordError) }
}

