package nl.tsakiris.classifier.condition.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternTokenizer implements Tokenizer {

  private final Pattern regexPattern = Pattern.compile("^/(([^/\\\\]|\\\\/|\\\\\\\\)*)/.*$");

  @Override
  public TokenizeResult tokenize(String input) {
    Matcher matcher = regexPattern.matcher(input);
    if (matcher.matches()) {
      String group = matcher.group(1);
      String regex = group.replace("\\/", "/");
      Pattern pattern = Pattern.compile(regex);
      return TokenizeResult.builder()
          .token(new RegexPatternToken(pattern))
          .remainingInput(input.substring(group.length() + 2))
          .build();
    }
    return null;
  }

}
