package com.cognizant.messengerback.services;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class SseEmitters {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        emitter.onCompletion(() -> {
//            System.err.println(emitter + " completed");
            this.emitters.remove(emitter);
        });
        emitter.onTimeout(() -> {
//            System.err.println(emitter + " timeout");
            emitter.complete();
            this.emitters.remove(emitter);
        });
//        System.err.println(emitter + " added");
        return emitter;
    }

    void send(Object obj) {
        List<SseEmitter> failedEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(obj);
//                System.err.println(emitter + " sent " + obj);
            } catch (Exception e) {
                System.err.println(emitter + " faild");
                emitter.completeWithError(e);
                failedEmitters.add(emitter);
            }
        });
        this.emitters.removeAll(failedEmitters);
    }
}
