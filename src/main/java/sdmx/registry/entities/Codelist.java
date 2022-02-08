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
@Table(name = "Codelist", catalog = "registry", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"agencyid", "id", "version"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codelist.findAll", query = "SELECT c FROM Codelist c"),
    @NamedQuery(name = "Codelist.findByCodelist", query = "SELECT c FROM Codelist c WHERE c.codelist = :codelist"),
    @NamedQuery(name = "Codelist.findByAgencyid", query = "SELECT c FROM Codelist c WHERE c.agencyid = :agencyid"),
    @NamedQuery(name = "Codelist.findById", query = "SELECT c FROM Codelist c WHERE c.id = :id"),
    @NamedQuery(name = "Codelist.findByVersion", query = "SELECT c FROM Codelist c WHERE c.version = :version")})
public class Codelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codelist", nullable = false)
    private Long codelist;
    @Basic(optional = false)
    @Column(name = "agencyid", nullable = false, length = 100)
    private String agencyid;
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false, length = 50)
    private String version;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumn(name = "name", referencedColumnName = "id")
    @ManyToOne
    private Name name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codelist1")
    private List<Code> codeList;

    public Codelist() {
    }

    public Codelist(Long codelist) {
        this.codelist = codelist;
    }

    public Codelist(Long codelist, String agencyid, String id, String version) {
        this.codelist = codelist;
        this.agencyid = agencyid;
        this.id = id;
        this.version = version;
    }

    public Long getCodelist() {
        return codelist;
    }

    public void setCodelist(Long codelist) {
        this.codelist = codelist;
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

    @XmlTransient
    public List<Code> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codelist != null ? codelist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codelist)) {
            return false;
        }
        Codelist other = (Codelist) object;
        if ((this.codelist == null && other.codelist != null) || (this.codelist != null && !this.codelist.equals(other.codelist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Codelist[ codelist=" + codelist + " ]";
    }
    
}
