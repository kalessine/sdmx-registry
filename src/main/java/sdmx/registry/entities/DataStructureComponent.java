/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "DataStructureComponent", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataStructureComponent.findAll", query = "SELECT d FROM DataStructureComponent d"),
    @NamedQuery(name = "DataStructureComponent.findByAgencyid", query = "SELECT d FROM DataStructureComponent d WHERE d.dataStructureComponentPK.agencyid = :agencyid"),
    @NamedQuery(name = "DataStructureComponent.findById", query = "SELECT d FROM DataStructureComponent d WHERE d.dataStructureComponentPK.id = :id"),
    @NamedQuery(name = "DataStructureComponent.findByVersion", query = "SELECT d FROM DataStructureComponent d WHERE d.dataStructureComponentPK.version = :version"),
    @NamedQuery(name = "DataStructureComponent.findByPosition", query = "SELECT d FROM DataStructureComponent d WHERE d.dataStructureComponentPK.position = :position"),
    @NamedQuery(name = "DataStructureComponent.findByType", query = "SELECT d FROM DataStructureComponent d WHERE d.type = :type"),
    @NamedQuery(name = "DataStructureComponent.findByAssignmentstatus", query = "SELECT d FROM DataStructureComponent d WHERE d.assignmentstatus = :assignmentstatus"),
    @NamedQuery(name = "DataStructureComponent.findByIdentifier", query = "SELECT d FROM DataStructureComponent d WHERE d.identifier = :identifier")})
public class DataStructureComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DataStructureComponentPK dataStructureComponentPK;
    @Column(name = "type")
    private Integer type;
    @Column(name = "assignmentstatus", length = 50)
    private String assignmentstatus;
    @Column(name = "identifier", length = 500)
    private String identifier;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumn(name = "attributerelationshiptype", referencedColumnName = "id")
    @ManyToOne
    private AttributeRelationshipType attributerelationshiptype;
    @JoinColumn(name = "codelistenumeration", referencedColumnName = "refid")
    @ManyToOne
    private CodelistReference codelistenumeration;
    @JoinColumn(name = "conceptidentity", referencedColumnName = "refid")
    @ManyToOne
    private ConceptReference conceptidentity;
    @JoinColumn(name = "conceptschemeenumeration", referencedColumnName = "refid")
    @ManyToOne
    private ConceptSchemeReference conceptschemeenumeration;
    @JoinColumns({
        @JoinColumn(name = "agencyid", referencedColumnName = "agencyid", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "version", referencedColumnName = "version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private DataStructure dataStructure;

    public DataStructureComponent() {
    }

    public DataStructureComponent(DataStructureComponentPK dataStructureComponentPK) {
        this.dataStructureComponentPK = dataStructureComponentPK;
    }

    public DataStructureComponent(String agencyid, String id, String version, int position) {
        this.dataStructureComponentPK = new DataStructureComponentPK(agencyid, id, version, position);
    }

    public DataStructureComponentPK getDataStructureComponentPK() {
        return dataStructureComponentPK;
    }

    public void setDataStructureComponentPK(DataStructureComponentPK dataStructureComponentPK) {
        this.dataStructureComponentPK = dataStructureComponentPK;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAssignmentstatus() {
        return assignmentstatus;
    }

    public void setAssignmentstatus(String assignmentstatus) {
        this.assignmentstatus = assignmentstatus;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Annotated getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotated annotations) {
        this.annotations = annotations;
    }

    public AttributeRelationshipType getAttributerelationshiptype() {
        return attributerelationshiptype;
    }

    public void setAttributerelationshiptype(AttributeRelationshipType attributerelationshiptype) {
        this.attributerelationshiptype = attributerelationshiptype;
    }

    public CodelistReference getCodelistenumeration() {
        return codelistenumeration;
    }

    public void setCodelistenumeration(CodelistReference codelistenumeration) {
        this.codelistenumeration = codelistenumeration;
    }

    public ConceptReference getConceptidentity() {
        return conceptidentity;
    }

    public void setConceptidentity(ConceptReference conceptidentity) {
        this.conceptidentity = conceptidentity;
    }

    public ConceptSchemeReference getConceptschemeenumeration() {
        return conceptschemeenumeration;
    }

    public void setConceptschemeenumeration(ConceptSchemeReference conceptschemeenumeration) {
        this.conceptschemeenumeration = conceptschemeenumeration;
    }

    public DataStructure getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(DataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataStructureComponentPK != null ? dataStructureComponentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataStructureComponent)) {
            return false;
        }
        DataStructureComponent other = (DataStructureComponent) object;
        if ((this.dataStructureComponentPK == null && other.dataStructureComponentPK != null) || (this.dataStructureComponentPK != null && !this.dataStructureComponentPK.equals(other.dataStructureComponentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.DataStructureComponent[ dataStructureComponentPK=" + dataStructureComponentPK + " ]";
    }
    
}
