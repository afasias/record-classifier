package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.tokenizer.Token;

@Data
@Builder
public class ParseResult {

  private final Condition condition;

  private final List<Token> remainingTokens;

}
