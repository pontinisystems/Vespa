package pontinisystems.vespa.coreapp

interface Mapper<in E, T> {
    fun mapFrom(from :E):T
}

interface MapperTwo<in E, in S, T> {
    fun mapFromTwo(from :E, from2 :S):T
}