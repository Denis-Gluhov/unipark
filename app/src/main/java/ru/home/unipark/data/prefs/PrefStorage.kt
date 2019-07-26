package ru.home.unipark.data.prefs

import android.content.Context.MODE_PRIVATE
import android.text.TextUtils
import io.reactivex.Completable
import io.reactivex.Single
import ru.home.unipark.App
import ru.home.unipark.data.exceptions.EmptyStorageException

class PrefStorage {

    private val APP_SETTINGS = "user"
    private val KEY_PHONE = "phone_number"
    private val KEY_TOKEN = "access_token"
    private val KEY_NAME = "firstname"
    private val KEY_CITY_NAME = "city_name"
    private val KEY_CITY_ID = "city_id"

    private val prefs = App.getInstance().getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    fun write(params: Params): Completable {
        return Completable.fromAction {
            val editor = prefs.edit()
            editor.putString(KEY_PHONE, params.phone)
            editor.putString(KEY_TOKEN, params.token)
            editor.putString(KEY_NAME, params.firstName)
            editor.putString(KEY_CITY_NAME, params.cityName)
            editor.putInt(KEY_CITY_ID, params.cityId)
            editor.apply()
        }
    }

    fun read(): Single<Params> {
        return Single.create {
            val phone = prefs.getString(KEY_PHONE, "")
            val token = prefs.getString(KEY_TOKEN, "")
            val firstName = prefs.getString(KEY_NAME, "")
            val cityName = prefs.getString(KEY_CITY_NAME, "")
            val cityId = prefs.getInt(KEY_CITY_ID, 0)

            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(token) && !TextUtils.isEmpty(firstName)
                && !TextUtils.isEmpty(cityName) && cityId != 0) {
                it.onSuccess(Params(phone, token, firstName, cityName, cityId))
            } else {
                it.onError(EmptyStorageException())
            }
        }
    }

    fun clear(): Completable {
        return Completable.fromAction {
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
        }
    }

    class Params(
        val phone: String,
        val token: String,
        val firstName: String,
        val cityName: String,
        val cityId: Int
    )
}