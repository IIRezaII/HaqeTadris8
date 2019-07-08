package com.example.haqetadris8

class Calculate {

    fun associatesDegreeUnder20(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = 20 - stuNumber
        return unit.toDouble() - (unit.toDouble() * diffrence.toDouble() * 0.02)
    }


    fun associatesDegreeMoreThan40(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = stuNumber - 40

        return unit.toDouble() + (unit.toDouble() * diffrence.toDouble() * 0.02)
    }


    fun masterDegreeMoreThan25(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = stuNumber - 25
        return (unit.toDouble() + (unit.toDouble() * diffrence.toDouble() * 0.05)) * 1.5
    }


    fun masterDegreeUnder7(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = 7 - stuNumber
        return (unit.toDouble() - (unit.toDouble() * diffrence.toDouble() * 0.05)) * 1.5
    }

    fun generalUnder25(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = 25 - stuNumber
        return unit.toDouble() - (unit.toDouble() * diffrence.toDouble() * 0.02)
    }

    fun generalMoreThan50(unit: Int, stuNumber: Int): Double {
        val diffrence: Int = stuNumber - 50
        return unit.toDouble() + (unit.toDouble() * diffrence.toDouble() * 0.02)
    }
}