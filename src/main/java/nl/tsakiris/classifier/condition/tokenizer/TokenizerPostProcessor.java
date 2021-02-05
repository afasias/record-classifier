package nl.tsakiris.classifier.condition.tokenizer;

import java.util.List;

public interface TokenizerPostProcessor {

  List<Token> process(List<Token> input) throws TokenizerException;

}
