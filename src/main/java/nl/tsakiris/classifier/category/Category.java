package nl.tsakiris.classifier.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.ConditionDeserializer;
import nl.tsakiris.classifier.record.Record;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = Category.Builder.class)
@Builder(builderClassName = "Builder")
public class Category {

  private final String name;

  @JsonDeserialize(using = ConditionDeserializer.class)
  private final Condition condition;

  private final List<Category> categories;

  private final List<Record> records = new ArrayList<>();

  private final Map<String, Object> aggregations = new HashMap<>();


  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
