package com.senai.projintrv.service;

import org.springframework.stereotype.Service;

import com.senai.projintrv.models.Cliente;
import com.senai.projintrv.models.Consultor;
import com.senai.projintrv.repository.ClienteRepository;
import com.senai.projintrv.repository.ConsultorRepository;

import jakarta.transaction.Transactional;

@Service 
public class ClienteService {

    // Cria variaveis clienterepository e consultorrepository

    private  final ClienteRepository clienteRepository;
    private  final ConsultorRepository consultorRepository;

    // Cria o construtor
    public ClienteService(
        ClienteRepository clienteRepository,
        ConsultorRepository consultorRepository
    ){
        this.clienteRepository = clienteRepository;
        this.consultorRepository = consultorRepository;
    }



    // Create 

    @Transactional 
    public Cliente criar(Long idConsultor, Cliente cliente){

        // Primeiro verifica se o consultor existe

        Consultor consultor = consultorRepository.findById(idConsultor).orElseThrow(
            ()->new RuntimeException("Consultor não encontrado")
        );


        // Validação simples

        if(cliente.getNomeEmpresa()==null || cliente.getNomeEmpresa().isBlank()){
            throw new RuntimeException("Nome da empresa é obrigatório");
        }



        if(cliente.getSegmento()==null || cliente.getSegmento().isBlank()){
            throw new RuntimeException(
                "Segmento é obrigatório"
            );
        }


        if(cliente.getFaturamentoAnual()== null){
            throw new RuntimeException(
                "Faturamento anual é obrigatório"
            );
        }


        // Associa o consultor encontrado ao cliente

        cliente.setConsultor(consultor);

        // Agora salva

        return clienteRepository.save(cliente);
    }


    // Read por id

    public Cliente buscarporId(Long id){
        return clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente não encontrado"));
    }


    // Update

    @Transactional 
    public  Cliente atualizar(Long id, Cliente dados){
        Cliente cliente = buscarporId(id);

        cliente.setNomeEmpresa(dados.getNomeEmpresa());

        cliente.setSegmento(dados.getSegmento());


        cliente.setFaturamentoAnual(dados.getFaturamentoAnual());

        cliente.setNivel(dados.getNivel());


        cliente.setStatus(dados.getStatus());

        return  clienteRepository.save(cliente);
    }


    // Delete 

    @Transactional 
    public void excluir(Long id){
        Cliente cliente = buscarporId(id);

        clienteRepository.deleteById(cliente.getIdCliente());
    }
    
}
