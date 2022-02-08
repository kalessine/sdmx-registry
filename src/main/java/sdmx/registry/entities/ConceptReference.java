/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "ConceptReference", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptReference.findAll", query = "SELECT c FROM ConceptReference c"),
    @NamedQuery(name = "ConceptReference.findByRefid", query = "SELECT c FROM ConceptReference c WHERE c.refid = :refid"),
    @NamedQuery(name = "ConceptReference.findByAgencyid", query = "SELECT c FROM ConceptReference c WHERE c.agencyid = :agencyid"),
    @NamedQuery(name = "ConceptReference.findById", query = "SELECT c FROM ConceptReference c WHERE c.id = :id"),
    @NamedQuery(name = "ConceptReference.findByVersion", query = "SELECT c FROM ConceptReference c WHERE c.version = :version"),
    @NamedQuery(name = "ConceptReference.findByConceptid", query = "SELECT c FROM ConceptReference c WHERE c.conceptid = :conceptid")})
public class ConceptReference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "refid", nullable = false)
    private Long refid;
    @Column(name = "agencyid", length = 100)
    private String agencyid;
    @Column(name = "id", length = 45)
    private String id;
    @Column(name = "version", length = 50)
    private String version;
    @Column(name = "conceptid", length = 100)
    private String conceptid;
    @OneToMany(mappedBy = "conceptidentity")
    private List<DataStructureComponent> dataStructureComponentList;

    public ConceptReference() {
    }

    public ConceptReference(Long refid) {
        this.refid = refid;
    }

    public Long getRefid() {
        return refid;
    }

    public void setRefid(Long refid) {
        this.refid = refid;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConceptid() {
        return conceptid;
    }

    public void setConceptid(String conceptid) {
        this.conceptid = conceptid;
    }

    @XmlTransient
    public List<DataStructureComponent> getDataStructureComponentList() {
        return dataStructureComponentList;
    }

    public void setDataStructureComponentList(List<DataStructureComponent> dataStructureComponentList) {
        this.dataStructureComponentList = dataStructureComponentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refid != null ? refid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptReference)) {
            return false;
        }
        ConceptReference other = (ConceptReference) object;
        if ((this.refid == null && other.refid != null) || (this.refid != null && !this.refid.equals(other.refid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.ConceptReference[ refid=" + refid + " ]";
    }
    
}
