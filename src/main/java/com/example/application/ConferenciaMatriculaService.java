package com.example.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ConferenciaMatriculaService {


    @Async // runs in a separate thread (enable with @EnableAsync in a config class)
    public CompletableFuture<Void> longRunningTask(String matricula, Map<String, String> itensMatricula) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("deu erro");
            return CompletableFuture.failedFuture(new RuntimeException("Error"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("deu outor erro");
            return CompletableFuture.failedFuture(new RuntimeException("Error"));
        }

        return CompletableFuture.completedFuture(null);
    }
}

