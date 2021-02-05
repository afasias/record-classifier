package nl.tsakiris.classifier;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static nl.tsakiris.classifier.condition.TermOperator.equals;
import static nl.tsakiris.classifier.condition.TermOperator.startsWith;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.tsakiris.classifier.condition.NotCondition;
import nl.tsakiris.classifier.condition.TermCondition;
import nl.tsakiris.classifier.config.Aggregation;
import nl.tsakiris.classifier.config.CountAggregation;
import nl.tsakiris.classifier.config.SumAggregation;
import nl.tsakiris.classifier.record.Record;
import nl.tsakiris.classifier.category.ClassifyingCategoryTreeProvider;
import nl.tsakiris.classifier.category.Category;
import nl.tsakiris.classifier.category.CategoryTreeProvider;
import org.junit.Test;

public class ClassifyingCategoryTreeProviderTest {

  private final Category categoryTree = Category.builder()
      .condition(new NotCondition(new TermCondition("name", startsWith, "Ignored")))
      .categories(asList(
          Category.builder()
              .name("Credit")
              .condition(new TermCondition("debitCredit", equals, "Credit"))
              .build(),
          Category.builder()
              .name("Debit")
              .condition(new TermCondition("debitCredit", equals, "Debit"))
              .categories(singletonList(
                  Category.builder()
                      .name("Super Market")
                      .categories(asList(
                          Category.builder()
                              .name("Albert Heijn")
                              .condition(new TermCondition("name", startsWith, "Albert"))
                              .build(),
                          Category.builder()
                              .name("Dirk van den Broek")
                              .condition(new TermCondition("name", startsWith, "Dirk"))
                              .build()
                      ))
                      .build()
              ))
              .build()
      ))
      .build();

  private final List<Record> records = asList(
      Record.builder()
          .with("debitCredit", "Credit")
          .with("amount", "100")
          .with("name", "Ignored")
          .build(),
      Record.builder()
          .with("debitCredit", "Credit")
          .with("amount", "100")
          .build(),
      Record.builder()
          .with("debitCredit", "Debit")
          .with("amount", "100")
          .with("name", "Albert Heijn")
          .build(),
      Record.builder()
          .with("debitCredit", "Debit")
          .with("amount", "20")
          .with("name", "Albert Heijn")
          .build(),
      Record.builder()
          .with("debitCredit", "Debit")
          .with("amount", "150")
          .with("name", "Dirk van den Broek")
          .build(),
      Record.builder()
          .with("debitCredit", "Debit")
          .with("amount", "50")
          .with("name", "Other Expense")
          .build()
  );

  private final Map<String, Aggregation<?>> aggregations = new HashMap<String, Aggregation<?>>() {{
    put("totalAmount", new SumAggregation("amount"));
    put("count", new CountAggregation());
  }};

  @Test
  public void test() {

    CategoryTreeProvider categoryTreeClassifier =
        new ClassifyingCategoryTreeProvider(() -> records, categoryTree, aggregations);
    Category categoryTree = categoryTreeClassifier.get();

    Category creditCategory = categoryTree.getCategories().get(0);
    assertEquals("Credit", creditCategory.getName());
    assertEquals(1, creditCategory.getAggregations().get("count"));
    assertEquals(new BigDecimal("100"), creditCategory.getAggregations().get("totalAmount"));

    Category debitCategory = categoryTree.getCategories().get(1);
    assertEquals("Debit", debitCategory.getName());
    assertEquals(4, debitCategory.getAggregations().get("count"));
    assertEquals(new BigDecimal("320"), debitCategory.getAggregations().get("totalAmount"));

    Category superMarketCategory = debitCategory.getCategories().get(0);
    assertEquals("Super Market", superMarketCategory.getName());
    assertEquals(3, superMarketCategory.getAggregations().get("count"));
    assertEquals(new BigDecimal("270"), superMarketCategory.getAggregations().get("totalAmount"));

    Category albertHeijnCategory = superMarketCategory.getCategories().get(0);
    assertEquals("Albert Heijn", albertHeijnCategory.getName());
    assertEquals(2, albertHeijnCategory.getAggregations().get("count"));
    assertEquals(new BigDecimal("120"), albertHeijnCategory.getAggregations().get("totalAmount"));

    Category dirkCategory = superMarketCategory.getCategories().get(1);
    assertEquals("Dirk van den Broek", dirkCategory.getName());
    assertEquals(1, dirkCategory.getAggregations().get("count"));
    assertEquals(new BigDecimal("150"), dirkCategory.getAggregations().get("totalAmount"));

  }

}
