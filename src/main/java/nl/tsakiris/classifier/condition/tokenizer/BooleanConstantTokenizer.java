package nl.tsakiris.classifier.condition.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanConstantTokenizer implements Tokenizer {

  private final Pattern pattern = Pattern.compile("^(true|false)([^\\\\w].*)?$");

  @Override
  public TokenizeResult tokenize(String input) {
    Matcher matcher = pattern.matcher(input);
    if (matcher.matches()) {
      String match = matcher.group(1);
      boolean value = Boolean.parseBoolean(match);
      return TokenizeResult.builder()
          .token(new BooleanConstantToken(value))
          .remainingInput(input.substring(match.length()))
          .build();
    }
    return null;
  }

}
