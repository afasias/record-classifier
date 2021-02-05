package nl.tsakiris.classifier.condition.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermFieldTokenizer implements Tokenizer {

  private final Pattern pattern = Pattern.compile("^(\\w+).*$");

  @Override
  public TokenizeResult tokenize(String input) {
    Matcher matcher = pattern.matcher(input);
    if (matcher.matches()) {
      String field = matcher.group(1);
      return TokenizeResult.builder()
          .token(new TermFieldToken(field))
          .remainingInput(input.substring(field.length()))
          .build();
    }
    return null;
  }

}
