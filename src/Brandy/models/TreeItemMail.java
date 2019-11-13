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



  private TreeItem a;
  public void cargarTree (UsuarioCorreo user){

  }
   /* TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
    rootItem.setExpanded(true);
    for (int i = 1; i < lengt; i++) {
      TreeItem<String> item = new TreeItem<String> ("Message" + i);
      rootItem.getChildren().add(item)
*/
  }


 /*

 metodo q es return de Treeitem cargarcarpetas( UsuarioCorreo c){

 0 crear el emailtreeItem raiz
 1 conectar a store
 2 obtener carpetas primer nivel
 3 creo un emailtreeitem por carpeta
 4 añadir a la raiz


   TreeItem<CustomItem> root = new TreeItem<CustomItem>(new CustomItem(new Label("Root")));

    initialize TreeItem<CustomItem> as container for CustomItem object
 TreeItem<CustomItem> node = new TreeItem<CustomItem>(new CustomItem(new Label("Node 1"), new Button("Button 1")));

   add node to root
    root.getChildren().add(node);

   set tree root
    tree.setRoot(root);

   add items to the layout
    layout.setCenter(tree);




 private ObservableList<TreeItem<String>> buildChildren(TreeItem<String> TreeItem) {
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
  }
*/
}
        }