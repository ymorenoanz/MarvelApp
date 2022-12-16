package com.yaritzama.marvelapp.core.utils

import com.yaritzama.marvelapp.data.mappers.toDomain
import com.yaritzama.marvelapp.domain.model.BaseResult
import com.yaritzama.marvelapp.domain.model.CharacterModel

data class StateUIP2<T>(
    override var data: T?,
    override var error: String?,
    val isLoading: Boolean = false,
): BaseResult1<T>()


data class Res<T>(
    override var data: T?,
    override var error: String?,
): BaseResult1<T>()

abstract class BaseResult1<T>{
    abstract var data: T?
    abstract var error: String?
}


fun <T> BaseResult1<T>.toStateUP(): StateUIP2<T> =
    StateUIP2(this.data,this.error,false)

fun asd(){
    val a = StateUIP2<String>(data = "", error = "false", isLoading = true)



}


/*
fun getCharacterList2(needsUpdate: Boolean = false) {
        viewModelScope.launch(ioDispatcher + CoroutineExceptionHandler { _, throwable ->
            _stateUI.value = _stateUI.value.copy(error = throwable.toString())
        }) {
            _stateUI2.value = _stateUI2.value.copy(isLoading = true)
            _stateUI2.value = (repo  as MarvelRepositoryImpl) .getCharacterList2(needsUpdate).toStateUP()
        }
    }

suspend fun getCharacterList2(needsUpdate: Boolean): Res<List<CharacterModel>> {
    val  forceUpdate = characterDAO.getCount() == 0
    return if ( needsUpdate || forceUpdate) {
        api.getCharacterList().validateResponse(
            { characterResponse ->
                deleteAllData()
                characterResponse?.let { saveAllData(it)}
                BaseResult.Success(characterDAO.getAll().toDomain())
            }, { throwable ->
                Res(throwable.toString())
            }
        )
    } else  Res(data = characterDAO.getAll().toDomain(), error = null)

}*/