package nl.tsakiris.classifier.category;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.config.Aggregation;
import nl.tsakiris.classifier.record.Record;
import nl.tsakiris.classifier.record.RecordProvider;

@AllArgsConstructor
public class ClassifyingCategoryTreeProvider implements CategoryTreeProvider {

  private final RecordProvider recordProvider;

  private final Category categoryTree;

  private final Map<String, Aggregation<?>> aggregations;

  @Override
  public Category get() {

    recordProvider.get().forEach(record -> {
      Category matchedCategory = findMatchingBranch(categoryTree, record);
      if (matchedCategory == null) {
        return;
      }
      matchedCategory.getRecords().add(record);
    });
    calculateAggregations(categoryTree);
    return categoryTree;
  }

  private void calculateAggregations(Category category) {
    for (Map.Entry<String, Aggregation<?>> entry : aggregations.entrySet()) {
      String name = entry.getKey();
      Aggregation<?> aggregation = entry.getValue();
      Object result = aggregation.calculateFor(category);
      category.getAggregations().put(name, result);
      if (category.getCategories() != null) {
        category.getCategories().forEach(this::calculateAggregations);
      }
    }
  }

  private Category findMatchingBranch(Category category, Record record) {
    Condition condition = category.getCondition();
    if (condition != null && !condition.test(record)) {
      return null;
    }
    List<Category> categories = category.getCategories();
    if (categories != null) {
      for (Category child : categories) {
        Category matchedCategory = findMatchingBranch(child, record);
        if (matchedCategory != null) {
          return matchedCategory;
        }
      }
    }
    if (condition != null) {
      return category;
    }
    return null;
  }

}
