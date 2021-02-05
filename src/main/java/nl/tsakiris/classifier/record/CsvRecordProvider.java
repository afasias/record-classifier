package nl.tsakiris.classifier.record;

import static java.util.stream.Collectors.toList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.config.CsvImportDriver;
import nl.tsakiris.classifier.stream.InputStreamProvider;

@RequiredArgsConstructor
public class CsvRecordProvider implements RecordProvider {

  private final CsvImportDriver driver;

  private final InputStreamProvider inputStreamProvider;

  @Override
  @SneakyThrows
  public List<Record> get() {

    CSVParser csvParser = new CSVParserBuilder()
        .withQuoteChar(driver.getQuoteChar())
        .withSeparator(driver.getSeparator())
        .build();

    try (InputStream inputStream = inputStreamProvider.get();
        InputStreamReader reader = new InputStreamReader(inputStream);
        CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build()) {

      String[] headers = driver.isHasHeader() ? csvReader.readNext() : new String[0];
      CsvRecordMapper csvRecordMapper = new CsvRecordMapper(headers, driver.getDataMapping());

      return csvReader.readAll()
          .stream()
          .map(csvRecordMapper::map)
          .collect(toList());
    }
  }

}
