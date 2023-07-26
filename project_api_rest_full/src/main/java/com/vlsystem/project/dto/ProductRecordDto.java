package com.vlsystem.project.dto;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotBlank String nome,@NotNull BigDecimal value) {

	
}
