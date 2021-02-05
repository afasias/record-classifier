package nl.tsakiris.classifier.condition.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StringLiteralTokenizerTest {

  private final StringLiteralTokenizer tokenizer = new StringLiteralTokenizer();

  @Test
  public void test() {
    test("'simple'", "simple");
    test("'abc\\'def'", "abc'def");
    test("'abc\\\\def'", "abc\\def");
  }

  private void test(String input, String expected) {
    TokenizeResult result = tokenizer.tokenize(input);
    String value = ((StringLiteralToken) result.getToken()).getValue();
    assertEquals(expected, value);
    assertTrue(result.getRemainingInput().isEmpty());
  }

}
