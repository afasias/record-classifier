package nl.tsakiris.classifier.export;

import nl.tsakiris.classifier.config.Configuration;
import nl.tsakiris.classifier.config.ExportDriver;
import nl.tsakiris.classifier.config.HtmlExportDriver;
import nl.tsakiris.classifier.config.JsonExportDriver;
import nl.tsakiris.classifier.stream.OutputStreamProvider;
import nl.tsakiris.classifier.stream.OutputStreamProviderFactory;

public class ExportStrategyFactory {

  private ExportStrategyFactory() {}

  public static ExportStrategy forConfiguration(Configuration configuration) {
    ExportDriver driver = configuration.getExportDriver();
    if (driver instanceof JsonExportDriver) {
      OutputStreamProvider outputStreamProvider =
          OutputStreamProviderFactory.forMedium(((JsonExportDriver) driver).getTarget());
      return new JsonExportStrategy(outputStreamProvider);
    }
    if (driver instanceof HtmlExportDriver) {
      OutputStreamProvider outputStreamProvider =
          OutputStreamProviderFactory.forMedium(((HtmlExportDriver) driver).getTarget());
      return new HtmlExportStrategy(outputStreamProvider, (HtmlExportDriver) driver,
          configuration.getFields(), configuration.getAggregations());
    }
    throw new IllegalArgumentException(driver.getClass().getName());
  }

}
