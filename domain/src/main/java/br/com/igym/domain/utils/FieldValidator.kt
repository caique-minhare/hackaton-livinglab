package br.com.igym.domain.utils

import br.com.igym.domain.entities.FieldErrorType
import io.reactivex.Single

class FieldValidator {
    companion object {
        private const val EMAIL_REGEX = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        private const val MIN_PASSWORD_LENGTH = 4

        fun validateEmail(email: String): Single<FieldErrorType?> {
            return when {
                !email.matches(Regex(EMAIL_REGEX)) -> Single.just(FieldErrorType.InvalidEmail)
                else -> Single.just(null)
            }
        }

        fun validatePassword(password: String): Single<FieldErrorType?> {
            return when {
                password.isBlank() -> Single.fromCallable { FieldErrorType.InvalidPassword }
                password.length < MIN_PASSWORD_LENGTH -> Single.fromCallable { FieldErrorType.PasswordTooShort }
                else -> Single.fromCallable { null }
            }
        }
    }
}