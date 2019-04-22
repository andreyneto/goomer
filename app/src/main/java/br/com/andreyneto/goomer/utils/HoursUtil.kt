package br.com.andreyneto.goomer.utils

import br.com.andreyneto.goomer.model.Menu
import br.com.andreyneto.goomer.model.Restaurantes
import br.com.andreyneto.goomer.model.Sale
import java.util.*
import java.text.SimpleDateFormat


object HoursUtil {
    fun currentSale(menu: Menu): Sale? {
        menu.sales?.let {
            val today = Calendar.getInstance()
            val formatter = SimpleDateFormat("HH:mm")
            val now = java.sql.Time(
                formatter.parse(
                    "${today.get(Calendar.HOUR_OF_DAY)}:${today.get(Calendar.MINUTE)}"
                ).time
            )
            for (sale in it) {
                for (hour in sale.hours) {
                    if (hour.days.contains(today.get(Calendar.DAY_OF_WEEK))) {
                        val from = java.sql.Time(formatter.parse(hour.from).time)
                        val to = java.sql.Time(formatter.parse(hour.to).time)
                            if (to.after(from) && now.after(from) && now.before(to) || !to.after(from) && (now.after(from) || now.before(to))) {
                                return sale
                            }
                    }
                }
            }
        }
        return null
    }

    fun openStatus(restaurantes: Restaurantes): String {
        restaurantes.hours?.let {
            val today = Calendar.getInstance()
            val formatter = SimpleDateFormat("HH:mm")
            val now = java.sql.Time(
                formatter.parse(
                    "${today.get(Calendar.HOUR_OF_DAY)}:${today.get(Calendar.MINUTE)}"
                ).time
            )
            for (hour in restaurantes.hours) {
                if (hour.days.contains(today.get(Calendar.DAY_OF_WEEK))) {
                    val from = java.sql.Time(formatter.parse(hour.from).time)
                    val to = java.sql.Time(formatter.parse(hour.to).time)
                    if (to.after(from) && now.after(from) && now.before(to) || !to.after(from) && (now.after(from) || now.before(to))) {
                        return "Aberto"
                    }
                }
            }
            return "Fechado"
        }
        return "Horário de funcionamento não informado"
    }
}