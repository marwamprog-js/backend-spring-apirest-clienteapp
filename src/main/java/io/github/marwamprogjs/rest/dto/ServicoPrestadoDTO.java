package io.github.marwamprogjs.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestadoDTO {

    @NotBlank(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotBlank(message = "{campo.preco.obrigatorio}")
    private String valor;

    @NotBlank(message = "{campo.data.obrigatorio}")
    private String data;

    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Long idCliente;
}
