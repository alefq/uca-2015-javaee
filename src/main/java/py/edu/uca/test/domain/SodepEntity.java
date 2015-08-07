package py.edu.uca.test.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * Clase base (padre o superclase) para las entidades JPA que son la manera
 * principal en que la aplicación persiste datos
 * </p>
 *
 * @author sodep S.A.
 */
public abstract class SodepEntity {

    public abstract Object getId();

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null) {
            if (obj.getClass().equals(this.getClass())) {
                SodepEntity other = (SodepEntity) obj;
                if (other.getId() != null && this.getId() != null)
                    equals = this.getId().equals(other.getId());

            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : hashCode();
    }

    @Override
    public String toString() {
        if (Boolean.parseBoolean(System.getProperty("debug.entities"))) {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        } else {
            return super.toString();
        }
    }
}
