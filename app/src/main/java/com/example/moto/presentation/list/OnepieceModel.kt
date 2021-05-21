package com.example.moto.presentation.list

sealed class OnepieceModel

data class OnepieceSuccess(val onepieceList: List<Onepiece>) : OnepieceModel()
object OnepieceLoader : OnepieceModel()
object OnepieceLoaderLogo : OnepieceModel()
object OnepieceError : OnepieceModel()