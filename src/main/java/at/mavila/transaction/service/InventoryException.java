package at.mavila.transaction.service;

import java.io.Serial;

public class InventoryException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 8287343L;

  public InventoryException(String message) {
    super(message);
  }

}