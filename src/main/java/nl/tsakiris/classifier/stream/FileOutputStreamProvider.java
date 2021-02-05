package nl.tsakiris.classifier.stream;

import java.io.FileOutputStream;
import java.io.OutputStream;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class FileOutputStreamProvider implements OutputStreamProvider {

  private final String path;

  @Override
  @SneakyThrows
  public OutputStream get() {
    return new FileOutputStream(path);
  }

}
