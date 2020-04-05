package com.example.applover.data.network.util.parser

interface ErrorParser {
    fun parse(throwable:Throwable) : String
}