package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.FixedCondition;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.tokenizer.BooleanConstantToken;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public class BooleanConstantParser implements Parser {

  @Override
  public ParseResult parse(List<Token> tokens) throws ParserException {
    if (tokens.isEmpty()) {
      return null;
    }
    Token token = tokens.get(0);
    if (token instanceof BooleanConstantToken) {
      BooleanConstantToken booleanConstantToken = (BooleanConstantToken) token;
      Condition condition = FixedCondition.builder()
          .value(booleanConstantToken.isValue())
          .build();
      return ParseResult.builder()
          .condition(condition)
          .remainingTokens(tokens.subList(1, tokens.size()))
          .build();
    }
    return null;
  }

}
