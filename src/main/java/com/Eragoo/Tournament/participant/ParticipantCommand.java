package com.Eragoo.Tournament.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantCommand {
    @NotBlank
    @Size(max = 50, message = "\"name\" field must be not blank and under 50 characters")
    private String name;
}
