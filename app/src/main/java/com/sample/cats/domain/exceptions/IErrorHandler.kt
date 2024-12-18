package com.sample.cats.domain.exceptions

import com.sample.cats.domain.exceptions.ErrorModel

interface IErrorHandler {
    fun handleException(throwable: Throwable?): ErrorModel
}