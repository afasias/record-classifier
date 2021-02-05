package nl.tsakiris.classifier.condition.parser;

import java.util.List;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.TermCondition;
import nl.tsakiris.classifier.condition.tokenizer.StringLiteralToken;
import nl.tsakiris.classifier.condition.tokenizer.TermFieldToken;
import nl.tsakiris.classifier.condition.tokenizer.TermOperatorToken;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public class StringFunctionParser implements Parser {

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
      if (!(token instanceof TermOperatorToken)) {
        return null;
      }
      TermOperatorToken termOperatorToken = (TermOperatorToken) token;
      tokens = tokens.subList(1, tokens.size());
      if (tokens.isEmpty()) {
        return null;
      }
      token = tokens.get(0);
      if (!(token instanceof StringLiteralToken)) {
        return null;
      }
      StringLiteralToken stringLiteralToken = (StringLiteralToken) token;
      Condition condition = TermCondition.builder()
          .field(termFieldToken.getField())
          .operator(termOperatorToken.getValue())
          .value(stringLiteralToken.getValue())
          .build();
      return ParseResult.builder()
          .condition(condition)
          .remainingTokens(tokens.subList(1, tokens.size()))
          .build();
    }
    return null;
  }

}
