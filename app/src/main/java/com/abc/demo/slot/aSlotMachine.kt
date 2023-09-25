package com.abc.demo.slot

class aSlotMachine(private val numberOfWheels: Int) {
    @JvmField
    val slots: ArrayList<aWheel> = ArrayList()
    private var playerFunds = 0

    init {
        generateWheels()
    }

    private fun generateWheels() {
        for (i in 0 until numberOfWheels) {
            val aWheel = aWheel()
            slots.add(aWheel)
        }
    }

    fun checkPlayerFunds(): Int {
        return playerFunds
    }

    fun setPlayerFunds(amount: Int) {
        playerFunds = amount
    }

    fun addPlayerFunds(amount: Int) {
        playerFunds += amount
    }

    fun countSlots(): Int {
        return slots.size
    }

    fun getWinValue(line: ArrayList<AaSymbols>): Int {
        return line[0].value
    }

    fun checkWin(line: ArrayList<AaSymbols>): Boolean {
        var counter = 0
        for (symbol in line) {
            if (symbol == line[0]) {
                counter++
            }
        }
        return counter == line.size
    }

    fun spin(): ArrayList<AaSymbols> {
        addPlayerFunds(-1)
        val line = ArrayList<AaSymbols>()
        for (wheel in slots) {
            line.add(wheel.spin())
        }
        return line
    }

    val currentSymbols: ArrayList<AaSymbols>
        get() {
            val currentSymbols = ArrayList<AaSymbols>()
            for (wheel in slots) {
                val symbol = wheel.currentSymbol
                currentSymbols.add(symbol)
            }
            return currentSymbols
        }
    val topLineSymbols: ArrayList<AaSymbols>
        get() {
            val topSymbols = ArrayList<AaSymbols>()
            for (wheel in slots) {
                val top = wheel.topSymbol
                topSymbols.add(top)
            }
            return topSymbols
        }
    val bottomLineSymbols: ArrayList<AaSymbols>
        get() {
            val bottomSymbols = ArrayList<AaSymbols>()
            for (wheel in slots) {
                val bottom = wheel.bottomSymbol
                bottomSymbols.add(bottom)
            }
            return bottomSymbols
        }

    private fun getSymbolImage(symbol: AaSymbols): String {
        return symbol.imageName
    }

    fun getLineImages(line: ArrayList<AaSymbols>): ArrayList<String> {
        val images = ArrayList<String>()
        for (symbol in line) {
            val image = getSymbolImage(symbol)
            images.add(image)
        }
        return images
    }

    private fun getSymbolImage1(symbol: AaSymbols): Int {
        return symbol.value
    }

    fun getLineImages1(line: ArrayList<AaSymbols>): ArrayList<Int> {
        val images = ArrayList<Int>()
        for (symbol in line) {
            val image = getSymbolImage1(symbol)
            images.add(image)
        }
        return images
    }
}