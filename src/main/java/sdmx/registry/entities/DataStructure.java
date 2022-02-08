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
@Table(name = "DataStructure", catalog = "registry", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"agencyid", "id", "version"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataStructure.findAll", query = "SELECT d FROM DataStructure d"),
    @NamedQuery(name = "DataStructure.findByDatastructure", query = "SELECT d FROM DataStructure d WHERE d.datastructure = :datastructure"),
    @NamedQuery(name = "DataStructure.findByAgencyid", query = "SELECT d FROM DataStructure d WHERE d.agencyid = :agencyid"),
    @NamedQuery(name = "DataStructure.findById", query = "SELECT d FROM DataStructure d WHERE d.id = :id"),
    @NamedQuery(name = "DataStructure.findByVersion", query = "SELECT d FROM DataStructure d WHERE d.version = :version")})
public class DataStructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "datastructure", nullable = false)
    private Long datastructure;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataStructure")
    private List<DataStructureComponent> dataStructureComponentList;

    public DataStructure() {
    }

    public DataStructure(Long datastructure) {
        this.datastructure = datastructure;
    }

    public DataStructure(Long datastructure, String agencyid, String id, String version) {
        this.datastructure = datastructure;
        this.agencyid = agencyid;
        this.id = id;
        this.version = version;
    }

    public Long getDatastructure() {
        return datastructure;
    }

    public void setDatastructure(Long datastructure) {
        this.datastructure = datastructure;
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
    public List<DataStructureComponent> getDataStructureComponentList() {
        return dataStructureComponentList;
    }

    public void setDataStructureComponentList(List<DataStructureComponent> dataStructureComponentList) {
        this.dataStructureComponentList = dataStructureComponentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datastructure != null ? datastructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataStructure)) {
            return false;
        }
        DataStructure other = (DataStructure) object;
        if ((this.datastructure == null && other.datastructure != null) || (this.datastructure != null && !this.datastructure.equals(other.datastructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.DataStructure[ datastructure=" + datastructure + " ]";
    }
    
}
