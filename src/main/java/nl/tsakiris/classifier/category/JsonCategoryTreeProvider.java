package nl.tsakiris.classifier.category;

import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class JsonCategoryTreeProvider implements CategoryTreeProvider {

  private final InputStream inputStream;

  @Override
  @SneakyThrows
  public Category get() {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(inputStream, Category.class);
  }

}
