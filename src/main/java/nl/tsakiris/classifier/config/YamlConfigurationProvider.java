package nl.tsakiris.classifier.config;

import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.stream.InputStreamProvider;

@Data
public class YamlConfigurationProvider implements ConfigurationProvider {

  private final InputStreamProvider inputStreamProvider;

  @Override
  @SneakyThrows
  public Configuration get() {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    objectMapper.registerModule(new JavaTimeModule());
    InputStream inputStream = inputStreamProvider.get();
    return objectMapper.readValue(inputStream, Configuration.class);
  }

}
