package br.com.igym.data.mapper

abstract class Mapper<in I, out O> {
    abstract fun transform(i: I): O

    fun mapCollection(listOfI: List<I>?): List<O> {
        val uList = ArrayList<O>()
        if (listOfI == null) return uList
        listOfI.forEach {
            uList.add(transform(it))
        }
        return uList
    }
}