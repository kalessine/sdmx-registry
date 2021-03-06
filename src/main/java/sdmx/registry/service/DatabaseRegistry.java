/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import sdmx.Registry;
import sdmx.SdmxIO;
import sdmx.commonreferences.CodeReference;
import sdmx.commonreferences.CodelistReference;
import sdmx.commonreferences.ConceptReference;
import sdmx.commonreferences.ConceptSchemeReference;
import sdmx.commonreferences.DataStructureReference;
import sdmx.commonreferences.DataflowReference;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.ItemReference;
import sdmx.commonreferences.ItemSchemeReferenceBase;
import sdmx.commonreferences.NestedNCNameID;
import sdmx.commonreferences.Version;
import sdmx.exception.ParseException;
import sdmx.registry.entities.Codelist;
import sdmx.registry.entities.Dataflow;
import sdmx.registry.entities.DataStructure;
import sdmx.registry.util.AnnotationsUtil;
import sdmx.registry.util.CodeUtil;
import sdmx.registry.util.CodelistUtil;
import sdmx.registry.util.ConceptSchemeUtil;
import sdmx.registry.util.ConceptUtil;
import sdmx.registry.util.DataStructureUtil;
import sdmx.registry.util.DataflowUtil;
import sdmx.registry.util.LanguageUtil;
import sdmx.message.StructureType;
import sdmx.registry.WritableRegistry;
import sdmx.structure.base.ItemSchemeType;
import sdmx.structure.base.ItemType;
import sdmx.structure.codelist.CodeType;
import sdmx.structure.codelist.CodelistType;
import sdmx.structure.concept.ConceptSchemeType;
import sdmx.structure.concept.ConceptType;
import sdmx.structure.dataflow.DataflowType;
import sdmx.structure.datastructure.DataStructureType;

/**
 *
 * @author James
 */
public class DatabaseRegistry implements WritableRegistry {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sdmxregistryPU");
    EntityManager query = EMF.createEntityManager();
    EntityManager update = EMF.createEntityManager();
    
    public static void main(String args[]) throws IOException, ParseException {
       new DatabaseRegistry();
    }

    public DatabaseRegistry() {
        LanguageUtil.init(update);
    }

