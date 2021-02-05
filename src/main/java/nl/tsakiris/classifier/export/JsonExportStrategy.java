package nl.tsakiris.classifier.export;

import java.io.OutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.stream.OutputStreamProvider;
import nl.tsakiris.classifier.category.Category;
import nl.tsakiris.classifier.category.CategoryTreeProvider;

@AllArgsConstructor
public class JsonExportStrategy implements ExportStrategy {

  private final OutputStreamProvider outputStreamProvider;

  @Override
  @SneakyThrows
  public void execute(CategoryTreeProvider categoryTreeProvider) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    try (OutputStream outputStream = outputStreamProvider.get()) {
      Category categoryTree = categoryTreeProvider.get();
      objectMapper.writeValue(outputStream, categoryTree);
    }
  }

}
