package nl.tsakiris.classifier.condition.parser;

import static java.util.Arrays.asList;
import java.util.List;
import nl.tsakiris.classifier.condition.AndCondition;
import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.NotCondition;
import nl.tsakiris.classifier.condition.OrCondition;
import nl.tsakiris.classifier.condition.tokenizer.BooleanBinaryOperator;
import nl.tsakiris.classifier.condition.tokenizer.BooleanBinaryOperatorToken;
import nl.tsakiris.classifier.condition.tokenizer.BooleanNotOperatorToken;
import nl.tsakiris.classifier.condition.tokenizer.CloseParenthesisToken;
import nl.tsakiris.classifier.condition.tokenizer.OpenParenthesisToken;
import nl.tsakiris.classifier.condition.tokenizer.Token;

public class BooleanParser implements Parser {

  private final Parser expressionParser = new ExpressionParser();

  @Override
  public ParseResult parse(List<Token> tokens) throws ParserException {
    ParseResult firstTermParseResult = parseFirstTerm(tokens);
    if (firstTermParseResult == null) {
      return null;
    }
    tokens = firstTermParseResult.getRemainingTokens();
    if (tokens.isEmpty() || !(tokens.get(0) instanceof BooleanBinaryOperatorToken)) {
      return firstTermParseResult;
    }
    BooleanBinaryOperatorToken booleanBinaryOperatorToken =
        (BooleanBinaryOperatorToken) tokens.get(0);
    BooleanBinaryOperator operator = booleanBinaryOperatorToken.getOperator();
    tokens = tokens.subList(1, tokens.size());
    ParseResult booleanParseResult = parse(tokens);
    if (booleanParseResult == null) {
      throw new ParserException();
    }
    List<Condition> conditions = asList(
        firstTermParseResult.getCondition(),
        booleanParseResult.getCondition());
    Condition condition = BooleanBinaryOperator.AND.equals(operator) ?
        AndCondition.builder().conditions(conditions).build() :
        OrCondition.builder().conditions(conditions).build();
    return ParseResult.builder()
        .condition(condition)
        .remainingTokens(booleanParseResult.getRemainingTokens())
        .build();
  }

  private ParseResult parseFirstTerm(List<Token> tokens) throws ParserException {
    if (tokens.isEmpty()) {
      return null;
    }
    ParseResult expressionParseResult = expressionParser.parse(tokens);
    if (expressionParseResult != null) {
      return expressionParseResult;
    }
    if (tokens.get(0) instanceof BooleanNotOperatorToken) {
      tokens = tokens.subList(1, tokens.size());
      ParseResult booleanParseResult = parse(tokens);
      if (booleanParseResult == null) {
        return null;
      }
      NotCondition condition = NotCondition.builder()
          .condition(booleanParseResult.getCondition())
          .build();
      return ParseResult.builder()
          .condition(condition)
          .remainingTokens(booleanParseResult.getRemainingTokens())
          .build();
    }
    if (tokens.get(0) instanceof OpenParenthesisToken) {
      tokens = tokens.subList(1, tokens.size());
      ParseResult booleanParseResult = parse(tokens);
      if (booleanParseResult == null) {
        return null;
      }
      tokens = booleanParseResult.getRemainingTokens();
      if (tokens.isEmpty()) {
        return null;
      }
      if (!(tokens.get(0) instanceof CloseParenthesisToken)) {
        return null;
      }
      return ParseResult.builder()
          .condition(booleanParseResult.getCondition())
          .remainingTokens(tokens.subList(1, tokens.size()))
          .build();
    }
    return null;
  }

}
