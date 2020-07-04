package com.konet.sample.library

object Constants {

    object Apis {

        /**
         * Fake API without authorization
         */
        object Reqres {
            const val url = "https://reqres.in/api/"
            const val users = "users"
            const val login = "login"
        }
    }

    object Headers {

        const val authorization = "Authorization"
    }

    const val bearer = "Bearer"
}