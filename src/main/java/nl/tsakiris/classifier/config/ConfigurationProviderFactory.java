package nl.tsakiris.classifier.config;

import lombok.SneakyThrows;
import nl.tsakiris.classifier.stream.FileInputStreamProvider;
import nl.tsakiris.classifier.stream.InputStreamProvider;

public class ConfigurationProviderFactory {

  private ConfigurationProviderFactory() {}

  @SneakyThrows
  public static ConfigurationProvider fromYamlFile(String path) {
    InputStreamProvider inputStreamProvider = new FileInputStreamProvider(path);
    return new YamlConfigurationProvider(inputStreamProvider);
  }

}
