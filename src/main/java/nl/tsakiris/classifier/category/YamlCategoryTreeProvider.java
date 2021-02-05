package nl.tsakiris.classifier.category;

import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class YamlCategoryTreeProvider implements CategoryTreeProvider {

  private final InputStream inputStream;

  @Override
  @SneakyThrows
  public Category get() {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    return objectMapper.readValue(inputStream, Category.class);
  }

}
