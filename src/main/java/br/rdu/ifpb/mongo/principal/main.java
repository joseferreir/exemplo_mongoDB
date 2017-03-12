/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rdu.ifpb.mongo.principal;

import br.rdu.ifpb.mongo.entity.Pessoa;
import br.rdu.ifpb.mongo.repositorio.PessoaRepositoriMongo;

/**
 *
 * @author José Ferreira
 */
public class main {

    public static void main(String[] args) {
        PessoaRepositoriMongo dao = new PessoaRepositoriMongo("localhost", "aula", 27017);
        // dao.upDate(new Pessoa("maria", "333.333.333-44", 24));
        //  dao.remove("nome", "maria");
         Pessoa wpessoa = dao.find("nome", "maria");
        System.err.println("result "+wpessoa);
    }
}
