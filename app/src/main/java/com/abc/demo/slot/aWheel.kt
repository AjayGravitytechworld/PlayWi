package com.abc.demo.slot

import java.util.Collections
import java.util.Random

class aWheel {
    private val allSymbols: ArrayList<AaSymbols> = ArrayList()
    var holdAvailable = false
    var nudgeAvailable = false
    var playerHasHeld = false
    var currentSymbol: AaSymbols

    init {
        generateSymbols()
        currentSymbol = randomSymbol
    }

    private fun generateSymbols() {
        Collections.addAll(allSymbols, *AaSymbols.values())
    }

    private fun getSymbolIndex(symbol: AaSymbols): Int {
        return allSymbols.indexOf(symbol)
    }

    private fun getSymbolImage(symbol: AaSymbols): String {
        return symbol.imageName
    }

    fun getSymbolImageAtIndex(index: Int): String {
        val symbol = getSymbolAtIndex(index)
        return getSymbolImage(symbol)
    }

    private fun getSymbolAtIndex(index: Int): AaSymbols {
        return allSymbols[index]
    }

    private val randomSymbol: AaSymbols
        get() {
            val randomIndex = randomInt(countSymbols())
            return getSymbolAtIndex(randomIndex)
        }

    private fun getPreviousSymbol(currentSymbol: AaSymbols): AaSymbols {
        val currentIndex = getSymbolIndex(currentSymbol)
        return if (currentIndex == 0) {
            val topIndex = countSymbols() - 1
            getSymbolAtIndex(topIndex)
        } else {
            val topIndex = currentIndex - 1
            getSymbolAtIndex(topIndex)
        }
    }

    val topSymbol: AaSymbols
        get() {
            val currentSymbol = currentSymbol
            return getPreviousSymbol(currentSymbol)
        }
    val bottomSymbol: AaSymbols
        get() {
            val currentSymbol = currentSymbol
            val currentIndex = getSymbolIndex(currentSymbol)
            return if (currentIndex == countSymbols() - 1) {
                val bottomIndex = 0
                getSymbolAtIndex(bottomIndex)
            } else {
                val bottomIndex = currentIndex + 1
                getSymbolAtIndex(bottomIndex)
            }
        }

    private fun countSymbols(): Int {
        return allSymbols.size
    }

    private fun randomInt(max: Int): Int {
        val rand = Random()
        return rand.nextInt(max)
    }

    fun spin(): AaSymbols {
        randomAssignNudgeAvailable()
        randomAssignHoldAvailable()
        return if (playerHasHeld) {
            currentSymbol
        } else {
            val newSymbol = randomSymbol
            currentSymbol = newSymbol
            newSymbol
        }
    }

    fun nudge(): AaSymbols {
        currentSymbol = if (getSymbolIndex(currentSymbol) == 0) {
            getSymbolAtIndex(countSymbols() - 1)
        } else {
            val newIndex = getSymbolIndex(currentSymbol) - 1
            getSymbolAtIndex(newIndex)
        }
        return currentSymbol
    }

    private fun randomAssignNudgeAvailable() {
        val randomNumber = randomInt(30)
        nudgeAvailable = randomNumber == 10
    }

    private fun randomAssignHoldAvailable() {
        val randomNumber = randomInt(30)
        holdAvailable = randomNumber == 10
    }
}