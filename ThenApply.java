package com.basic.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenApply {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    // Create a CompletableFuture
    CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(0);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      return "Rajeev";
    });

    // Attach a callback to the Future using thenApply()
    CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
      return "Hello " + name;
    });

    // Block and get the result of the future.
    System.out.println(greetingFuture.get()); // Hello Rajeev
  }

}


class ThenApplyThenApply {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      return "Rajeev";
    }).thenApply(name -> {
      return "Hello " + name;
    }).thenApply(greeting -> {
      return greeting + ", Welcome to the CalliCoder Blog";
    });

    System.out.println(welcomeText.get());
  }

}
