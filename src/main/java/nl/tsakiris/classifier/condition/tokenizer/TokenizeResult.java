package nl.tsakiris.classifier.condition.tokenizer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenizeResult {

  private Token token;
  private String remainingInput;

}
