package nl.tsakiris.classifier.stream;

import lombok.SneakyThrows;
import nl.tsakiris.classifier.config.FileMedium;
import nl.tsakiris.classifier.config.Medium;

public class InputStreamProviderFactory {

  private InputStreamProviderFactory() {}

  public static InputStreamProvider forMedium(Medium medium) {
    if (medium instanceof FileMedium) {
      return forFileMedium((FileMedium) medium);
    }
    throw new IllegalArgumentException(medium.getClass().getName());
  }

  @SneakyThrows
  private static InputStreamProvider forFileMedium(FileMedium medium) {
    return new FileInputStreamProvider(medium.getPath());
  }

}
