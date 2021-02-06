package nl.tsakiris.classifier.condition.tokenizer;

import java.util.regex.Pattern;
import lombok.Data;

@Data
public class RegexPatternToken implements Token {

  private final Pattern pattern;

}