    @Override
    public void load(StructureType struct) {
        try {
            System.out.println("Loading Codelists");
            loadCodelists(struct);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println("Loading Concepts");
            loadConcepts(struct);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println("Loading DataStructures");
            loadDataStructures(struct);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println("Loading Dataflows");
            loadDataflows(struct);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadDataflows(StructureType struct) {
        if( struct.getStructures().getDataflows()==null ) {return; }
        for(int i=0;i<struct.getStructures().getDataflows().size();i++) {
            DataflowType df = struct.getStructures().getDataflows().getDataflow(i);
            sdmx.registry.entities.Dataflow dataflow = DataflowUtil.findDataflow(update, df.getAgencyID().toString(), df.getId().toString(), df.getVersion().toString());
            boolean alreadyExists = false;
            if (dataflow != null) {
                alreadyExists = true;
                System.out.println("Dataflow: " + df.getAgencyID().toString() + ":" + df.getId().toString() + ":" + df.getVersion().toString() + " already exists");
                continue;
            }
            update.getTransaction().begin();
            Dataflow dbdf = DataflowUtil.createDatabaseDataflow(update, df);
            update.merge(dbdf);
            update.getTransaction().commit();
        }
    }    
    public void loadCodelists(StructureType struct) {
        if (struct.getStructures().getCodelists() == null) {
            return;
        }
        for (int i = 0; i < struct.getStructures().getCodelists().getCodelists().size(); i++) {
            CodelistType c = struct.getStructures().getCodelists().getCodelists().get(i);
            List<CodeType> clist = new ArrayList<CodeType>();
            for (ItemType itm : c.getItems()) {
                clist.add((CodeType) itm);
            }
            boolean alreadyExists = false;
            sdmx.registry.entities.Codelist codelist = CodelistUtil.findDatabaseCodelist(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString());
            if (codelist != null) {
                alreadyExists = true;
                System.out.println("Codelist: " + c.getAgencyID().toString() + ":" + c.getId().toString() + ":" + c.getVersion().toString() + " already exists");
                continue;
            }
            sdmx.registry.entities.Code cdb = null;
            for (int j = 0; j < c.size(); j++) {
                cdb = CodeUtil.findDatabaseCode(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString(), c.getItem(j).getId().toString());
                if (cdb != null) {
                    break;
                    /*alreadyExists = true;
                    Iterator<CodeType> it = clist.iterator();
                    while (it.hasNext()) {
                        CodeType ct = it.next();
                        if (ct.getId().equals(cdb.getCodePK().getId())) {
                            it.remove();
                        }
                    }*/
                }
            }

            if (alreadyExists) {

                try {
                    update.getTransaction().begin();
                    sdmx.registry.entities.Codelist cs = CodelistUtil.findDatabaseCodelist(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString());
                    // What isn't already in the database
                    for (CodeType ct : clist) {
                        sdmx.registry.entities.Code con = CodeUtil.createDatabaseCode(update, cs, ct);
                        cs.getCodeList().add(con);
                    }
                    update.merge(cs);
                    update.getTransaction().commit();
                } catch (Exception ex) {
                    update.getTransaction().rollback();
                    ex.printStackTrace();
                } finally {
                    update.clear();
                }
            } else {
                try {
                    update.getTransaction().begin();
                    sdmx.registry.entities.Codelist cs = CodelistUtil.createDatabaseCodelist(update, c);
                    update.persist(cs);
                    update.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    update.getTransaction().rollback();
                } finally {
                    update.clear();

                }
            }

        }

    }

    public void loadConcepts(StructureType struct) {
        if (struct.getStructures().getConcepts() == null) {
            return;
        }
        for (int i = 0; i < struct.getStructures().getConcepts().getConceptSchemes().size(); i++) {
            ConceptSchemeType c = struct.getStructures().getConcepts().getConceptSchemes().get(i);
            sdmx.registry.entities.ConceptScheme  conceptscheme1 = ConceptSchemeUtil.findDatabaseConceptScheme(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString());
            boolean alreadyExists = false;
            if (conceptscheme1 != null) {
                alreadyExists = true;
                System.out.println("ConceptScheme: " + c.getAgencyID().toString() + ":" + c.getId().toString() + ":" + c.getVersion().toString() + " already exists");
                continue;
            }

            List<ConceptType> clist = new ArrayList<ConceptType>();
            for (ItemType itm : c.getItems()) {
                clist.add((ConceptType) itm);
            }
            alreadyExists = false;
            sdmx.registry.entities.ConceptScheme conceptscheme = ConceptSchemeUtil.findDatabaseConceptScheme(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString());
            if (conceptscheme != null) {
                alreadyExists = true;
                System.out.println("ConceptScheme: " + c.getAgencyID().toString() + ":" + c.getId().toString() + ":" + c.getVersion().toString() + " already exists");
                continue;
            }
            sdmx.registry.entities.Concept cdb = null;
            for (int j = 0; j < c.size(); j++) {
                cdb = ConceptUtil.findDatabaseConcept(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString(), c.getItem(j).getId().toString());
                if (cdb != null) {
                    alreadyExists = true;
                    Iterator<ConceptType> it = clist.iterator();
                    while (it.hasNext()) {
                        ConceptType ct = it.next();
                        if (ct.getId().equals(cdb.getConceptid())) {
                            it.remove();
                        }
                    }
                }
            }

            if (alreadyExists) {
                try {
                    update.getTransaction().begin();
                    sdmx.registry.entities.ConceptScheme cs = ConceptSchemeUtil.findDatabaseConceptScheme(update, c.getAgencyID().toString(), c.getId().toString(), c.getVersion().toString());
                    // What isn't already in the database
                    for (ConceptType ct : clist) {
                        sdmx.registry.entities.Concept con = ConceptUtil.createDatabaseConcept(update, cs, ct);
                        cs.getConceptList().add(con);
                    }
                    
                    update.merge(cs);
                    update.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    update.clear();
                }
            } else {
                try {
                    update.getTransaction().begin();
                    sdmx.registry.entities.ConceptScheme cs = ConceptSchemeUtil.createDatabaseConceptScheme(update, c);
                    update.persist(cs);
                    update.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                   update.clear();
                }
            }

        }

    }

    public void loadDataStructures(StructureType struct) {
        if (struct.getStructures().getDataStructures() == null) {
            System.out.println("Data Structure Is Null");
            return;
        }
        for (int i = 0; i < struct.getStructures().getDataStructures().getDataStructures().size(); i++) {
            try {
                DataStructureType ds = struct.getStructures().getDataStructures().getDataStructures().get(i);
            sdmx.registry.entities.DataStructure datastructure = DataStructureUtil.findDataStructure(update, ds.getAgencyID().toString(), ds.getId().toString(), ds.getVersion().toString());
            boolean alreadyExists = false;
            if (datastructure != null) {
                alreadyExists = true;
                System.out.println("Datastructure: " + ds.getAgencyID().toString() + ":" + ds.getId().toString() + ":" + ds.getVersion().toString() + " already exists");
                continue;
            }
                update.getTransaction().begin();
                System.out.println("DS " + ds.getAgencyID().toString() + ":" + ds.getId().toString() + ":" + ds.getVersion().toString());
                sdmx.registry.entities.DataStructure ds2;
                ds2 = DataStructureUtil.createDatabaseDataStructure(update, ds);
                update.persist(ds2);
                update.flush();
                update.getTransaction().commit();
        } catch(javax.persistence.RollbackException re) {
            for(int j=0;j<re.getSuppressed().length;j++){
              re.getSuppressed()[j].printStackTrace();
            }
            re.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
            if(update.getTransaction().isActive()){
               update.getTransaction().rollback();
               }
        }
        finally {
                update.clear();
            }
        }
    }

    @Override
    public void unload(StructureType struct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {

    }

    @Override
    public List<DataflowType> listDataflows() {
        List<DataflowType> dataflows = search(DataflowReference.create(new NestedNCNameID("all"),new IDType("all"), new Version("*")));
        return dataflows;
    }

    @Override
    public DataStructureType find(DataStructureReference ref) {
        return DataStructureUtil.toSDMXDataStructure(DataStructureUtil.findDataStructure(query, ref.getAgencyId().toString(), ref.getMaintainableParentId().toString(), ref.getVersion().toString()));
    }

    @Override
    public DataflowType find(DataflowReference ref) {
        Dataflow result  = DataflowUtil.findDataflow(query, ref.getAgencyId().toString(),ref.getMaintainableParentId().toString(),ref.getVersion().toString());
        DataflowType sdmxDataflow = DataflowUtil.toSDMXDataflow(result);
        return sdmxDataflow;
    }

    @Override
    public CodeType find(CodeReference ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CodelistType find(CodelistReference ref) {
        return CodelistUtil.toSDMXCodelist(CodelistUtil.findDatabaseCodelist(this.query, ref.getAgencyId().toString(), ref.getMaintainableParentId().toString(), ref.getVersion().toString()));
    }

    @Override
    public ItemType find(ItemReference ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemSchemeType find(ItemSchemeReferenceBase ref) {
        ConceptSchemeType ct = find(ref.asConceptSchemeReference());
        if( ct != null ) return ct;
        CodelistType ct2 = find(ref.asCodelistReference());
        return ct2;
    }

    @Override
    public ConceptType find(ConceptReference ref) {
        ConceptSchemeType cs = this.find(ref.toConceptSchemeReference());
        return cs.findConcept(ref.getId());
    }

    @Override
    public ConceptSchemeType find(ConceptSchemeReference ref) {
        List<ConceptSchemeType> css = this.search(ref);
        if( css.size()>0 ) {
            return css.get(0);
        }else return null;
    }

    @Override
    public List<DataStructureType> search(DataStructureReference ref) {
        //ref.dump();
        List<DataStructure> list = DataStructureUtil.searchDataStructure(query, ref.getAgencyId().toString(),ref.getMaintainableParentId().toString(),ref.getVersion().toString());
        List<DataStructureType> result = new ArrayList<DataStructureType>();
        for(DataStructure ds:list) {
            result.add(DataStructureUtil.toSDMXDataStructure(ds));
        }
        return result;
    }
    @Override
    public List<DataflowType> search(DataflowReference ref) {
        List<Dataflow> list = DataflowUtil.searchDataflow(query, ref.getAgencyId().toString(),ref.getMaintainableParentId().toString(),ref.getVersion().toString());
        List<DataflowType> result = new ArrayList<DataflowType>();
        for(Dataflow df:list) {
            result.add(DataflowUtil.toSDMXDataflow(df));
        }
        return result;
    }

    @Override
    public List<CodeType> search(CodeReference ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CodelistType> search(CodelistReference ref) {
        return CodelistUtil.toSDMXCodelist(CodelistUtil.searchCodelist(this.query, ref.getAgencyId().toString(), ref.getMaintainableParentId().toString(), ref.getVersion().toString()));
    }

    @Override
    public List<ItemType> search(ItemReference ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemSchemeType> search(ItemSchemeReferenceBase ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConceptType> search(ConceptReference ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConceptSchemeType> search(ConceptSchemeReference ref) {
        return ConceptSchemeUtil.toSDMXConceptSchemeTypes(ConceptSchemeUtil.searchConceptScheme(this.query, ref.getAgencyId().toString(), ref.getMaintainableParentId().toString(), ref.getVersion().toString()));
    }

    @Override
    public void save(OutputStream out) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<StructureType> getCache() {
        return Collections.EMPTY_LIST;
    }
}
