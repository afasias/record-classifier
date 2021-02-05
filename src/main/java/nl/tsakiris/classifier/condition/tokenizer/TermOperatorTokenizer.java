package nl.tsakiris.classifier.condition.tokenizer;

import static java.util.stream.Collectors.joining;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import nl.tsakiris.classifier.condition.TermOperator;

public class TermOperatorTokenizer implements Tokenizer {

  private final Pattern pattern = Pattern.compile("^(" +
      Stream.of(TermOperator.values()).map(Enum::name).collect(joining("|")) +
      ")([^\\\\w].*)?$");

  @Override
  public TokenizeResult tokenize(String input) {
    Matcher matcher = pattern.matcher(input);
    if (matcher.matches()) {
      String match = matcher.group(1);
      TermOperator value = TermOperator.valueOf(match);
      return TokenizeResult.builder()
          .token(new TermOperatorToken(value))
          .remainingInput(input.substring(match.length()))
          .build();
    }
    return null;
  }

}
