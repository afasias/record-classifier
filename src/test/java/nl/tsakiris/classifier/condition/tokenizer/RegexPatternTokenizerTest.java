package nl.tsakiris.classifier.condition.tokenizer;

import static org.junit.Assert.assertTrue;
import java.util.regex.Pattern;
import org.junit.Test;

public class RegexPatternTokenizerTest {

  private final RegexPatternTokenizer tokenizer = new RegexPatternTokenizer();

  @Test
  public void test() {
    test("/[a-z]+/", "abcdef");
    test("/[a-z]+\\/[a-z]+/", "abc/def");
    test("/[a-z]+\\\\[a-z]+/", "abc\\def");
  }

  private void test(String regex, String testValue) {
    TokenizeResult result = tokenizer.tokenize(regex);
    Pattern pattern = ((RegexPatternToken) result.getToken()).getPattern();
    assertTrue(pattern.matcher(testValue).matches());
    assertTrue(result.getRemainingInput().isEmpty());
  }

}
