package by.shvants.avtomobilka.utils

interface Mapper<F, out T> {
    fun map(from: F): T
}

fun <F, T> Mapper<F, T>.toListMapper(): Mapper<List<F>, List<T>> = MapperToListMapper(this)

private class MapperToListMapper<F, T>(
    val singleMapper: Mapper<F, T>
) : Mapper<List<F>, List<T>> {
    override fun map(from: List<F>): List<T> = from.map(singleMapper::map)
}

