package Brandy.models;

import javafx.scene.control.TreeItem;

import javax.mail.Folder;
import javax.mail.Store;

public class TreeItemMail extends TreeItem<String> {
  private String nombre;
  private UsuarioCorreo usuarioCorreo;
  private Folder folder;
  private Store store;

  public TreeItemMail(String nombre, UsuarioCorreo usuarioCorreo, Folder folder) {
    super(nombre);

    this.nombre = nombre;
    this.usuarioCorreo = usuarioCorreo;
    this.folder = folder;
  }

  public Folder getFolder() {
    return folder;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UsuarioCorreo getUsuarioCorreo() {
    return usuarioCorreo;
  }

  public void setUsuarioCorreo(UsuarioCorreo usuarioCorreo) {
    this.usuarioCorreo = usuarioCorreo;
  }

  public void setFolder(Folder folder) {
    this.folder = folder;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }
}



