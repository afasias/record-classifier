package nl.tsakiris.classifier;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.config.Aggregation;
import nl.tsakiris.classifier.config.Configuration;
import nl.tsakiris.classifier.config.ConfigurationProvider;
import nl.tsakiris.classifier.config.ConfigurationProviderFactory;
import nl.tsakiris.classifier.config.ImportDriver;
import nl.tsakiris.classifier.export.ExportStrategy;
import nl.tsakiris.classifier.export.ExportStrategyFactory;
import nl.tsakiris.classifier.record.RecordProvider;
import nl.tsakiris.classifier.record.RecordProviderFactory;
import nl.tsakiris.classifier.category.ClassifyingCategoryTreeProvider;
import nl.tsakiris.classifier.category.Category;
import nl.tsakiris.classifier.category.CategoryTreeProvider;

@AllArgsConstructor
public class Classifier {

  private final Configuration configuration;

  @SneakyThrows
  public void run() {

    ImportDriver importDriver = configuration.getImportDriver();
    RecordProvider recordProvider = RecordProviderFactory.forDriver(importDriver);

    Category categoryTree = configuration.getRootCategory();
    Map<String, Aggregation<?>> aggregations = configuration.getAggregations();
    CategoryTreeProvider categoryTreeProvider =
        new ClassifyingCategoryTreeProvider(recordProvider, categoryTree, aggregations);

    ExportStrategy exportStrategy = ExportStrategyFactory.forConfiguration(configuration);
    exportStrategy.execute(categoryTreeProvider);

  }

  public static void main(String[] args) {
    String configFile = args[0];
    ConfigurationProvider configProvider = ConfigurationProviderFactory.fromYamlFile(configFile);
    new Classifier(configProvider.get()).run();
  }

}
