package nl.tsakiris.classifier.stream;

import java.io.FileInputStream;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class FileInputStreamProvider implements InputStreamProvider {

  private final String path;

  @Override
  @SneakyThrows
  public InputStream get() {
    return new FileInputStream(path);
  }

}
