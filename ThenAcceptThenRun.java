package com.basic.async;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptThenRun {

  // thenApply() variants
  // <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
  // <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
  // <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)

  public static void main(String[] args) {
    // thenAccept() example
    int productId = 12344;
    CompletableFuture.supplyAsync(() -> {
      return ProductService.getProductDetail(productId);
    }).thenAccept(product -> {
      System.out.println(" hiii........");
      System.out.println("Got product detail from remote service " + product.name);
    });

    // thenRun() example
    CompletableFuture.supplyAsync(() -> {
      // Run some computation
      return new String("Hi Good morning.. ");
    }).thenRun(() -> {
      // Computation Finished.
      System.out.println(" Prabhu ");
    });
  }

}


class ProductService {
  ProductService() {}

  public static Product getProductDetail(int id) {
    return new Product(id);
  }
}


class Product {
  int id;
  String name = "woodlands";

  Product(int id) {
    this.id = id;
  }
}
