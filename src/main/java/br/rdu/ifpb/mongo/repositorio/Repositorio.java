/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rdu.ifpb.mongo.repositorio;

import br.rdu.ifpb.mongo.entity.Pessoa;

/**
 *
 * @author José Ferreira
 */
public interface Repositorio {

    int add(Pessoa pessoa);

    Pessoa find(String key, String value);

    int remove(String key, String value);

    int upDate(Pessoa pessoa);
    
}
