package nl.tsakiris.classifier.export;

import nl.tsakiris.classifier.category.CategoryTreeProvider;

public interface ExportStrategy {

  void execute(CategoryTreeProvider categoryTreeProvider);

}
