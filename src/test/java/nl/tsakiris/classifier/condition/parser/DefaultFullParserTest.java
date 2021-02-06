package nl.tsakiris.classifier.condition.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import lombok.SneakyThrows;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.record.Record;
import org.junit.Test;

public class DefaultFullParserTest {

  private final FullParser fullParser = new DefaultFullParser();

  @Test
  @SneakyThrows
  public void test_true() {
    Condition condition = fullParser.parse("true");
    assertTrue(condition.test(null));
  }

  @Test
  @SneakyThrows
  public void test_false() {
    Condition condition = fullParser.parse("false");
    assertFalse(condition.test(null));
  }

  @Test
  public void test_stringOperations() {
    test_stringOperation("name == 'example'", "example", "Example");
    test_stringOperation("name equals 'example'", "example", "Example");
    test_stringOperation("name equalsIgnoreCase 'example'", "Example", "other example");
    test_stringOperation("name startsWith 'example'", "example_", "Example_");
    test_stringOperation("name endsWith 'example'", "_example", "_Example");
    test_stringOperation("name contains 'example'", "_example_", "_Example_");
    test_stringOperation("! name == 'example'", "Example", "example");
    test_stringOperation("name startsWith 'exa' && name endsWith 'ple'", "example", "Example");
    test_stringOperation("name startsWith 'exa' || name endsWith 'ple'", "Example", "ExamplE");
  }

  @SneakyThrows
  private void test_stringOperation(String input, String matchingText, String mismatchingText) {
    Condition condition = fullParser.parse(input);
    Record matchingRecord = Record.builder()
        .with("name", matchingText)
        .build();
    Record mismatchingRecord = Record.builder()
        .with("name", mismatchingText)
        .build();
    assertTrue(condition.test(matchingRecord));
    assertFalse(condition.test(mismatchingRecord));
  }

  @Test
  public void test_logicalOperatorsAndPrecedence() {
    test_logicalOperatorsAndPrecedence("! false || true", true);
    test_logicalOperatorsAndPrecedence("! (false || true)", false);
    test_logicalOperatorsAndPrecedence("false && false || true", true);
    test_logicalOperatorsAndPrecedence("false && (false || true)", false);
    test_logicalOperatorsAndPrecedence("true || false && false", false);
    test_logicalOperatorsAndPrecedence("true || (false && false)", true);
  }

  @SneakyThrows
  private void test_logicalOperatorsAndPrecedence(String input, boolean expected) {
    Condition condition = fullParser.parse(input);
    assertEquals(expected, condition.test(null));
  }

}
