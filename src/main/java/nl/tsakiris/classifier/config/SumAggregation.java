package nl.tsakiris.classifier.config;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.tsakiris.classifier.category.Category;
import nl.tsakiris.classifier.record.Record;

@Data
@AllArgsConstructor
@JsonDeserialize(builder = SumAggregation.Builder.class)
@Builder(builderClassName = "Builder")
public class SumAggregation implements Aggregation<BigDecimal> {

  private final String field;

  @Override
  public BigDecimal calculateFor(Category category) {
    BigDecimal sum = BigDecimal.ZERO;
    if (category.getCategories() != null) {
      for (Category childCategory : category.getCategories()) {
        sum = sum.add(calculateFor(childCategory));
      }
    }
    for (Record record : category.getRecords()) {
      String value = record.get(field).replace(',', '.');
      try {
        sum = sum.add(new BigDecimal(value));
      } catch (NumberFormatException e) {
        // ignore non-numerical input
      }
    }
    return sum;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
  }

}
