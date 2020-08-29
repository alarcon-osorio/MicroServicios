package com.server.store.product.controller;

import java.util.List;
import java.util.Map;
import lombok.*;

@Getter @Setter @Builder
public class ErrorMessage {
   private String code;
   private List<Map<String, String>> messages;
}
