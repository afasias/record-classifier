package nl.tsakiris.classifier.stream;

import lombok.SneakyThrows;
import nl.tsakiris.classifier.config.FileMedium;
import nl.tsakiris.classifier.config.Medium;

public class OutputStreamProviderFactory {

  private OutputStreamProviderFactory() {}

  public static OutputStreamProvider forMedium(Medium medium) {
    if (medium instanceof FileMedium) {
      return forFileMedium((FileMedium) medium);
    }
    throw new IllegalArgumentException(medium.getClass().getName());
  }

  @SneakyThrows
  private static OutputStreamProvider forFileMedium(FileMedium medium) {
    return new FileOutputStreamProvider(medium.getPath());
  }

}
