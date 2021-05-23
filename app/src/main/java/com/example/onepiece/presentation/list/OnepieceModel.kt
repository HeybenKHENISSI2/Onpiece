package com.example.onepiece.presentation.list

sealed class OnepieceModel

data class OnepieceSuccess(val onepieceList: List<Onepiece>) : OnepieceModel()
object OnepieceLoader : OnepieceModel()
object OnepieceError : OnepieceModel()