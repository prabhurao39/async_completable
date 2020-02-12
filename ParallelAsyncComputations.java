package com.basic.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelAsyncComputations {

  public static void main(String[] args) {
    CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      return "Some Result";
    }).thenApply(result -> {
      /*
       * Executed in the same thread where the supplyAsync() task is executed or in the main thread
       * If the supplyAsync() task completes immediately (Remove sleep() call to verify)
       */
      return "Processed Result";
    });

    CompletableFuture.supplyAsync(() -> {
      return "Some Result";
    }).thenApplyAsync(result -> {
      // Executed in a different thread from ForkJoinPool.commonPool()
      return "Processed Result";
    });



    Executor executor = Executors.newFixedThreadPool(2);
    CompletableFuture.supplyAsync(() -> {
      return "Some result";
    }).thenApplyAsync(result -> {
      // Executed in a thread obtained from the executor
      return "Processed Result";
    }, executor);
  }

}
