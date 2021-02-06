package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.RegexCondition;
import nl.tsakiris.classifier.condition.tokenizer.RegexOperatorToken;
import nl.tsakiris.classifier.condition.tokenizer.RegexPatternToken;
import nl.tsakiris.classifier.condition.tokenizer.TermFieldToken;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public class RegexMatchParser implements Parser {

  @Override
  public ParseResult parse(List<Token> tokens) throws ParserException {
    if (tokens.isEmpty()) {
      return null;
    }
    Token token = tokens.get(0);
    if (token instanceof TermFieldToken) {
      TermFieldToken termFieldToken = (TermFieldToken) token;
      tokens = tokens.subList(1, tokens.size());
      if (tokens.isEmpty()) {
        return null;
      }
      token = tokens.get(0);
      if (!(token instanceof RegexOperatorToken)) {
        return null;
      }
      tokens = tokens.subList(1, tokens.size());
      if (tokens.isEmpty()) {
        return null;
      }
      token = tokens.get(0);
      if (!(token instanceof RegexPatternToken)) {
        return null;
      }
      RegexPatternToken regexPatternToken = (RegexPatternToken) token;
      Condition condition = RegexCondition.builder()
          .field(termFieldToken.getField())
          .pattern(regexPatternToken.getPattern())
          .build();
      return ParseResult.builder()
          .condition(condition)
          .remainingTokens(tokens.subList(1, tokens.size()))
          .build();
    }
    return null;
  }

}
