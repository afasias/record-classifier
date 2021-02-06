package nl.tsakiris.classifier.condition.parser;

import nl.tsakiris.classifier.condition.Condition;
import nl.tsakiris.classifier.condition.tokenizer.TokenizerException;

public interface FullParser {

  Condition parse(String input) throws ParserException, TokenizerException;

}
