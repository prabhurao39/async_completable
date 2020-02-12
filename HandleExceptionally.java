package com.basic.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// https://dzone.com/articles/20-examples-of-using-javas-completablefuture
// https://www.callicoder.com/java-8-completablefuture-tutorial/
public class HandleExceptionally {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Integer age = -1;

    CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
      if (age < 0) {
        throw new IllegalArgumentException("Age can not be negative");
      }
      if (age > 18) {
        return "Adult";
      } else {
        return "Child";
      }
    }).exceptionally(ex -> {
      System.out.println("Oops! We have an exception - " + ex.getMessage());
      return "Unknown!";
    });

    System.out.println("Maturity : " + maturityFuture.get());
  }
}


class Handle {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Integer age = -1;

    CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
      if (age < 0) {
        throw new IllegalArgumentException("Age can not be negative");
      }
      if (age > 18) {
        return "Adult";
      } else {
        return "Child";
      }
    }).handle((res, ex) -> {
      if (ex != null) {
        System.out.println("Oops! We have an exception - " + ex.getMessage());
        return "Unknown!";
      }
      return res;
    });

    System.out.println("Maturity : " + maturityFuture.get());
  }
}
