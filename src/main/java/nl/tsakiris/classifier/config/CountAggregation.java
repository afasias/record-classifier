package nl.tsakiris.classifier.config;

import lombok.Data;
import nl.tsakiris.classifier.category.Category;

@Data
public class CountAggregation implements Aggregation<Integer> {

  @Override
  public Integer calculateFor(Category category) {
    int count = category.getRecords().size();
    if (category.getCategories() != null) {
      for (Category childCategory : category.getCategories()) {
        count += calculateFor(childCategory);
      }
    }
    return count;
  }

}
