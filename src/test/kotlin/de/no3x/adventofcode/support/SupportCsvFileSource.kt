package de.no3x.adventofcode.support

import org.junit.jupiter.params.provider.ArgumentsSource

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ArgumentsSource(CsvFileArgumentsProvider::class)
annotation class SupportCsvFileSource(val fileName: String, val delimiter: String = ",")