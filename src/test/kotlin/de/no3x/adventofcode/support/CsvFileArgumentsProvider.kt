package de.no3x.adventofcode.support

import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider
import org.junit.jupiter.params.provider.Arguments
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

class CsvFileArgumentsProvider : AnnotationBasedArgumentsProvider<SupportCsvFileSource>() {

    override fun provideArguments(context: org.junit.jupiter.api.extension.ExtensionContext, annotation: SupportCsvFileSource?): Stream<out Arguments> {
        val method = context.requiredTestMethod
        val fileName = annotation?.fileName ?: throw IllegalArgumentException("File name not provided")
        val delimiter = annotation.delimiter
        val path = Paths.get(context.requiredTestClass.getResource(fileName).toURI())
            ?: throw IllegalArgumentException("File not found in resources: $fileName")
        return readFromCsv(
            path, delimiter
        )
    }

    private fun readFromCsv(path: Path, delimiter: String): Stream<Arguments> {
        val lines = Files.readAllLines(path)

        val column1 = mutableListOf<Int>()
        val column2 = mutableListOf<Int>()

        lines.forEach { line ->
            val (col1, col2) = line.split(delimiter).map { it.trim().toInt() }
            column1.add(col1)
            column2.add(col2)
        }

        return Stream.of(Arguments.of(column1, column2))
    }
}