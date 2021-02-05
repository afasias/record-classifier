package nl.tsakiris.classifier.export;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.config.Aggregation;
import nl.tsakiris.classifier.config.FieldType;
import nl.tsakiris.classifier.config.HtmlExportDriver;
import nl.tsakiris.classifier.stream.OutputStreamProvider;
import nl.tsakiris.classifier.category.CategoryTreeProvider;

@AllArgsConstructor
public class HtmlExportStrategy implements ExportStrategy {

  private final OutputStreamProvider outputStreamProvider;

  private final HtmlExportDriver driver;

  private final Map<String, FieldType> fields;

  private final Map<String, Aggregation<?>> aggregations;

  @Override
  @SneakyThrows
  public void execute(CategoryTreeProvider categoryTreeProvider) {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    Configuration cfg = new Configuration(new Version(2, 3, 20));
    cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "html-templates");

    Map<String, Object> input = new HashMap<>();
    input.put("aggregations", aggregations.keySet());
    input.put("fields", fields.keySet());
    input.put("categoryTreeJson", objectMapper.writeValueAsString(categoryTreeProvider.get()));
    input.put("fieldsJson", objectMapper.writeValueAsString(fields));
    input.put("aggregationsJson", objectMapper.writeValueAsString(aggregations));
    input.put("defaultAggregationJson",
        objectMapper.writeValueAsString(driver.getDefaultAggregation()));

    Template template = cfg.getTemplate("category-explorer-with-piechart.ftl");
    try (Writer writer = new OutputStreamWriter(outputStreamProvider.get())) {
      template.process(input, writer);
    }
  }

}
