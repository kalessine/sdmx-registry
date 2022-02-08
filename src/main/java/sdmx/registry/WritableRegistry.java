/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry;

import sdmx.message.StructureType;

/**
 *
 * @author jsg
 */
public interface WritableRegistry extends sdmx.Registry {
     public void load(StructureType struct);  
}
