package nl.tsakiris.classifier.record;

import nl.tsakiris.classifier.config.CsvImportDriver;
import nl.tsakiris.classifier.config.ImportDriver;
import nl.tsakiris.classifier.stream.InputStreamProvider;
import nl.tsakiris.classifier.stream.InputStreamProviderFactory;

public class RecordProviderFactory {

  private RecordProviderFactory() {}

  public static RecordProvider forDriver(ImportDriver driver) {
    if (driver instanceof CsvImportDriver) {
      return forCsvDriver((CsvImportDriver) driver);
    }
    throw new IllegalAccessError(driver.getClass().getName());
  }

  private static RecordProvider forCsvDriver(CsvImportDriver driver) {
    final InputStreamProvider inputStreamProvider =
        InputStreamProviderFactory.forMedium(driver.getSource());
    return new CsvRecordProvider(driver, inputStreamProvider);
  }

}
