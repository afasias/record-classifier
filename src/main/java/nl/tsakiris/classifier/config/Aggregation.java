package nl.tsakiris.classifier.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nl.tsakiris.classifier.category.Category;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CountAggregation.class, name = "count"),
    @JsonSubTypes.Type(value = SumAggregation.class, name = "sum"),
})
public interface Aggregation<T> {

  T calculateFor(Category category);

}
