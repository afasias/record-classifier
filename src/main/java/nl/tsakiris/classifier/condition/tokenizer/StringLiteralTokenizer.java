package nl.tsakiris.classifier.condition.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringLiteralTokenizer implements Tokenizer {

  private final Pattern pattern = Pattern.compile("^'(([^\\\\']|\\\\'|\\\\\\\\)*)'.*$");

  @Override
  public TokenizeResult tokenize(String input) {
    Matcher matcher = pattern.matcher(input);
    if (matcher.matches()) {
      String literal = matcher.group(1);
      String value = literal.replace("\\'", "'").replace("\\\\", "\\");
      return TokenizeResult.builder()
          .token(new StringLiteralToken(value))
          .remainingInput(input.substring(literal.length() + 2))
          .build();
    }
    return null;
  }

}
