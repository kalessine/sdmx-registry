/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "ConceptScheme", catalog = "registry", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"agencyid", "id", "version"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptScheme.findAll", query = "SELECT c FROM ConceptScheme c"),
    @NamedQuery(name = "ConceptScheme.findByConceptschemeid", query = "SELECT c FROM ConceptScheme c WHERE c.conceptschemeid = :conceptschemeid"),
    @NamedQuery(name = "ConceptScheme.findByAgencyid", query = "SELECT c FROM ConceptScheme c WHERE c.agencyid = :agencyid"),
    @NamedQuery(name = "ConceptScheme.findById", query = "SELECT c FROM ConceptScheme c WHERE c.id = :id"),
    @NamedQuery(name = "ConceptScheme.findByVersion", query = "SELECT c FROM ConceptScheme c WHERE c.version = :version")})
public class ConceptScheme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conceptschemeid", nullable = false)
    private Long conceptschemeid;
    @Basic(optional = false)
    @Column(name = "agencyid", nullable = false, length = 100)
    private String agencyid;
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false, length = 50)
    private String version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptScheme")
    private List<Concept> conceptList;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumn(name = "name", referencedColumnName = "id")
    @ManyToOne
    private Name name;

    public ConceptScheme() {
    }

    public ConceptScheme(Long conceptschemeid) {
        this.conceptschemeid = conceptschemeid;
    }

    public ConceptScheme(Long conceptschemeid, String agencyid, String id, String version) {
        this.conceptschemeid = conceptschemeid;
        this.agencyid = agencyid;
        this.id = id;
        this.version = version;
    }

    public Long getConceptschemeid() {
        return conceptschemeid;
    }

    public void setConceptschemeid(Long conceptschemeid) {
        this.conceptschemeid = conceptschemeid;
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

    @XmlTransient
    public List<Concept> getConceptList() {
        return conceptList;
    }

    public void setConceptList(List<Concept> conceptList) {
        this.conceptList = conceptList;
    }

    public Annotated getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotated annotations) {
        this.annotations = annotations;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptschemeid != null ? conceptschemeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptScheme)) {
            return false;
        }
        ConceptScheme other = (ConceptScheme) object;
        if ((this.conceptschemeid == null && other.conceptschemeid != null) || (this.conceptschemeid != null && !this.conceptschemeid.equals(other.conceptschemeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.ConceptScheme[ conceptschemeid=" + conceptschemeid + " ]";
    }
    
}
