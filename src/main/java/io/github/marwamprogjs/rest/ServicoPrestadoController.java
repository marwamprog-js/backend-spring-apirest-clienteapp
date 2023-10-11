package io.github.marwamprogjs.rest;

import io.github.marwamprogjs.model.entity.Cliente;
import io.github.marwamprogjs.model.entity.ServicoPrestado;
import io.github.marwamprogjs.model.repository.ClienteRepository;
import io.github.marwamprogjs.model.repository.ServicoPrestadoRepository;
import io.github.marwamprogjs.rest.dto.ServicoPrestadoDTO;
import io.github.marwamprogjs.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadoController(ClienteRepository clienteRepository, ServicoPrestadoRepository servicoPrestadoRepository, BigDecimalConverter bigDecimalConverter) {
        this.clienteRepository = clienteRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
        this.bigDecimalConverter = bigDecimalConverter;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = clienteRepository
                .findById(dto.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));


        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setValor(bigDecimalConverter.converterStringToBigDecimal(dto.getValor()));
        servicoPrestado.setCliente(cliente);

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                           @RequestParam(value = "mes", required = false) Integer mes) {

        return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);

    }

}
