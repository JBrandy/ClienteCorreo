package Brandy.models;

import javafx.scene.control.TreeItem;

import javax.mail.Folder;

public class TreeItemMail extends TreeItem<String> {
  private String nombre;
  private UsuarioCorreo user;
  private Folder[] folder;

  public TreeItemMail(String nombre, UsuarioCorreo user, Folder[] folder) {
    super(nombre);

    this.nombre = nombre;
    this.user = user;
    this.folder = folder;
  }



 /* private ObservableList<TreeItem<String>> buildChildren(TreeItem<String> TreeItem) {
    File f = new File(TreeItem.getValue());
    if (f != null && f.isDirectory()) {
      File[] files = f.listFiles();
      if (files != null) {
        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();
        for (File childFile : files) {
          children.add(createNode(childFile));
        }
        return children;
      }
    }
    return FXCollections.emptyObservableList();
  }*/

}
